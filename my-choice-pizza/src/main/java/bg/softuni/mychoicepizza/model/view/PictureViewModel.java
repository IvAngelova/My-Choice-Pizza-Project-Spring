package bg.softuni.mychoicepizza.model.view;

public class PictureViewModel {
    private String title;
    private String url;

    public PictureViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public PictureViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureViewModel setUrl(String url) {
        this.url = url;
        return this;
    }
}
