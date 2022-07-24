package bg.softuni.mychoicepizza.repository;

import bg.softuni.mychoicepizza.model.entity.CartItemEntity;
import bg.softuni.mychoicepizza.model.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    List<CartItemEntity> findByUser_Username(String user_username);

    @Query("UPDATE CartItemEntity c SET c.quantity = ?1 where c.id = ?2 and c.user.id = ?3")
    @Modifying
    void updateQuantity(Integer quantity, Long itemId, Long userId);

    @Query("DELETE from CartItemEntity c where c.user.id=?2 and c.id = ?1")
    @Modifying
    void deleteByUserAndItem(Long itemId, Long userId);

    List<CartItemEntity> findByIdInAndUser_Username(Collection<Long> ids, String user_username);

    void deleteAllByIdInAndUser_Username(Collection<Long> ids, String user_username);
}
