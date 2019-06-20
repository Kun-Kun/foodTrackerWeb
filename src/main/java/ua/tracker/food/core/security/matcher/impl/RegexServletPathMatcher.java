package ua.tracker.food.core.security.matcher.impl;

import ua.tracker.food.core.security.matcher.RequestMatcher;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RegexServletPathMatcher implements RequestMatcher {

    private Pattern urlPattern;

    public RegexServletPathMatcher(String urlPatternString){
        this.urlPattern = Pattern.compile(urlPatternString,Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean matches(ServletRequest request) {

        HttpServletRequest http =  (HttpServletRequest)request;
        String uri = http.getServletPath();
        return urlPattern.matcher(uri).matches();
    }

}
