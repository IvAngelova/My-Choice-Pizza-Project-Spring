package bg.softuni.mychoicepizza.model.entity;

import bg.softuni.mychoicepizza.model.entity.enums.RoleNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleNameEnum role;

    public UserRoleEntity() {
    }

    public RoleNameEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(RoleNameEnum role) {
        this.role = role;
        return this;
    }
}
