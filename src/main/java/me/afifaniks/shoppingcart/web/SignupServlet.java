package me.afifaniks.shoppingcart.web;

import me.afifaniks.shoppingcart.service.UserServiceImpl;
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
        Map<String, String> errors = validate(userDTO);

        if (errors.isEmpty()) {
            LOGGER.info("User is valid. Saving.. {}", userDTO);

            userService.saveUser(userDTO);
            resp.sendRedirect("/home");
        } else {
            LOGGER.info("Invalid user data. Saving.. {}", userDTO);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, resp);
        }

    }

    private Map<String, String> validate(UserDTO userDTO) {
        var valFactory = Validation.buildDefaultValidatorFactory();
        var validator = valFactory.getValidator();

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        Map<String, String> errors = new HashMap<>();

        for (var violation: violations) {
            String path = violation.getPropertyPath().toString();

            if (errors.containsKey(path)) {
                String errorMsg = errors.get(path);
                errors.put(path, errorMsg + "<br/>" + violation.getMessage());
            } else {
                errors.put(path, violation.getMessage());
            }
        }
        return errors;
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

//        userDTO.setUsername(req.getParameter("username"));
//        userDTO.setFirstName(req.getParameter("firstName"));
//        userDTO.setLastName(req.getParameter("lastName"));
//        userDTO.setEmail(req.getParameter("email"));
//        userDTO.setPassword(req.getParameter("password"));
//        userDTO.setPasswordConfirmed(req.getParameter("passwordConfirmed"));
//        userDTO.setEmail(req.getParameter("email"));

        return userDTO;
    }
}
