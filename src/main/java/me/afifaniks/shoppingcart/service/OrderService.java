/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/28/2021
 * Time      :     4:19 PM
 **/

package me.afifaniks.shoppingcart.service;

import me.afifaniks.shoppingcart.domain.User;
import me.afifaniks.shoppingcart.dto.ShippingAddressDTO;

public interface OrderService {
    void processOrder(ShippingAddressDTO shippingAddressDTO, User currentUser);
}
