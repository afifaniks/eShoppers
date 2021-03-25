/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/25/2021
 * Time      :     4:31 PM
 **/

package me.afifaniks.shoppingcart.dto;

import javax.validation.constraints.NotEmpty;

public class LoginDTO {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public LoginDTO(@NotEmpty String username, @NotEmpty String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
