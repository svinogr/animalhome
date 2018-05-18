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
    private String imagePrev;
    private String question;
    private String word;

    public Animal() {
    }

    public String getImagePrev() {
        return imagePrev;
    }

    public void setImagePrev(String imagePrev) {
        this.imagePrev = imagePrev;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", soundAnimal='" + soundAnimal + '\'' +
                ", soundAuthor='" + soundAuthor + '\'' +
                ", image='" + image + '\'' +
                ", imagePrev='" + imagePrev + '\'' +
                ", question='" + question + '\'' +
                ", word='" + word + '\'' +
                '}';
    }
}
