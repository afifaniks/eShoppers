/**
 * Created by IntelliJ IDEA.
 * Author    :     Afif Al Mamun
 * Web       :     https://afifaniks.me
 * Date      :     3/28/2021
 * Time      :     4:02 PM
 **/

package me.afifaniks.shoppingcart.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ShippingAddressDTO {
    @NotEmpty
    private String address;
    private String address2;

    @NotEmpty
    private String state;

    @NotEmpty
    private String zip;

    @NotEmpty
    private String country;

    @NotEmpty
    @Pattern(regexp = "(?:\\+88|01)?\\d{11}", message = "Must be a valid Bangladeshi number")
    private String mobileNumber;

    public ShippingAddressDTO() {
    }

    public ShippingAddressDTO(@NotEmpty String address, String address2, @NotEmpty String state, @NotEmpty String zip, @NotEmpty String country, @NotEmpty @Pattern(regexp = "(?:\\+88|01)?\\d{11}", message = "Must be a valid Bangladeshi number") String mobileNumber) {
        this.address = address;
        this.address2 = address2;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "ShippingAddressDTO{" +
                "address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
