package ua.epam.food.core.security.filter;

import ua.epam.food.core.security.filter.builder.FilterAction;
import ua.epam.food.core.security.filter.builder.FilterBuilder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/AuthorizationUriFilter")
public class AuthorizationUriFilter implements Filter {
    private FilterAction filter;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest http =  (HttpServletRequest)request;
        String uri = http.getRequestURI();
        System.out.println();
        System.out.println("uri from filter "+uri);
        if (filter==null){
            filter = buildFilter();
        }
        filter.execute(request,response,chain);
    }


    public void destroy() {
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

    private FilterAction buildFilter(){
        return FilterBuilder
                .init()
                .uriMatches("^/s$")
                .uriMatches("^/$")
                .and()
                .isNotAuthenticated()
                .setMatchAction().forward("/login.jsp")
                .setDefaultAction()
                .filterBranch(FilterBuilder
                        .init().uriMatches("^/CheckoutPage.jsp$")
                        .setMatchAction().sendError(404)
                        .setDefaultAction()
                        .doFilter()
                );

    }

}