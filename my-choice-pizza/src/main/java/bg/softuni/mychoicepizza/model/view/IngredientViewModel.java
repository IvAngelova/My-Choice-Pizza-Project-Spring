package bg.softuni.mychoicepizza.model.view;


public class IngredientViewModel {
    private Long id;
    private String name;
    private PictureViewModel picture;

    public IngredientViewModel() {
    }

    public Long getId() {
        return id;
    }

    public IngredientViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public IngredientViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public PictureViewModel getPicture() {
        return picture;
    }

    public IngredientViewModel setPicture(PictureViewModel picture) {
        this.picture = picture;
        return this;
    }
}
