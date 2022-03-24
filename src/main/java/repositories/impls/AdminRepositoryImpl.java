package repositories.impls;

import entities.users.Admin;
import repositories.interfaces.AdminRepository;

import javax.persistence.EntityManagerFactory;

public class AdminRepositoryImpl extends BaseUserRepositoryImpl<Admin> implements AdminRepository {
    public AdminRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Admin> clazz) {
        super(entityManagerFactory, clazz);
    }
}
