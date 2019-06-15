/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.food;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import ua.epam.food.core.security.matcher.RegexRequestMatcher;
import ua.epam.food.core.security.matcher.RequestMatcher;

/**
 *
 * @author nmcdo5
 */
public class FilterBuilder implements FilterAction{
	private List<RequestMatcher> requestMatchers = new ArrayList<>();
	private FilterAction matchAction;
	private FilterAction defaultAction;
	
	public static FilterBuilder init(){
		return new FilterBuilder();
	}
	
	public FilterBuilder matches(String pattern){
		requestMatchers.add(new RegexRequestMatcher(pattern));
		return this;
	}
	
	public MatchAction setMatchAction(){
		return new MatchAction(this);
	}

	public DefaultAction setDefaultAction(){
		return new DefaultAction(this);
	}

	@Override
	public void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		for (RequestMatcher matcher: requestMatchers){
			if (matcher.matches(request)){
				matchAction.execute(request,response,chain);
				return;
			}
		}
		defaultAction.execute(request,response,chain);
	}

	public class MatchAction extends AbstractAction {

		public MatchAction(FilterBuilder parentInstance) {
			super(parentInstance);
		}

		@Override
		protected void setActionField(FilterAction action) {
			matchAction = action;
		}
	}

	public class DefaultAction extends AbstractAction {

		public DefaultAction(FilterBuilder parentInstance) {
			super(parentInstance);
		}

		@Override
		protected void setActionField(FilterAction action) {
			defaultAction = action;
		}
	}

	private abstract class AbstractAction {
		private FilterBuilder parentInstance;

		public AbstractAction(FilterBuilder parentInstance) {
			this.parentInstance = parentInstance;
		}

		protected abstract void setActionField(FilterAction action);

		public FilterBuilder doFilter() {
			setActionField(new FilterAction() {
				@Override
				public void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException{
					chain.doFilter(request, response);
				}
			});
			return parentInstance;
		}

		public FilterBuilder throwException(RuntimeException exception) {
			setActionField(new FilterAction() {
				@Override
				public void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException{
					throw exception;
				}
			});
			return parentInstance;
		}

		public FilterBuilder sendError(int responseCode) {
			setActionField(new FilterAction() {
				@Override
				public void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException{

					HttpServletResponse http = (HttpServletResponse) response;
					http.sendError(responseCode);
				}
			});
			return parentInstance;
		}

		public FilterBuilder sendRedirect(String url) {
			setActionField(new FilterAction() {
				@Override
				public void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException{

					HttpServletResponse http = (HttpServletResponse) response;
					http.sendRedirect(url);
				}
			});
			return parentInstance;
		}

		public FilterBuilder forward(String url) {
			setActionField(new FilterAction() {
				@Override
				public void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException{
					request.getRequestDispatcher(url).forward(request, response);
				}
			});
			return parentInstance;
		}

		public FilterBuilder filterBranch() {
			return FilterBuilder.init();
		}
	}
	
}
