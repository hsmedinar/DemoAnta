package tawa.stbig.com.demotawa.object;

import java.io.Serializable;

/**
 * Created by root on 21/01/16.
 */
public class ImageObject implements Serializable {

    int id;
    String title;
    String description;
    String image;
    String created_at;
    String updated_at;

    public ImageObject(int id, String title, String description, String image, String created_at, String updated_at){
        this.id=id;
        this.title=title;
        this.description=description;
        this.image=image;
        this.created_at=created_at;
        this.updated_at=updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
