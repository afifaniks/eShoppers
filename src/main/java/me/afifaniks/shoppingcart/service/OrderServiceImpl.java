/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/28/2021
 * Time      :     4:24 PM
 **/

package me.afifaniks.shoppingcart.service;

import me.afifaniks.shoppingcart.domain.Order;
import me.afifaniks.shoppingcart.domain.ShippingAddress;
import me.afifaniks.shoppingcart.domain.User;
import me.afifaniks.shoppingcart.dto.ShippingAddressDTO;
import me.afifaniks.shoppingcart.repository.*;

public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private ShippingAddressRepository shippingAddressRepository;
    private CartRepository cartRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ShippingAddressRepository shippingAddressRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.shippingAddressRepository = shippingAddressRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void processOrder(ShippingAddressDTO shippingAddressDTO, User currentUser) {
        var shippingAddress = convertTo(shippingAddressDTO);
        var savedShippingAddress = shippingAddressRepository.save(shippingAddress);

        var cart = cartRepository.findByUser(currentUser).orElseThrow(
                () -> new RuntimeException("Cart Not Found User")
        );

        Order order = new Order();
        order.setCart(cart);
        order.setShippingAddress(shippingAddress);
        order.setShipped(false);
        order.setUser(currentUser);

        orderRepository.save(order);
    }

    private ShippingAddress convertTo(ShippingAddressDTO shippingAddressDTO) {
        var shippingAddress = new ShippingAddress();
        shippingAddress.setAddress(shippingAddressDTO.getAddress());
        shippingAddress.setAddress2(shippingAddressDTO.getAddress2());
        shippingAddress.setCountry(shippingAddressDTO.getCountry());
        shippingAddress.setState(shippingAddressDTO.getState());
        shippingAddress.setZip(shippingAddressDTO.getZip());
        shippingAddress.setMobileNumber(shippingAddressDTO.getMobileNumber());

        return shippingAddress;
    }
}
