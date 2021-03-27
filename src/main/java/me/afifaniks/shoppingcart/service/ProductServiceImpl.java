/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/23/2021
 * Time      :     5:24 PM
 **/

package me.afifaniks.shoppingcart.service;

import me.afifaniks.shoppingcart.domain.Cart;
import me.afifaniks.shoppingcart.domain.Product;
import me.afifaniks.shoppingcart.dto.ProductDTO;
import me.afifaniks.shoppingcart.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAllProductsSortedByName() {
        return productRepository.findAllProducts()
                .stream()
                .map(this::converToDTO)
                .sorted(Comparator.comparing(ProductDTO::getName))
                .collect(Collectors.toList());
    }

    private ProductDTO converToDTO(Product product) {
        return new me.afifaniks.shoppingcart.dto.ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
