package repositories.interfaces;

import entities.products.OrderedProduct;
import entities.products.Product;

import java.util.List;

public interface OrderedProductRepository extends BaseRepository<OrderedProduct> {
    List<OrderedProduct> readAllByProduct(Product product);
}
