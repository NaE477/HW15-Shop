package repositories.impls;

import entities.base.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import repositories.interfaces.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public abstract class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepository<T> {
    private final EntityManagerFactory entityManagerFactory;
    private final Class<T> clazz;
    private EntityManager entityManager;

    private EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();

        return entityManager;
    }


    @Override
    public T ins(T t) {
        var transaction = getEntityManager().getTransaction();
        try {
            transaction.begin();
            getEntityManager().persist(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T readById(Integer id) {
        try {
            return getEntityManager().find(clazz,id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<T> readAll() {
        try {
            return getEntityManager().createQuery("from " + clazz.getSimpleName(),clazz).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public T update(T t) {
        var transaction = getEntityManager().getTransaction();
        try {
            transaction.begin();
            getEntityManager().merge(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(T t) {
        var transaction = getEntityManager().getTransaction();
        try {
            transaction.begin();
            getEntityManager().remove(getEntityManager().contains(t) ? t : getEntityManager().merge(t));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void truncate(String tableName) {
        var session = getEntityManager().unwrap(Session.class);
        var t = session.beginTransaction();
        try {
            session.createSQLQuery("TRUNCATE " + tableName + " CASCADE;").executeUpdate();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        }
    }
}
