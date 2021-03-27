/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/23/2021
 * Time      :     5:44 PM
 **/

package me.afifaniks.shoppingcart.repository;

import me.afifaniks.shoppingcart.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAllProducts();
    Optional<Product> findById(Long id);
}
