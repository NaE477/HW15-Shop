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
    public OrderedProduct ins(OrderedProduct orderedProduct) {
        var manager = super.getEntityManagerFactory().createEntityManager();
        var transaction = manager.getTransaction();
        try {
            transaction.begin();

            var productQty = manager
                    .createQuery("select p.inStock from Product p where p.id = :productId", Integer.class)
                    .setParameter("productId", orderedProduct.getProduct().getId())
                    .getSingleResult();
            manager.createQuery("update Product p set p.inStock = :newStock where p.id = :productId")
                    .setParameter("newStock", productQty - orderedProduct.getQuantity())
                    .setParameter("productId",orderedProduct.getProduct().getId())
                    .executeUpdate();
            manager.persist(orderedProduct);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }
        return orderedProduct;
    }

    @Override
    public List<OrderedProduct> readAllByProduct(Product product) {
        var manager = super.getEntityManagerFactory().createEntityManager();
        try {
            return manager.createQuery("select o from OrderedProduct o where o.product.id = :productId", getClazz())
                    .setParameter("productId", product.getId())
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
