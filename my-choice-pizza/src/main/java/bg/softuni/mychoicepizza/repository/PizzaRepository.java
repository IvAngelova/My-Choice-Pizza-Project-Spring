package bg.softuni.mychoicepizza.repository;

import bg.softuni.mychoicepizza.model.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {

    List<PizzaEntity> findByUser_Username(String user_username);

    @Query("UPDATE PizzaEntity p SET p.quantity = ?1 where p.id = ?2 and p.user.id = ?3")
    @Modifying
    void updateQuantity(Integer quantity, Long itemId, Long userId);

    @Query("DELETE from PizzaEntity p where p.user.id=?2 and p.id = ?1")
    @Modifying
    void deleteByUserAndItem(Long itemId, Long userId);

}
