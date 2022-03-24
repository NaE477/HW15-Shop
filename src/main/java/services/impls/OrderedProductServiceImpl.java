package services.impls;

import entities.products.OrderedProduct;
import entities.products.Product;
import repositories.interfaces.OrderedProductRepository;
import services.interfaces.OrderedProductService;

import java.util.List;

public class OrderedProductServiceImpl extends BaseServiceImpl<OrderedProduct, OrderedProductRepository> implements OrderedProductService {
    public OrderedProductServiceImpl(OrderedProductRepository repository) {
        super(repository);
    }

    @Override
    public List<OrderedProduct> findAllByProduct(Product product) {
        return repository.readAllByProduct(product);
    }
}
