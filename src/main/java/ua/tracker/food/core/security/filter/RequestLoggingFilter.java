package ua.tracker.food.core.security.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet Filter implementation class RequestLoggingFilter
 */
@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {
	private Logger log = LogManager.getLogger(RequestLoggingFilter.class);
 
	@Override
    public void init(FilterConfig fConfig) throws ServletException {

    }
	
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration<String> params = req.getParameterNames();
        while(params.hasMoreElements()){
            String name = params.nextElement();
            String value = request.getParameter(name);
            log.log(Level.INFO, "{}::Request Params::{}={}",req.getRemoteAddr(),name,value);
        }

        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                log.log(Level.INFO, "{}::Cookie::{},{}",req.getRemoteAddr(),cookie.getName(),cookie.getValue());
            }
        }
        // pass the request along the filter chain
		try{
			chain.doFilter(request, response);
		}catch(Exception e){
			log.error("Error :",e);
			throw e;
		}
    }
	@Override
    public void destroy() {
        //we can close resources here
    }

}