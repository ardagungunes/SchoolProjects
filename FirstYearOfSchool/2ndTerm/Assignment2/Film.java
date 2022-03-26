import java.util.ArrayList;
import java.util.Arrays;

public class Film implements Comparable<Film>{
    private String id;
    private String filmTitle;
    private String language;
    private ArrayList<String> directorIds = new ArrayList<String>();
    private String length;
    private String country;
    private ArrayList<String> performersId = new ArrayList<String>();
    private ArrayList<Integer> ratings = new ArrayList<Integer>();



    public Film(String id, String filmTitle, String language,  String length, String country) {
        this.id = id;
        this.filmTitle = filmTitle;
        this.language = language;

        this.length = length;
        this.country = country;


    }

    public void addRating(String rating){
        this.ratings.add(Integer.parseInt(rating));
    }

    public void removeRating(int rating){

        int position = this.ratings.indexOf(rating);

        this.ratings.remove(position);


        //this.ratings.remove(0);
    }

    public void editRating(int oldRating,String newRating){
        int position = this.ratings.indexOf(oldRating);
        this.ratings.set(position,Integer.parseInt(newRating));
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public String getId() {
        return id;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public String getLanguage() {
        return language;
    }

    public ArrayList<String> getDirectorIds() {
        return directorIds;
    }

    public String getLength() {
        return length;
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<String> getPerformersId() {
        return performersId;
    }

    public void generateDirectorsList(String[] arr){
        this.directorIds.addAll(Arrays.asList(arr));
    }

    public void generatePerformersList(String[] arr){
        this.performersId.addAll(Arrays.asList(arr));
    }

    public double getAverageRating(){
        int ratedUser = this.getRatings().size();
        double sum = 0.0;

        for(int i : this.getRatings()){
            sum += i;
        }

        if(ratedUser == 0){
            return 0.0;
        }return sum / (double) ratedUser;
    }

    public int compareTo(Film film){
        return (Double.compare(this.getAverageRating(), film.getAverageRating()));
    }

}
