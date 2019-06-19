/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.food.core.security.filter.builder;

import ua.epam.food.core.security.data.Privilege;
import ua.epam.food.core.security.data.Role;
import ua.epam.food.core.security.matcher.*;
import ua.epam.food.core.security.matcher.impl.AuthenticatedSecurityContextMatcher;
import ua.epam.food.core.security.matcher.impl.PrivilegeSecurityContextMatcher;
import ua.epam.food.core.security.matcher.impl.RegexRequestMatcher;
import ua.epam.food.core.security.matcher.impl.RoleSecurityContextMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author nmcdo5
 */
public class FilterBuilder implements FilterAction {

    private Set<RequestMatcher> subMatchers = new HashSet<>();
    private FilterAction matchAction;
    private FilterAction defaultAction;

    public static FilterBuilder init() {
        return new FilterBuilder();
    }

    public SubMatcher uriMatches(String pattern) {
        SubMatcher subMatcher = new SubMatcher(this);
        subMatcher.uriMatches(pattern);
        return subMatcher;
    }

    public SubMatcher isAuthenticated() {
        SubMatcher subMatcher = new SubMatcher(this);
        subMatcher.isAuthenticated();
        return subMatcher;
    }

    public SubMatcher isNotAuthenticated() {
        SubMatcher subMatcher = new SubMatcher(this);
        subMatcher.isAuthenticated();
        return subMatcher;
    }

    public SubMatcher hasRole(Role role) {
        SubMatcher subMatcher = new SubMatcher(this);
        subMatcher.hasRole(role);
        return subMatcher;
    }

    public SubMatcher doesntHaveRole(Role role) {
        SubMatcher subMatcher = new SubMatcher(this);
        subMatcher.doesntHaveRole(role);
        return subMatcher;
    }

    public SubMatcher hasPrivilege(Privilege privilege) {
        SubMatcher subMatcher = new SubMatcher(this);
        subMatcher.hasPrivilege(privilege);
        return subMatcher;
    }

    public SubMatcher doesntHavePrivilege(Privilege privilege) {
        SubMatcher subMatcher = new SubMatcher(this);
        subMatcher.doesntHavePrivilege(privilege);
        return subMatcher;
    }

    public MatchAction setMatchAction() {
        return new MatchAction(this);
    }

    public DefaultAction setDefaultAction() {
        return new DefaultAction(this);
    }

    @Override
    public void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        for (RequestMatcher matcher : subMatchers) {
            if (!matcher.matches(request)) {
                defaultAction.execute(request, response, chain);
                return;
            }
        }
        matchAction.execute(request, response, chain);

    }

    public class SubMatcher implements RequestMatcher {

        private FilterBuilder filterBuilderInstance;
        private Set<RequestMatcher> requestMatchers = new HashSet<>();

        private SubMatcher(FilterBuilder parentInstance) {
            this.filterBuilderInstance = parentInstance;
        }

        @Override
        public boolean matches(ServletRequest request) {
            for (RequestMatcher matcher : requestMatchers) {
                if (matcher.matches(request)) {
                    return true;
                }
            }
            return false;
        }

        public SubMatcher uriMatches(String pattern) {
            requestMatchers.add(new RegexRequestMatcher(pattern));
            return this;
        }
        public SubMatcher isAuthenticated() {
            requestMatchers.add(new AuthenticatedSecurityContextMatcher(false));
            return this;
        }

        public SubMatcher isNotAuthenticated() {
            requestMatchers.add(new AuthenticatedSecurityContextMatcher(true));
            return this;
        }

        public SubMatcher hasRole(Role role) {
            requestMatchers.add(new RoleSecurityContextMatcher(role,false));
            return this;
        }

        public SubMatcher doesntHaveRole(Role role) {
            requestMatchers.add(new RoleSecurityContextMatcher(role,true));
            return this;
        }

        public SubMatcher hasPrivilege(Privilege privilege) {
            requestMatchers.add(new PrivilegeSecurityContextMatcher(privilege,false));
            return this;
        }

        public SubMatcher doesntHavePrivilege(Privilege privilege) {
            requestMatchers.add(new PrivilegeSecurityContextMatcher(privilege,true));
            return this;
        }

        public SubMatcher and() {
            subMatchers.add(this);
            return new SubMatcher(filterBuilderInstance);
        }

        public MatchAction setMatchAction() {
            subMatchers.add(this);
            return new MatchAction(filterBuilderInstance);
        }

        public DefaultAction setDefaultAction() {
            subMatchers.add(this);
            return new DefaultAction(filterBuilderInstance);
        }

	}

    public class MatchAction extends AbstractAction {

        private MatchAction(FilterBuilder parentInstance) {
            super(parentInstance);
        }

        @Override
        protected void setActionField(FilterAction action) {
            matchAction = action;
        }
    }

    public class DefaultAction extends AbstractAction {

        private DefaultAction(FilterBuilder parentInstance) {
            super(parentInstance);
        }

        @Override
        protected void setActionField(FilterAction action) {
            defaultAction = action;
        }
    }

    private abstract class AbstractAction {
        private FilterBuilder filterBuilderInstance;

        private AbstractAction(FilterBuilder parentInstance) {
            this.filterBuilderInstance = parentInstance;
        }

        protected abstract void setActionField(FilterAction action);

        public FilterBuilder doFilter() {
            setActionField(new FilterAction() {
                @Override
                public void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                    chain.doFilter(request, response);
                }
            });
            return filterBuilderInstance;
        }

        public FilterBuilder throwException(RuntimeException exception) {
            setActionField(new FilterAction() {
                @Override
                public void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                    throw exception;
                }
            });
            return filterBuilderInstance;
        }

        public FilterBuilder sendError(int responseCode) {
            setActionField(new FilterAction() {
                @Override
                public void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

                    HttpServletResponse http = (HttpServletResponse) response;
                    http.sendError(responseCode);
                }
            });
            return filterBuilderInstance;
        }

        public FilterBuilder sendRedirect(String url) {
            setActionField(new FilterAction() {
                @Override
                public void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
					HttpServletRequest httpRequest = (HttpServletRequest) request;
					String contextPath = httpRequest.getContextPath();
                    httpResponse.sendRedirect(contextPath+url);
                }
            });
            return filterBuilderInstance;
        }

        public FilterBuilder forward(String url) {
            setActionField(new FilterAction() {
                @Override
                public void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                    storeUrlInParameter("origin",request);
                    request.getRequestDispatcher(url).forward(request, response);
                }
            });
            return filterBuilderInstance;
        }

        public FilterBuilder filterBranch(FilterAction filter) {
            setActionField(filter);
            return filterBuilderInstance;
        }

        private void storeUrlInSession(String parameterName, ServletRequest request){
            HttpServletRequest http = (HttpServletRequest) request;
            HttpSession session = http.getSession(true);
            session.setAttribute(parameterName, http.getRequestURI());
        }

        private void storeUrlInParameter(String parameterName, ServletRequest request){
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            request.setAttribute(parameterName, httpRequest.getRequestURI());
        }
    }

}
