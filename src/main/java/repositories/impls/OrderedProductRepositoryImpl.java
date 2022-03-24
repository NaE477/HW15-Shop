package repositories.impls;

import entities.products.OrderedProduct;
import entities.products.Product;
import repositories.interfaces.OrderedProductRepository;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class OrderedProductRepositoryImpl extends BaseRepositoryImpl<OrderedProduct> implements OrderedProductRepository {
    public OrderedProductRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<OrderedProduct> clazz) {
        super(entityManagerFactory, clazz);
    }

    @Override
    public List<OrderedProduct> readAllByProduct(Product product) {
        var manager = super.getEntityManagerFactory().createEntityManager();
        try {
            return manager.createQuery("select o from OrderedProduct o where o.product.id = :productId",getClazz())
                    .setParameter("productId",product.getId())
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
