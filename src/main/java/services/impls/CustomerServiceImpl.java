package services.impls;

import entities.users.Customer;
import repositories.interfaces.CustomerRepository;
import services.interfaces.CustomerService;

public class CustomerServiceImpl extends BaseUserServiceImpl<Customer> implements CustomerService {
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }
}
