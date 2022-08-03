package bg.softuni.mychoicepizza.service;

import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;
import bg.softuni.mychoicepizza.model.service.PriceServiceModel;
import bg.softuni.mychoicepizza.model.view.PriceViewModel;

public interface PriceService {
    PriceViewModel findPriceByPizzaSize(SizeEnum sizeEnum);

    PriceViewModel findById(Long id);

    PriceServiceModel editPrice(PriceServiceModel priceServiceModel);
}
