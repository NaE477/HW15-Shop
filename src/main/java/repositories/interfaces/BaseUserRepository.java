package repositories.interfaces;

import entities.users.User;

public interface BaseUserRepository<T extends User> extends BaseRepository<T> {
    T readByUsername(String username);
}
