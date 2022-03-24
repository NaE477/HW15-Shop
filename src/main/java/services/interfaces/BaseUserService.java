package services.interfaces;

import entities.users.User;

public interface BaseUserService<T extends User> extends BaseService<T> {
    T findByUsername(String username);
}
