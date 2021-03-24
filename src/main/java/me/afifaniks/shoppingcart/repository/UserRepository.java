package me.afifaniks.shoppingcart.repository;

import me.afifaniks.shoppingcart.domain.User;
import me.afifaniks.shoppingcart.dto.UserDTO;

public interface UserRepository {
    void save(User user);
}
