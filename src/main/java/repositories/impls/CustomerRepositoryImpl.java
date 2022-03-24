package repositories.impls;

import entities.users.Customer;
import repositories.interfaces.CustomerRepository;

import javax.persistence.EntityManagerFactory;

public class CustomerRepositoryImpl extends BaseUserRepositoryImpl<Customer> implements CustomerRepository {
    public CustomerRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Customer> clazz) {
        super(entityManagerFactory, clazz);
    }
}
