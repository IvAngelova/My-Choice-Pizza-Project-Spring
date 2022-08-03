package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.exception.ObjectNotFoundException;
import bg.softuni.mychoicepizza.model.entity.PriceEntity;
import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;
import bg.softuni.mychoicepizza.model.service.PriceServiceModel;
import bg.softuni.mychoicepizza.model.view.PriceViewModel;
import bg.softuni.mychoicepizza.repository.PriceRepository;
import bg.softuni.mychoicepizza.service.PriceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService {
    private final ModelMapper modelMapper;
    private final PriceRepository priceRepository;

    public PriceServiceImpl(ModelMapper modelMapper, PriceRepository priceRepository) {
        this.modelMapper = modelMapper;
        this.priceRepository = priceRepository;
    }

    @Override
    public PriceViewModel findPriceByPizzaSize(SizeEnum sizeEnum) {

      return   priceRepository.findByPizzaSize(sizeEnum)
                .map(priceEntity -> modelMapper.map(priceEntity, PriceViewModel.class))
                .orElseThrow(() -> new ObjectNotFoundException("Не съществува такава цена!"));


    }

    @Override
    public PriceViewModel findById(Long id) {
        return priceRepository.findById(id)
                .map(priceEntity -> modelMapper.map(priceEntity, PriceViewModel.class))
                .orElseThrow(() -> new ObjectNotFoundException("Не съществува цена с това id!"));
    }

    @Override
    public PriceServiceModel editPrice(PriceServiceModel priceServiceModel) {
        PriceEntity priceEntity = priceRepository.findById(priceServiceModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Не съществува цена с това id!"));

        priceEntity.setBasePrice(priceServiceModel.getBasePrice())
                .setAdditionalProductPrice(priceServiceModel.getAdditionalProductPrice());

        return modelMapper.map(priceRepository.save(priceEntity),PriceServiceModel.class);
    }
}
