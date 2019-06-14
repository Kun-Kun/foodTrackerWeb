package ua.epam.food.core.security.matcher;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RegexRequestMatcher implements RequestMatcher {

    private Pattern urlPattern;

    public RegexRequestMatcher(String urlPatternString){
        this.urlPattern = Pattern.compile(urlPatternString);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return urlPattern.matcher(request.getRequestURI()).matches();
    }

}
