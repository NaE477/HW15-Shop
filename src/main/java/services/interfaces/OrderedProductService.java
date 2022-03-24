package services.interfaces;

import entities.products.OrderedProduct;
import entities.products.Product;

import java.util.List;

public interface OrderedProductService extends BaseService<OrderedProduct> {
    List<OrderedProduct> findAllByProduct(Product product);
}
