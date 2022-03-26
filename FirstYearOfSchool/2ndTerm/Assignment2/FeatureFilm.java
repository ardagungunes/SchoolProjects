import java.util.ArrayList;
import java.util.Arrays;

public class FeatureFilm extends Film{
    private ArrayList<String> genres = new ArrayList<String>();
    private String releaseDate;
    private ArrayList<String> writers = new ArrayList<String>();
    private String budget;

    public FeatureFilm(String id, String filmTitle, String language, String length, String country) {
        super(id, filmTitle, language, length, country);
    }

    public FeatureFilm(String id, String filmTitle, String language, String length, String country, String releaseDate, String budget) {
        super(id, filmTitle, language, length, country);
        this.releaseDate = releaseDate;
        this.budget = budget;
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
