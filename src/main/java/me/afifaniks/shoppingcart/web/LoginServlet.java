/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/25/2021
 * Time      :     3:45 PM
 **/

package me.afifaniks.shoppingcart.web;

import me.afifaniks.shoppingcart.domain.User;
import me.afifaniks.shoppingcart.dto.LoginDTO;
import me.afifaniks.shoppingcart.repository.UserRepositoryImpl;
import me.afifaniks.shoppingcart.service.UserService;
import me.afifaniks.shoppingcart.service.UserServiceImpl;
import me.afifaniks.shoppingcart.util.SecurityContext;
import me.afifaniks.shoppingcart.util.UserNotFoundException;
import me.afifaniks.shoppingcart.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final static Logger LOGGER = LoggerFactory.getLogger(SignupServlet.class);
    private UserService userService = new UserServiceImpl(
            new UserRepositoryImpl()
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Serving Login page");

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginDTO loginDTO = new LoginDTO(
                req.getParameter("username"),
                req.getParameter("password")
        );

        LOGGER.info("Login attempted - {}", loginDTO);

        var errors = ValidationUtil.getInstance().validate(loginDTO);

        if (!errors.isEmpty()) {
            LOGGER.info("Login failed.");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }

        try {
            login(loginDTO, req);
            LOGGER.info("Login successful");
            resp.sendRedirect("/home");
        } catch (UserNotFoundException exception) {
            LOGGER.error("User not found");
            req.setAttribute("user", loginDTO);
            req.setAttribute("notFound", "User not found!");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }

    private void login(LoginDTO loginDTO, HttpServletRequest req) throws UserNotFoundException {
        User user = userService.verifyUser(loginDTO);

        SecurityContext.login(req, user);
    }
}
