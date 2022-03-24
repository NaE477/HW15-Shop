package services.impls;

import entities.products.Product;
import repositories.interfaces.ProductRepository;
import services.interfaces.ProductService;

public class ProductServiceImpl extends BaseServiceImpl<Product, ProductRepository> implements ProductService {
    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
    }
}
