/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/28/2021
 * Time      :     1:05 PM
 **/

package me.afifaniks.shoppingcart.web;

import me.afifaniks.shoppingcart.domain.Cart;
import me.afifaniks.shoppingcart.domain.User;
import me.afifaniks.shoppingcart.repository.CartItemRepositoryImpl;
import me.afifaniks.shoppingcart.repository.CartRepositoryImpl;
import me.afifaniks.shoppingcart.repository.ProductRepositoryImpl;
import me.afifaniks.shoppingcart.service.CartService;
import me.afifaniks.shoppingcart.service.CartServiceImpl;
import me.afifaniks.shoppingcart.util.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutServlet.class);

    private CartService cartService = new CartServiceImpl(
            new CartRepositoryImpl(),
            new ProductRepositoryImpl(),
            new CartItemRepositoryImpl()
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Serving checkout page");

        User currentUser = SecurityContext.getCurrentUser(req);
        Cart cart = cartService.getCartByUser(currentUser);
        req.setAttribute("cart", cart);

        req.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(req, resp);
    }
}
