package bg.softuni.mychoicepizza.model.binding;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


public class UserProfileBindingModel {
    private Long id;

    private String username;

    @NotBlank(message = "Полето е задължително!")
    @Size(min = 4, max = 30, message = "Имената трябва да са между 4 и 30 символа!")
    private String fullName;
    @NotBlank(message = "Полето е задължително!")
    private String phoneNumber;
    @NotBlank(message = "Полето е задължително!")
    @Size(min = 4, max = 60, message = "Адресът трябва да е с дължина до 60 символа!")
    private String address;


    public UserProfileBindingModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfileBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserProfileBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserProfileBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserProfileBindingModel setAddress(String address) {
        this.address = address;
        return this;
    }
}
