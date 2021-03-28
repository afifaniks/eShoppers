/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/26/2021
 * Time      :     9:30 PM
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
import me.afifaniks.shoppingcart.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-to-cart")
public class CartServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartServlet.class);
    private CartService cartService = new CartServiceImpl(
            new CartRepositoryImpl(),
            new ProductRepositoryImpl(),
            new CartItemRepositoryImpl());
    private enum Action {
        ADD,
        REMOVE
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productId = req.getParameter("productId");
        var action = req.getParameter("action");
        var cart = getCart(req);

        if (!StringUtil.isEmpty(action)) {
            processCart(productId, action, cart);
            resp.sendRedirect("/checkout");
            return;
        }

        LOGGER.info("Request to add item to cart. Id: {}", productId);

        cartService.addProductToCart(productId, cart);
        resp.sendRedirect("/home");
    }

    private void processCart(String productId, String action, Cart cart) {
        switch (Action.valueOf(action.toUpperCase())) {
            case ADD:
                LOGGER.info("Received request to add product");
                cartService.addProductToCart(productId, cart);
                break;
            case REMOVE:
                LOGGER.info("Received request to remove product");
                cartService.removeProductFromCart(productId, cart);
                break;
        }
    }

    private Cart getCart(HttpServletRequest req) {
        final User user = SecurityContext.getCurrentUser(req);
        return cartService.getCartByUser(user);
    }

    private void addProductToCart(String productId, Cart cart) {
        LOGGER.info("Product added to Cart - {}", productId);
    }
}
