/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/23/2021
 * Time      :     5:14 PM
 **/

package me.afifaniks.shoppingcart.web;

import me.afifaniks.shoppingcart.dto.ProductDTO;
import me.afifaniks.shoppingcart.repository.DummyProductRepositoryImpl;
import me.afifaniks.shoppingcart.service.ProductService;
import me.afifaniks.shoppingcart.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private ProductService productService
            = new ProductServiceImpl(
                    new DummyProductRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ProductDTO> allProducts =
                productService.findAllProductsByName();

        req.setAttribute("products", allProducts);

        req.getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(req, resp);
    }
}
