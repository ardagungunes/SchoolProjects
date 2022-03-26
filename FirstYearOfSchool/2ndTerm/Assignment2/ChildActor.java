public class ChildActor extends Performer {
    private String age;

    public ChildActor(String id, String name, String surname, String country) {
        super(id, name, surname, country);
    }

    public ChildActor(String id, String name, String surname, String country, String age) {
        super(id, name, surname, country);
        this.age = age;
    }

    public String getAge() {
        return this.age;
    }
}
