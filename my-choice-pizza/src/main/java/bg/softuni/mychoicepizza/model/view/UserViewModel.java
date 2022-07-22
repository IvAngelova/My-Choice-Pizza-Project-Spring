package bg.softuni.mychoicepizza.model.view;

import bg.softuni.mychoicepizza.model.entity.enums.RoleNameEnum;

import java.util.List;
import java.util.Set;

public class UserViewModel {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String username;
    private List<String> addresses;
    private Set<RoleNameEnum> roles;

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserViewModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public UserViewModel setAddresses(List<String> addresses) {
        this.addresses = addresses;
        return this;
    }

    public Set<RoleNameEnum> getRoles() {
        return roles;
    }

    public UserViewModel setRoles(Set<RoleNameEnum> roles) {
        this.roles = roles;
        return this;
    }
}
