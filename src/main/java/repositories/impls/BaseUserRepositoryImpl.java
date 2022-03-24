package repositories.impls;

import entities.users.User;
import repositories.interfaces.BaseUserRepository;

import javax.persistence.EntityManagerFactory;

public abstract class BaseUserRepositoryImpl<T extends User> extends BaseRepositoryImpl<T> implements BaseUserRepository<T> {
    public BaseUserRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<T> clazz) {
        super(entityManagerFactory, clazz);
    }

    @Override
    public T readByUsername(String username) {
        try {
            return super
                    .getEntityManagerFactory()
                    .createEntityManager()
                    .createQuery("from " + super.getClazz().getSimpleName() + " u where u.username = :username", getClazz())
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
