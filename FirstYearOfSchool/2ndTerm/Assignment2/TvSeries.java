import java.util.ArrayList;
import java.util.Arrays;

public class TvSeries extends Film{
    private ArrayList<String> genres = new ArrayList<String>();
    private ArrayList<String> writers = new ArrayList<String>();
    private String startDate;
    private String endDate;
    private String seasons;
    private String episodes;

    public TvSeries(String id, String filmTitle, String language, String length, String country) {
        super(id, filmTitle, language, length, country);
    }

    public TvSeries(String id, String filmTitle, String language, String length, String country, String startDate, String endDate, String seasons, String episodes) {
        super(id, filmTitle, language, length, country);
        this.startDate = startDate;
        this. endDate = endDate;
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public ArrayList<String> getWriters() {
        return writers;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getSeasons() {
        return seasons;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void generateGenres(String[] arr){
        this.genres.addAll(Arrays.asList(arr));
    }

    public void generateWriters(String[] arr){
        this.writers.addAll(Arrays.asList(arr));
    }
}
