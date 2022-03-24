package services.impls;

import entities.products.OrderedProduct;
import repositories.interfaces.OrderedProductRepository;
import services.interfaces.OrderedProductService;

public class OrderedProductServiceImpl extends BaseServiceImpl<OrderedProduct, OrderedProductRepository> implements OrderedProductService {
    public OrderedProductServiceImpl(OrderedProductRepository repository) {
        super(repository);
    }
}
