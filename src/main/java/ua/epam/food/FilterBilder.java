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
import ua.epam.food.core.security.matcher.RegexRequestMatcher;
import ua.epam.food.core.security.matcher.RequestMatcher;

/**
 *
 * @author nmcdo5
 */
public class FilterBilder {
	private List<RequestMatcher> matchers = new ArrayList<>();
	private ServletRequest request;
	private ServletResponse response;
	private FilterChain chain;
	
	private FilterAction matchAction;
	private FilterAction defaultAction;
	
	public static FilterBilder init(ServletRequest request,ServletResponse response,FilterChain chain){
		FilterBilder instance = new FilterBilder();
		instance.chain = chain;
		instance.request = request;
		instance.response = response;
		return instance;
	}
	
	public FilterBilder matches(String pattern){
		matchers.add(new RegexRequestMatcher(pattern));
		return this;
	}
	
	public MatchAction setMatchAction(){
		return new MatchAction(this);
	}
	
	public class MatchAction {
		private FilterBilder parentInstance;

		public MatchAction(FilterBilder parentInstance) {
			this.parentInstance = parentInstance;
		}
		
		public FilterBilder doFilter() {
			matchAction=new FilterAction() {
				@Override
				public void execute() throws IOException,ServletException{
					parentInstance.chain.doFilter(request, response);
				}
			};
			return parentInstance;
		}
	}
	
}
