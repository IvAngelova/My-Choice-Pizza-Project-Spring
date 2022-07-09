package bg.softuni.mychoicepizza.model.binding;

import bg.softuni.mychoicepizza.model.validator.UniqueUserName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {
    @NotBlank(message = "Полето е задължително!")
    @Size(min = 4, max = 30, message = "Имената трябва да са между 4 и 30 символа!")
    private String fullName;
    @NotBlank(message = "Полето е задължително!")
    private String phoneNumber;
    @UniqueUserName
    @NotBlank(message = "Полето е задължително!")
    @Size(min = 4, max = 20, message = "Потребителското име трябва да е между 4 и 20 символа!")
    private String username;
    @NotNull(message = "Полето е задължително!")
    @Size(min = 4, max = 20, message = "Паролата трябва да е между 4 и 20 символа!")
    private String password;
    @NotNull(message = "Полето е задължително!")
    @Size(min = 4, max = 20, message = "Паролата трябва да е между 4 и 20 символа!")
    private String confirmPassword;
    @NotNull(message = "Полето е задължително!")
    @Size(min = 4, max = 60, message = "Адресът трябва да е между 4 и 60 символа!")
    private String address;

    public UserRegisterBindingModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegisterBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegisterBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserRegisterBindingModel setAddress(String address) {
        this.address = address;
        return this;
    }
}
