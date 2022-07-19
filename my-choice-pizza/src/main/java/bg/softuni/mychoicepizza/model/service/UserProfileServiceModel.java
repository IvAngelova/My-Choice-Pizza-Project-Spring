package bg.softuni.mychoicepizza.model.service;

import java.util.List;

public class UserProfileServiceModel {
    private Long id;
    //private String username;
    private String phoneNumber;
    private String fullName;

    private String newAddress;
    //private List<String> addresses;

    public Long getId() {
        return id;
    }

    public UserProfileServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserProfileServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfileServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public UserProfileServiceModel setNewAddress(String newAddress) {
        this.newAddress = newAddress;
        return this;
    }
}
