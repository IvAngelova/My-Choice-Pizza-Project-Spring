package bg.softuni.mychoicepizza.repository;

import bg.softuni.mychoicepizza.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    Optional<UserEntity> findByUsername(String username);
}
