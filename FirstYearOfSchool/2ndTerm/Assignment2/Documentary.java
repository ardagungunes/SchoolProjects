public class Documentary extends Film{
    private String releaseDate;

    public Documentary(String id, String filmTitle, String language, String length, String country) {
        super(id, filmTitle, language, length, country);
    }

    public Documentary(String id, String filmTitle, String language, String length, String country, String releaseDate) {
        super(id, filmTitle, language, length, country);
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
