/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/28/2021
 * Time      :     4:06 PM
 **/

package me.afifaniks.shoppingcart.domain;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Cart cart;
    private ShippingAddress shippingAddress;
    private LocalDateTime shippingDate;
    private Payment payment;
    private boolean shipped;
    private User user;

    public Order() {
    }

    public Order(Long id, Cart cart, ShippingAddress shippingAddress, LocalDateTime shippingDate, Payment payment, boolean shipped, User user) {
        this.id = id;
        this.cart = cart;
        this.shippingAddress = shippingAddress;
        this.shippingDate = shippingDate;
        this.payment = payment;
        this.shipped = shipped;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cart=" + cart +
                ", shippingAddress=" + shippingAddress +
                ", shippingDate=" + shippingDate +
                ", payment=" + payment +
                ", shipped=" + shipped +
                ", user=" + user +
                '}';
    }
}
