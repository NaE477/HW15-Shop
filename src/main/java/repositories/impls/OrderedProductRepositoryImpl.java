package repositories.impls;

import entities.products.OrderedProduct;
import repositories.interfaces.OrderedProductRepository;

import javax.persistence.EntityManagerFactory;

public class OrderedProductRepositoryImpl extends BaseRepositoryImpl<OrderedProduct> implements OrderedProductRepository {
    public OrderedProductRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<OrderedProduct> clazz) {
        super(entityManagerFactory, clazz);
    }
}
