package me.afifaniks.shoppingcart.web;

import me.afifaniks.shoppingcart.UserServiceImpl;
import me.afifaniks.shoppingcart.dto.UserDTO;
import me.afifaniks.shoppingcart.repository.UserRepositoryImpl;
import me.afifaniks.shoppingcart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private final static Logger LOGGER = LoggerFactory.getLogger(SignupServlet.class);
    private UserService userService = new UserServiceImpl(
            new UserRepositoryImpl()
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Serving Sign Up page");

        req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = copyParametersTo(req);
        if (isValid(userDTO)) {
            LOGGER.info("User is valid. Saving.. {}", userDTO);

            userService.saveUser(userDTO);
            resp.sendRedirect("/home");
        } else {
            LOGGER.info("Invalid user data. Saving.. {}", userDTO);
            req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, resp);
        }

    }

    private boolean isValid(UserDTO userDTO) {
        return true;
    }

    private UserDTO copyParametersTo(HttpServletRequest req) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(req.getParameter("firstName"));
        userDTO.setLastName(req.getParameter("lastName"));
        userDTO.setEmail(req.getParameter("email"));
        userDTO.setPassword(req.getParameter("password"));
        userDTO.setPasswordConfirmed(req.getParameter("passwordConfirmed"));
        userDTO.setEmail(req.getParameter("email"));

        return userDTO;
    }
}
