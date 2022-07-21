package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.model.entity.IngredientEntity;
import bg.softuni.mychoicepizza.model.entity.PizzaEntity;
import bg.softuni.mychoicepizza.model.entity.PriceEntity;
import bg.softuni.mychoicepizza.model.entity.UserEntity;
import bg.softuni.mychoicepizza.model.service.PizzaServiceModel;
import bg.softuni.mychoicepizza.model.view.PizzaViewModel;
import bg.softuni.mychoicepizza.repository.IngredientRepository;
import bg.softuni.mychoicepizza.repository.PizzaRepository;
import bg.softuni.mychoicepizza.repository.PriceRepository;
import bg.softuni.mychoicepizza.repository.UserRepository;
import bg.softuni.mychoicepizza.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    private final PizzaRepository pizzaRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final PriceRepository priceRepository;
    private final ModelMapper modelMapper;

    public CartServiceImpl(PizzaRepository pizzaRepository, IngredientRepository ingredientRepository, UserRepository userRepository, PriceRepository priceRepository, ModelMapper modelMapper) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.priceRepository = priceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addNewCartItem(PizzaServiceModel pizzaServiceModel, String username) {

        //todo
        List<IngredientEntity> ingredients = new ArrayList<>();
        for (String ingredientName : pizzaServiceModel.getIngredients()) {
            IngredientEntity ingredientEntity = ingredientRepository.findByName(ingredientName)
                    .orElseThrow(() -> new IllegalArgumentException());
            ingredients.add(ingredientEntity);
        }

        UserEntity userEntity = userRepository.findByUsername(username).
                orElseThrow(() -> new IllegalArgumentException());

        PriceEntity priceEntity = priceRepository.findByPizzaSize(pizzaServiceModel.getSize());
        BigDecimal basePrice = priceEntity.getBasePrice();
        BigDecimal additionalProductPrice = priceEntity.getAdditionalProductPrice();
        BigDecimal price = basePrice.add(additionalProductPrice.multiply(new BigDecimal(ingredients.size())));

        PizzaEntity pizzaEntity = new PizzaEntity();
        pizzaEntity.setQuantity(1)
                .setBase(pizzaServiceModel.getBase())
                .setSize(pizzaServiceModel.getSize())
                .setIngredients(ingredients)
                .setUser(userEntity)
                .setPrice(price);

        pizzaRepository.save(pizzaEntity);
    }

    @Override
    public List<PizzaViewModel> findAllPizzasByUser(String username) {
        return pizzaRepository.findByUser_Username(username)
                .stream()
                .map(pizzaEntity -> {
                    PizzaViewModel pizzaViewModel = modelMapper.map(pizzaEntity, PizzaViewModel.class);
                    List<String> ingredients = pizzaEntity.getIngredients()
                            .stream()
                            .map(IngredientEntity::getName)
                            .toList();
                    pizzaViewModel.setIngredients(ingredients);
                    return pizzaViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal updateQuantity(Integer quantity, Long itemId, Long userId) {

        pizzaRepository.updateQuantity(quantity, itemId, userId);

        PizzaEntity pizzaEntity = pizzaRepository.findById(itemId).get();

        return pizzaEntity.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public void removeItem(Long itemId, Long userId) {
        pizzaRepository.deleteByUserAndItem(itemId, userId);
    }
}
