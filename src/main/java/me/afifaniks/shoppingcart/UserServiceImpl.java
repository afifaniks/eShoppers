package me.afifaniks.shoppingcart;

import me.afifaniks.shoppingcart.domain.User;
import me.afifaniks.shoppingcart.dto.UserDTO;
import me.afifaniks.shoppingcart.repository.UserRepository;
import me.afifaniks.shoppingcart.service.UserService;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        String encrypted = encryptPassword(userDTO.getPassword());

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encrypted);
        user.setUsername(userDTO.getUsername());

        userRepository.save(user);
    }

    private String encryptPassword(String password) {
        return password;
    }
}
