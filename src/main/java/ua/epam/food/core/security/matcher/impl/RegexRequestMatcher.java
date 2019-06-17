package ua.epam.food.core.security.matcher.impl;

import ua.epam.food.core.security.matcher.RequestMatcher;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RegexRequestMatcher implements RequestMatcher {

    private Pattern urlPattern;

    public RegexRequestMatcher(String urlPatternString){
        this.urlPattern = Pattern.compile(urlPatternString,Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean matches(ServletRequest request) {

        HttpServletRequest http =  (HttpServletRequest)request;
        String uri = http.getRequestURI().substring(http.getContextPath().length());
        return urlPattern.matcher(uri).matches();
    }

}
