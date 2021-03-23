/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/23/2021
 * Time      :     5:40 PM
 **/

package me.afifaniks.shoppingcart.repository;

import me.afifaniks.shoppingcart.dto.ProductDTO;
import java.math.BigDecimal;
import java.util.List;

public class DummyProductRepositoryImpl implements ProductRepository {
    @Override
    public List<ProductDTO> findAllProducts() {
        return List.of(
                new ProductDTO(
                        "Apple iPad",
                        "iPad 10 GB",
                        BigDecimal.valueOf(34324432.22)
                ),
                new ProductDTO(
                        "JBM Box",
                        "JBL SS",
                        BigDecimal.valueOf(2432.22)
                ),
                new ProductDTO(
                        "Apple Airpod",
                        "Airpod mini",
                        BigDecimal.valueOf(432.22)
                )
        );
    }
}
