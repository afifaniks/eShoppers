package me.afifaniks.shoppingcart.service;

import me.afifaniks.shoppingcart.domain.User;
import me.afifaniks.shoppingcart.dto.UserDTO;
import me.afifaniks.shoppingcart.repository.UserRepository;
import me.afifaniks.shoppingcart.service.UserService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    @Override
    public boolean isNotUniqueUsername(UserDTO userDTO) {
        return userRepository.findByUsername(userDTO.getUsername()).isPresent();
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8)
            );

            return bytesToHex(bytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b: bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
