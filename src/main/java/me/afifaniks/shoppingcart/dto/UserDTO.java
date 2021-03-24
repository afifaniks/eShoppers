package me.afifaniks.shoppingcart.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotEmpty
    @Size(min = 4, max = 32)
    private String username;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String password;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String passwordConfirmed;

    @NotEmpty
    @Size(min = 5, max = 70)
    @Email
    private String email;

    @NotEmpty
    @Size(min = 4, max = 32)
    private String firstName;

    @NotEmpty
    @Size(min = 4, max = 32)
    private String lastName;

    public UserDTO() {
    }

    public UserDTO(@NotEmpty @Size(min = 4, max = 32) String username, @NotEmpty @Size(min = 6, max = 20) String password, @NotEmpty @Size(min = 6, max = 20) String passwordConfirmed, @NotEmpty @Size(min = 5, max = 70) @Email String email, @NotEmpty @Size(min = 4, max = 32) String firstName, @NotEmpty @Size(min = 4, max = 32) String lastName) {
        this.username = username;
        this.password = password;
        this.passwordConfirmed = passwordConfirmed;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty @Size(min = 4, max = 32) String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty @Size(min = 6, max = 20) String password) {
        this.password = password;
    }

    public String getPasswordConfirmed() {
        return passwordConfirmed;
    }

    public void setPasswordConfirmed(@NotEmpty @Size(min = 6, max = 20) String passwordConfirmed) {
        this.passwordConfirmed = passwordConfirmed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( @NotEmpty @Size(min = 5, max = 70) @Email String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty @Size(min = 4, max = 32) String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty @Size(min = 4, max = 32) String lastName) {
        this.lastName = lastName;
    }
}
