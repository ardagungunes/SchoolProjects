import java.util.ArrayList;
import java.util.Arrays;

public class ShortFilm extends Film{
    private ArrayList<String> genres = new ArrayList<String>();
    private String releaseDate;
    private ArrayList<String> writers = new ArrayList<String>();

    public ShortFilm(String id, String filmTitle, String language, String length, String country) {
        super(id, filmTitle, language, length, country);
    }

    public ShortFilm(String id, String filmTitle, String language, String length, String country, String releaseDate) {
        super(id, filmTitle, language, length, country);
        this.releaseDate = releaseDate;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public ArrayList<String> getWriters() {
        return writers;
    }

    public void generateGenres(String[] arr){
        this.genres.addAll(Arrays.asList(arr));
    }

    public void generateWriters(String[] arr){
        this.writers.addAll(Arrays.asList(arr));
    }
}
