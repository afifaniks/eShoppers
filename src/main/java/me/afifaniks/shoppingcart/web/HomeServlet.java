/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/23/2021
 * Time      :     5:14 PM
 **/

package me.afifaniks.shoppingcart.web;

import me.afifaniks.shoppingcart.domain.Cart;
import me.afifaniks.shoppingcart.dto.ProductDTO;
import me.afifaniks.shoppingcart.repository.CartItemRepositoryImpl;
import me.afifaniks.shoppingcart.repository.CartRepositoryImpl;
import me.afifaniks.shoppingcart.repository.ProductRepositoryImpl;
import me.afifaniks.shoppingcart.service.CartService;
import me.afifaniks.shoppingcart.service.CartServiceImpl;
import me.afifaniks.shoppingcart.service.ProductService;
import me.afifaniks.shoppingcart.service.ProductServiceImpl;
import me.afifaniks.shoppingcart.util.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeServlet.class);
    private ProductService productService
            = new ProductServiceImpl(new ProductRepositoryImpl());
    private CartService cartService =
            new CartServiceImpl(
                    new CartRepositoryImpl(),
                    new ProductRepositoryImpl(),
                    new CartItemRepositoryImpl()
            );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Serving home page");

        final String orderSuccessRedirect = req.getParameter("orderSuccess");
        if (orderSuccessRedirect != null && Boolean.valueOf(orderSuccessRedirect)) {
            req.setAttribute("message", "Your order has been placed successfully!");
        }

        List<ProductDTO> allProducts =
                productService.findAllProductsSortedByName();

        req.setAttribute("products", allProducts);

        LOGGER.info("Total Products: {}", allProducts.size());

//        Cart cart = cartService.getCartByUser(SecurityContext.getCurrentUser(req));

        if (SecurityContext.isAuthenticated(req)) {
            var currentUser = SecurityContext.getCurrentUser(req);
            var cart1 = cartService.getCartByUser(currentUser);
            req.setAttribute("cart", cart1);
        }

        req.getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(req, resp);
    }
}
