package bg.softuni.mychoicepizza.repository;

import bg.softuni.mychoicepizza.model.entity.IngredientEntity;
import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

    List<IngredientEntity> findAllByCategory_Name(CategoryNameEnum category_name);

    Optional<IngredientEntity> findByName(String name);
}
