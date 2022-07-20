package bg.softuni.mychoicepizza.repository;

import bg.softuni.mychoicepizza.model.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {

    List<PizzaEntity> findByUser_Username(String user_username);

}
