package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.exception.ObjectNotFoundException;
import bg.softuni.mychoicepizza.model.entity.IngredientEntity;
import bg.softuni.mychoicepizza.model.entity.PictureEntity;
import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;
import bg.softuni.mychoicepizza.model.service.IngredientServiceModel;
import bg.softuni.mychoicepizza.model.view.IngredientViewModel;
import bg.softuni.mychoicepizza.model.view.PictureViewModel;
import bg.softuni.mychoicepizza.repository.IngredientRepository;
import bg.softuni.mychoicepizza.repository.PictureRepository;
import bg.softuni.mychoicepizza.service.CategoryService;
import bg.softuni.mychoicepizza.service.CloudinaryImage;
import bg.softuni.mychoicepizza.service.CloudinaryService;
import bg.softuni.mychoicepizza.service.IngredientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, CloudinaryService cloudinaryService, PictureRepository pictureRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.ingredientRepository = ingredientRepository;
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addIngredient(IngredientServiceModel ingredientServiceModel) throws IOException {


        IngredientEntity ingredientEntity = new IngredientEntity()
                .setName(ingredientServiceModel.getName())
                .setCategory(categoryService.findCategoryByName(ingredientServiceModel.getCategory()));

        ingredientRepository.save(ingredientEntity);

        PictureEntity pictureEntity = createPictureEntity(ingredientServiceModel.getPicture(),
                ingredientServiceModel.getName());
        pictureEntity.setIngredient(ingredientEntity);

        pictureRepository.save(pictureEntity);
    }

    @Override
    public List<IngredientViewModel> findAllIngredientsByCategory(CategoryNameEnum categoryNameEnum) {

        return ingredientRepository.findAllByCategory_Name(categoryNameEnum)
                .stream()
                .map(this::mapToIngredientViewModel)
                .collect(Collectors.toList());


    }

    private IngredientViewModel mapToIngredientViewModel(IngredientEntity ingredientEntity) {
        IngredientViewModel ingredientViewModel = modelMapper.map(ingredientEntity, IngredientViewModel.class);
        PictureViewModel pictureViewModel = new PictureViewModel();
        pictureViewModel.setTitle(ingredientEntity.getPicture().getTitle())
                .setUrl(ingredientEntity.getPicture().getUrl());
        ingredientViewModel.setPicture(pictureViewModel);
        return ingredientViewModel;
    }

    @Override
    public void deleteIngredientById(Long id) {
        Optional<IngredientEntity> ingredientOpt = ingredientRepository.findById(id);
        if (ingredientOpt.isEmpty()) {
            throw new ObjectNotFoundException("Не съществува такава съставка!");
        }
        String publicId = ingredientOpt.get().getPicture().getPublicId();
        if (cloudinaryService.delete(publicId)) {
            pictureRepository.deleteByPublicId(publicId);
            ingredientRepository.deleteById(id);
        }
    }

    private PictureEntity createPictureEntity(MultipartFile file, String ingredientName) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);

        return new PictureEntity().
                setPublicId(uploaded.getPublicId()).
                setTitle(ingredientName).
                setUrl(uploaded.getUrl());
    }
}
