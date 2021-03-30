/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/28/2021
 * Time      :     7:39 PM
 **/

package me.afifaniks.shoppingcart.web;

import me.afifaniks.shoppingcart.dto.ShippingAddressDTO;
import me.afifaniks.shoppingcart.repository.*;
import me.afifaniks.shoppingcart.service.CartService;
import me.afifaniks.shoppingcart.service.CartServiceImpl;
import me.afifaniks.shoppingcart.service.OrderService;
import me.afifaniks.shoppingcart.service.OrderServiceImpl;
import me.afifaniks.shoppingcart.util.SecurityContext;
import me.afifaniks.shoppingcart.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServlet.class);

    private CartService cartService = new CartServiceImpl(
            new CartRepositoryImpl(),
            new ProductRepositoryImpl(),
            new CartItemRepositoryImpl()
    );
    private OrderService orderService = new OrderServiceImpl(
            new OrderRepositoryImpl(),
            new ShippingAddressRepositoryImpl(),
            new CartRepositoryImpl()
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCartToUi(req);
        req.setAttribute("countries", getCountries());

        req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Order Request Form Handling");

        var shippingAddress = copyParametersTo(req);
        LOGGER.info("shipping address: {}", shippingAddress);

        var errors = ValidationUtil.getInstance().validate(shippingAddress);

        if (!errors.isEmpty()) {
            req.setAttribute("countries", getCountries());
            req.setAttribute("errors", errors);
            req.setAttribute("shippingAddress", shippingAddress);
            addCartToUi(req);
            req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
        } else {
            orderService.processOrder(shippingAddress, SecurityContext.getCurrentUser(req));
            resp.sendRedirect("/home?orderSuccess=true");
        }
    }

    private ShippingAddressDTO copyParametersTo(HttpServletRequest request) {
        var shippingAddressDTO = new ShippingAddressDTO();

        shippingAddressDTO.setAddress(request.getParameter("address"));
        shippingAddressDTO.setAddress2(request.getParameter("address2"));
        shippingAddressDTO.setState(request.getParameter("state"));
        shippingAddressDTO.setZip(request.getParameter("zip"));
        shippingAddressDTO.setCountry(request.getParameter("country"));
        shippingAddressDTO.setMobileNumber(request.getParameter("mobileNumber"));

        return shippingAddressDTO;
    }

    private List<String> getCountries() {
        return List.of(
                "Bangladesh",
                "USA",
                "Australia"
        );
    }

    private void addCartToUi(HttpServletRequest request) {
        if (SecurityContext.isAuthenticated(request)) {
            var currentUser = SecurityContext.getCurrentUser(request);
            var cart = cartService.getCartByUser(currentUser);
            request.setAttribute("cart", cart);
        }
    }
}
