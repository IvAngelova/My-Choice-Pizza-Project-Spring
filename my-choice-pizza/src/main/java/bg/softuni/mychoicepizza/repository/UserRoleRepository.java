package bg.softuni.mychoicepizza.repository;

import bg.softuni.mychoicepizza.model.entity.UserRoleEntity;
import bg.softuni.mychoicepizza.model.entity.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRole(RoleNameEnum nameEnum);
}
