/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/26/2021
 * Time      :     10:29 PM
 **/

package me.afifaniks.shoppingcart.service;

import me.afifaniks.shoppingcart.domain.Cart;
import me.afifaniks.shoppingcart.domain.User;

public interface CartService {
    Cart getCartByUser(User currentUser);
    void addProductToCart(String productId, Cart cart);
    void removeProductFromCart(String productId, Cart cart);
}
