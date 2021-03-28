/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/28/2021
 * Time      :     4:12 PM
 **/

package me.afifaniks.shoppingcart.repository;

import me.afifaniks.shoppingcart.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
