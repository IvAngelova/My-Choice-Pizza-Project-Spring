package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.exception.ObjectNotFoundException;
import bg.softuni.mychoicepizza.model.entity.*;
import bg.softuni.mychoicepizza.model.service.PizzaServiceModel;
import bg.softuni.mychoicepizza.model.view.PizzaViewModel;
import bg.softuni.mychoicepizza.repository.*;
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
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final PriceRepository priceRepository;
    private final ModelMapper modelMapper;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(IngredientRepository ingredientRepository, UserRepository userRepository, PriceRepository priceRepository, ModelMapper modelMapper, CartItemRepository cartItemRepository) {
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.priceRepository = priceRepository;
        this.modelMapper = modelMapper;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void addNewCartItem(PizzaServiceModel pizzaServiceModel, String username) {

        List<IngredientEntity> ingredients = new ArrayList<>();
        for (String ingredientName : pizzaServiceModel.getIngredients()) {
            IngredientEntity ingredientEntity = ingredientRepository.findByName(ingredientName)
                    .orElseThrow(() -> new ObjectNotFoundException("Не съществува такава съставка!"));
            ingredients.add(ingredientEntity);
        }

        UserEntity userEntity = userRepository.findByUsername(username).
                orElseThrow(() -> new ObjectNotFoundException("Не съществува потребител с такова име!"));

        PriceEntity priceEntity = priceRepository
                .findByPizzaSize(pizzaServiceModel.getSize())
                .orElseThrow(() -> new ObjectNotFoundException("Не съществува такава цена спрямо подадения размер пица!"));
        BigDecimal basePrice = priceEntity.getBasePrice();
        BigDecimal additionalProductPrice = priceEntity.getAdditionalProductPrice();
        BigDecimal price = basePrice.add(additionalProductPrice.multiply(new BigDecimal(ingredients.size())));

        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setQuantity(1)
                .setBase(pizzaServiceModel.getBase())
                .setSize(pizzaServiceModel.getSize())
                .setIngredients(ingredients)
                .setUser(userEntity)
                .setPrice(price);

        cartItemRepository.save(cartItemEntity);
    }

    @Override
    public List<PizzaViewModel> findAllPizzasByUser(String username) {
        return cartItemRepository.findByUser_Username(username)
                .stream()
                .map(cartItemEntity -> {
                    PizzaViewModel pizzaViewModel = modelMapper.map(cartItemEntity, PizzaViewModel.class);
                    List<String> ingredients = cartItemEntity.getIngredients()
                            .stream()
                            .map(IngredientEntity::getName)
                            .toList();
                    pizzaViewModel.setIngredients(ingredients);
                    pizzaViewModel.setUsername(username);
                    return pizzaViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal updateQuantity(Integer quantity, Long itemId, Long userId) {

        cartItemRepository.updateQuantity(quantity, itemId, userId);

        CartItemEntity cartItemEntity = cartItemRepository
                .findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException("Не съществува такъв продукт!"));

        return cartItemEntity.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public void removeItem(Long itemId, Long userId) {
        cartItemRepository.deleteByUserAndItem(itemId, userId);
    }
}
