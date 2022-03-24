package repositories.impls;

import entities.products.Product;
import repositories.interfaces.ProductRepository;

import javax.persistence.EntityManagerFactory;

public class ProductRepositoryImpl extends BaseRepositoryImpl<Product> implements ProductRepository {
    public ProductRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Product> clazz) {
        super(entityManagerFactory, clazz);
    }
}
