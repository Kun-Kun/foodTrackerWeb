package ua.epam.food.core.security.matcher;

import javax.servlet.http.HttpServletRequest;

import java.util.regex.Pattern;

public class RegexMethodRequestMatcher implements RequestMatcher {

    private Pattern method;//

    public RegexMethodRequestMatcher(String methods) {
        this.method = Pattern.compile(methods);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return method.matcher(request.getMethod()).matches();
    }

}
