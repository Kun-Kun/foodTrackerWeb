package ua.epam.food.core.security.matcher.impl;

import ua.epam.food.core.security.matcher.RequestMatcher;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RegexRequestURIMatcher implements RequestMatcher {

    private Pattern urlPattern;

    public RegexRequestURIMatcher(String urlPatternString){
        this.urlPattern = Pattern.compile(urlPatternString,Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean matches(ServletRequest request) {

        HttpServletRequest http =  (HttpServletRequest)request;
        String dirtyUri = http.getRequestURI();
        int contextPathEndPosition = http.getContextPath().length();
        int sessionStartPosition = dirtyUri.indexOf(';');
        String uri = null;
        if(sessionStartPosition==-1){
            uri = dirtyUri.substring(contextPathEndPosition);
        }else {
            uri = dirtyUri.substring(contextPathEndPosition,sessionStartPosition);
        }
        return urlPattern.matcher(uri).matches();
    }

}
