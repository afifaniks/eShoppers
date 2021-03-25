package me.afifaniks.shoppingcart.service;

import me.afifaniks.shoppingcart.domain.User;
import me.afifaniks.shoppingcart.dto.LoginDTO;
import me.afifaniks.shoppingcart.dto.UserDTO;
import me.afifaniks.shoppingcart.util.UserNotFoundException;

public interface UserService {
    public void saveUser(UserDTO userDTO);
    boolean isNotUniqueUsername(UserDTO userDTO);
    User verifyUser(LoginDTO loginDTO) throws UserNotFoundException;
}
