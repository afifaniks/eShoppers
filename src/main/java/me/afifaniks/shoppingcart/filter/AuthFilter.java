/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/26/2021
 * Time      :     10:07 PM
 **/

package me.afifaniks.shoppingcart.filter;

import me.afifaniks.shoppingcart.util.SecurityContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter{
    private static final String[] ALLOWED_CONTENTS = {
            ".css",
            ".js",
            ".jpg",
            ".png",
            "home",
            "login",
            "signup"
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpServletRequest = (HttpServletRequest) request;
        var requestedUri = httpServletRequest.getRequestURI();

        boolean allowed = Stream.of(ALLOWED_CONTENTS).anyMatch(requestedUri::contains);

        if (requestedUri.equals("/")
                || allowed
                || SecurityContext.isAuthenticated(httpServletRequest)) {
            chain.doFilter(request, response);
        } else {
            httpServletRequest.setAttribute("message", "Please login to complete the action");
            httpServletRequest.getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(httpServletRequest, response);
        }
    }
}
