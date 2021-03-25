package me.afifaniks.shoppingcart.repository;

import me.afifaniks.shoppingcart.domain.User;
import me.afifaniks.shoppingcart.dto.UserDTO;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByUsername(String username);
}
