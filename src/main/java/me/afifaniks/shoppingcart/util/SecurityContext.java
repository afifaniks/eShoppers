/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/26/2021
 * Time      :     1:25 PM
 **/

package me.afifaniks.shoppingcart.util;

import me.afifaniks.shoppingcart.domain.User;
import me.afifaniks.shoppingcart.web.HomeServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityContext.class);
    public static final String AUTHENTICATION_KEY = "auth.key";

    public static void login(HttpServletRequest request, User user) {
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(AUTHENTICATION_KEY, user);
    }

    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.removeAttribute(AUTHENTICATION_KEY);
    }

    public static User getCurrentUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(AUTHENTICATION_KEY);

        LOGGER.info("Returning User Data: {}", user);
        return user;
    }

    public static boolean isAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        boolean authenticated = session.getAttribute(AUTHENTICATION_KEY) != null;

        LOGGER.info("Authentication {}", authenticated);


        return authenticated;
    }
}
