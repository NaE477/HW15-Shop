package services.impls;

import entities.users.User;
import repositories.interfaces.BaseUserRepository;
import services.interfaces.BaseUserService;

public abstract class BaseUserServiceImpl<T extends User> extends BaseServiceImpl<T, BaseUserRepository<T>> implements BaseUserService<T> {
    public BaseUserServiceImpl(BaseUserRepository<T> repository) {
        super(repository);
    }

    @Override
    public T findByUsername(String username) {
        return repository.readByUsername(username);
    }
}
