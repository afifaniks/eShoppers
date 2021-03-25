package me.afifaniks.shoppingcart.web;

import me.afifaniks.shoppingcart.service.UserServiceImpl;
import me.afifaniks.shoppingcart.dto.UserDTO;
import me.afifaniks.shoppingcart.repository.UserRepositoryImpl;
import me.afifaniks.shoppingcart.service.UserService;
import me.afifaniks.shoppingcart.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        Map<String, String> errors = ValidationUtil.getInstance().validate(userDTO);

        // Check for existing username only if other
        // fields are OKAY
        if (errors.isEmpty()) {
            boolean usernameExists = userService.isNotUniqueUsername(userDTO);
            if (usernameExists) {
                LOGGER.error("{} username exists!", userDTO.getUsername());
                errors.put("username", userDTO.getUsername() + " already exists. Please try another one.");
            }
        }

        if (errors.isEmpty()) {
            LOGGER.info("User is valid. Saving..");
            userService.saveUser(userDTO);
            resp.sendRedirect("/login");
        } else {
            LOGGER.info("Invalid user data. Saving.. {}", userDTO);
            req.setAttribute("errors", errors);
            req.setAttribute("user", userDTO);
            req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, resp);
        }

    }

    private UserDTO copyParametersTo(HttpServletRequest req) {
        UserDTO userDTO = new UserDTO(
                req.getParameter("username"),
                req.getParameter("password"),
                req.getParameter("passwordConfirmed"),
                req.getParameter("email"),
                req.getParameter("firstName"),
                req.getParameter("lastName")
        );

        return userDTO;
    }
}
