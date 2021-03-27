/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/26/2021
 * Time      :     10:30 PM
 **/

package me.afifaniks.shoppingcart.repository;

import me.afifaniks.shoppingcart.domain.Cart;
import me.afifaniks.shoppingcart.domain.User;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findByUser(User currentUser);
    Cart save(Cart cart);
    Cart update(Cart cart);
}
