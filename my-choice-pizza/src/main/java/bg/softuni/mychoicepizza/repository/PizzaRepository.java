package bg.softuni.mychoicepizza.repository;

import bg.softuni.mychoicepizza.model.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {

    void deleteAllByIdIn(Collection<Long> id);

    @Query(nativeQuery = true, value = "SELECT p.id from pizzas p " +
            "where p.id not in (SELECT pizzas_id from orders_pizzas);")
    List<Long> getAllPizzaIdsFromOldOrders();


}
