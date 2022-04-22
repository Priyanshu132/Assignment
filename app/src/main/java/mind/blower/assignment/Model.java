package mind.blower.assignment;

public class Model {

    private String title,description,imgpath;

    public Model() {
    }

    public Model(String title, String description, String imgpath) {
        this.title = title;
        this.description = description;
        this.imgpath = imgpath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
}
