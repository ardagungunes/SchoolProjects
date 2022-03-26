public class Actor extends Performer {
    private String height;

    public Actor(String id, String name, String surname, String country) {
        super(id, name, surname, country);
    }

    public Actor(String id, String name, String surname, String country, String height) {
        super(id, name, surname, country);
        this.height = height;
    }

    public String getHeight() {
        return this.height;
    }
}
