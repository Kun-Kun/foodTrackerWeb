package ua.epam.food.core.security.matcher.impl;

import ua.epam.food.core.security.matcher.RequestMatcher;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import java.util.regex.Pattern;

public class RegexMethodRequestMatcher implements RequestMatcher {

    private Pattern method;

    public RegexMethodRequestMatcher(String methods) {
        this.method = Pattern.compile(methods);
    }

    @Override
    public boolean matches(ServletRequest request) {
        HttpServletRequest http =  (HttpServletRequest)request;
        String httpMethod = http.getMethod();
        return method.matcher(httpMethod).matches();
    }

}
