package info.upump.animalhome.entity;

/**
 * Created by explo on 15.05.2018.
 */

public class Animal {
    private String name;
    private int id;
    private String soundAnimal;
    private String soundAuthor;
    private String image;

    public Animal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoundAnimal() {
        return soundAnimal;
    }

    public void setSoundAnimal(String soundAnimal) {
        this.soundAnimal = soundAnimal;
    }

    public String getSoundAuthor() {
        return soundAuthor;
    }

    public void setSoundAuthor(String soundAuthor) {
        this.soundAuthor = soundAuthor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
