package me.afifaniks.shoppingcart.repository;

import me.afifaniks.shoppingcart.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class UserRepositoryImpl implements UserRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private static Set<User> USERS = new CopyOnWriteArraySet<>();
    @Override
    public void save(User user) {
        USERS.add(user);
        LOGGER.info("User Saved. Total: {}", USERS.size());

        for (User u:
             USERS) {
            LOGGER.info(u.toString());
        };
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return USERS
                .stream()
                .filter(
                        user ->
                                Objects.equals(username, user.getUsername())
                )
                .findFirst();
    }
}
