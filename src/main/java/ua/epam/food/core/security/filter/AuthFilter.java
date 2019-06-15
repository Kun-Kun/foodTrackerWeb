package ua.epam.food.core.security.filter;

import ua.epam.food.FilterAction;
import ua.epam.food.FilterBuilder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/AuthFilter")
public class AuthFilter implements Filter {
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
                .matches("^/s$")
                .matches("^/$")
                .setMatchAction().sendError(HttpServletResponse.SC_FORBIDDEN)
                .setDefaultAction().filterBranch()
                    .matches("^/CheckoutPage.jsp$")
                    .setMatchAction().forward("/login.jsp")
                    .setDefaultAction().doFilter();
    }

}