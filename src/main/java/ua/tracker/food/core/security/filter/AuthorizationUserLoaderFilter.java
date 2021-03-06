package ua.tracker.food.core.security.filter;

import ua.tracker.food.core.security.context.SecurityContextHolder;
import ua.tracker.food.core.security.data.UserDetail;
import ua.tracker.food.services.UserDetailService;
import ua.tracker.food.services.UserDetailServiceImpl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthorizationUserLoaderFilter")
public class AuthorizationUserLoaderFilter implements Filter {

    private ServletContext context;
    private SecurityContextHolder contextHolder = SecurityContextHolder.getInstance();
    private UserDetailService userDetailService = UserDetailServiceImpl.getInstance();

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthorizationUserLoaderFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        String sessionUsername =
        //        "admin";
        (String)session.getAttribute("username");
        UserDetail userDetail = userDetailService.loadUserByUsername(sessionUsername);
        contextHolder.setSecurityData(userDetail);
        request.setAttribute("userInformation",userDetail);
        
        chain.doFilter(request, response);
    }

    public void destroy() {
        //close any resources here
    }

}