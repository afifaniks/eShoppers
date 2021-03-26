/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/26/2021
 * Time      :     2:16 PM
 **/

package me.afifaniks.shoppingcart.web;

import me.afifaniks.shoppingcart.util.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Logging out user...");
        SecurityContext.logout(req);
        // Redirecting to login page and sending parameter `logout`
        // to let the user know that they logged out successfully
        resp.sendRedirect("/login?logout=true");
    }
}
