package me.afifaniks.shoppingcart.service;

import me.afifaniks.shoppingcart.dto.UserDTO;

public interface UserService {
    public void saveUser(UserDTO userDTO);
    boolean isNotUniqueUsername(UserDTO userDTO);
}
