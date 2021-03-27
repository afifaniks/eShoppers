/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/26/2021
 * Time      :     10:30 PM
 **/

package me.afifaniks.shoppingcart.service;

import me.afifaniks.shoppingcart.domain.Cart;
import me.afifaniks.shoppingcart.domain.CartItem;
import me.afifaniks.shoppingcart.domain.Product;
import me.afifaniks.shoppingcart.domain.User;
import me.afifaniks.shoppingcart.repository.CartItemRepository;
import me.afifaniks.shoppingcart.repository.CartRepository;
import me.afifaniks.shoppingcart.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart getCartByUser(User currentUser) {
        Optional<Cart> optionalCart = cartRepository.findByUser(currentUser);

        return optionalCart.orElseGet(() -> createNewCart(currentUser));
    }

    private Cart createNewCart(User currentUser) {
        Cart cart = new Cart();
        cart.setUser(currentUser);

        return cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(String productId, Cart cart) {
        if (productId == null || productId.length() == 0)
            throw new IllegalArgumentException("Product id can not be null");

        Long id = parseProductId(productId);

        var product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product Not Found. ID " + id)
        );

        addProductToCart(product, cart);

        Integer totalItem = getTotalItem(cart);
        BigDecimal totalPrice = calculateTotalPrice(cart);

        cartRepository.save(cart);
    }

    private BigDecimal calculateTotalPrice(Cart cart) {
        return cart.getCartItems().stream().map(CartItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Integer getTotalItem(Cart cart) {
        return cart.getCartItems().stream().map(CartItem::getQty).reduce(0, Integer::sum);
    }

    private void addProductToCart(Product product, Cart cart) {
        var cartItemOptional = findSimilarProductInCart(cart, product);

        var cartItem = cartItemOptional.map(this::increaseQuantityByOne)
                .orElseGet(() -> createNewShoppingCartItem(product));

        cart.getCartItems().add(cartItem);
    }

    private CartItem createNewShoppingCartItem(Product product) {
        var cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQty(1);
        cartItem.setPrice(product.getPrice());

        return cartItemRepository.save(cartItem);
    }

    private CartItem increaseQuantityByOne(CartItem cartItem) {
        cartItem.setQty(cartItem.getQty() + 1);

        BigDecimal totalPrice = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQty()));
        cartItem.setPrice(totalPrice);

        return cartItemRepository.update(cartItem);
    }

    private Optional<CartItem> findSimilarProductInCart(Cart cart, Product product) {
        return cart.getCartItems()
                .stream()
                .filter(
                        cartItem ->
                                cartItem.getProduct().equals(product)
                )
                .findFirst();
    }

    private Long parseProductId(String productId) {
        try {
            return Long.parseLong(productId);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Product id must be a number", exception);
        }
    }
}
