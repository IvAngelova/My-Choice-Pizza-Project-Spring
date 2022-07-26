package bg.softuni.mychoicepizza.repository;

import bg.softuni.mychoicepizza.model.entity.OrderEntity;
import bg.softuni.mychoicepizza.model.entity.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Modifying
    @Query("DELETE from OrderEntity o where o.status = :old")
    void deleteAllOldOrders(@Param("old") OrderStatusEnum orderStatusEnum);


    List<OrderEntity> findAllByStatusOrderByCreatedAsc(OrderStatusEnum status);
}
