/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/26/2021
 * Time      :     9:48 PM
 **/

package me.afifaniks.shoppingcart.domain;

import java.math.BigDecimal;

public class CartItem {
    private Long id;
    private Product product;
    private Integer qty;
    private BigDecimal price;

    public CartItem() {
    }

    public CartItem(Long id, Product product, Integer qty, BigDecimal price) {
        this.id = id;
        this.product = product;
        this.qty = qty;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
