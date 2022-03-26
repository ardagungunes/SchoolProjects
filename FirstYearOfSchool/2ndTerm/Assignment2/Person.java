public class Person {
    // Every field is private because every people has fixed attributes;
    // I put getters but didn't put setters as I said above.

    private String id;
    private String name;
    private String surname;
    private String country;

    public Person(String id, String name, String surname, String country){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getCountry() {
        return this.country;
    }
}
