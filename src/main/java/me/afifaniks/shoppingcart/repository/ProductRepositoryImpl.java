/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/23/2021
 * Time      :     5:40 PM
 **/

package me.afifaniks.shoppingcart.repository;

import me.afifaniks.shoppingcart.domain.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    private static final List<Product> ALL_PRODUCTS = List.of(
            new Product(
                    1L,
                    "Apple iPad",
                    "iPad 10 GB",
                    BigDecimal.valueOf(500.00)
            ),
            new Product(
                    2L,
                    "JBM Box",
                    "JBM Speakers",
                    BigDecimal.valueOf(20.22)
            ),
            new Product(
                    3L,
                    "Apple Airpod",
                    "Airpod mini",
                    BigDecimal.valueOf(50.69)
            ),
            new Product(
                    4L,
                    "Razer Kraken",
                    "Best Mouse at Worst Price",
                    BigDecimal.valueOf(70.00)
            ),
            new Product(
                    5L,
                    "Bloody M1 Headphones",
                    "Headphone from A4Tech",
                    BigDecimal.valueOf(30)
            )
    );

    @Override
    public List<Product> findAllProducts() {
        return ALL_PRODUCTS;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return ALL_PRODUCTS.stream().filter(
                product ->
                        Objects.equals(id, product.getId())
        ).findFirst();
    }
}
