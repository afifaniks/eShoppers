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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productId = req.getParameter("productId");
        LOGGER.info("Request to add item to cart. Id: {}", productId);

        var cart = getCart(req);
        addProductToCart(productId, cart);

        resp.sendRedirect("/home");
    }

    private Cart getCart(HttpServletRequest req) {
        final User user = SecurityContext.getCurrentUser(req);
        return cartService.getCartByUser(user);
    }

    private void addProductToCart(String productId, Cart cart) {
        LOGGER.info("Product added to Cart - {}", productId);
    }
}
