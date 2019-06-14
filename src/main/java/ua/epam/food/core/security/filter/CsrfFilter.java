package ua.epam.food.core.security.filter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import ua.epam.food.core.security.matcher.RegexMethodRequestMatcher;
import ua.epam.food.core.security.matcher.RequestMatcher;
import ua.epam.food.exception.CsrfErrorException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@WebFilter("/CsrfFilter")
public class CsrfFilter implements Filter {

    private static final String OUTGOING_CSRF_ATTRIBUTE_NAME = "csrf_token";
    private static final String INCOMING_CSRF_ATTRIBUTE_NAME = "_csrf";
    private static final String SESSION_CSRF_ATTRIBUTE_NAME = "csrfCache";

    private RequestMatcher requestMatcher = new RegexMethodRequestMatcher("^(GET|HEAD|TRACE|OPTIONS)$");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException, CsrfErrorException {


        // Assume its HTTP
        HttpServletRequest httpReq = (HttpServletRequest) request;

        // Check the user session for the salt cache, if none is present we create one
        Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>)
                httpReq.getSession().getAttribute(SESSION_CSRF_ATTRIBUTE_NAME);

        if (csrfPreventionSaltCache == null) {
            csrfPreventionSaltCache = CacheBuilder.newBuilder()
                    .maximumSize(5000)
                    .expireAfterWrite(12, TimeUnit.HOURS)
                    .build();

            httpReq.getSession().setAttribute(SESSION_CSRF_ATTRIBUTE_NAME, csrfPreventionSaltCache);
        }

        // Generate the salt and store it in the users cache
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        csrfPreventionSaltCache.put(uuidString, Boolean.TRUE);

        // Add the salt to the current request so it can be used
        // by the page rendered in this request
        httpReq.setAttribute(OUTGOING_CSRF_ATTRIBUTE_NAME, uuidString);
        httpReq.setAttribute("csrf_field_name", INCOMING_CSRF_ATTRIBUTE_NAME);

        if (requestMatcher.matches(httpReq)) {
            chain.doFilter(request, response);
        } else {
            String incomingCsrf = httpReq.getParameter(INCOMING_CSRF_ATTRIBUTE_NAME);
            if (incomingCsrf != null && csrfPreventionSaltCache.getIfPresent(incomingCsrf) != null) {
                chain.doFilter(request, response);
            } else {
                throw new CsrfErrorException();
            }
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
