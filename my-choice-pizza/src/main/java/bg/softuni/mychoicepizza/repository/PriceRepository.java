package bg.softuni.mychoicepizza.repository;

import bg.softuni.mychoicepizza.model.entity.PriceEntity;
import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    PriceEntity findByPizzaSize(SizeEnum pizzaSize);
}
