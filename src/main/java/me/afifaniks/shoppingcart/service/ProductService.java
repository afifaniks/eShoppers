/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/23/2021
 * Time      :     5:14 PM
 **/

package me.afifaniks.shoppingcart.service;

import me.afifaniks.shoppingcart.domain.Cart;
import me.afifaniks.shoppingcart.domain.Product;
import me.afifaniks.shoppingcart.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllProductsSortedByName();
}
