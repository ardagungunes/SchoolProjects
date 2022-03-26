import java.util.ArrayList;

public class User extends  Person {
    private ArrayList<String> filmIds = new ArrayList<String>();
    private ArrayList<Integer> filmRatings = new ArrayList<Integer>();

    public User(String id, String name, String surname, String country) {
        super(id, name, surname, country);
    }

    public ArrayList<String> getFilmIds() {
        return this.filmIds;
    }

    public ArrayList<Integer> getFilmRatings() {
        return this.filmRatings;
    }

    public void addFilmsAndRatings(String id, String rate, ArrayList<Film> arr) {
        this.filmIds.add(id);
        this.filmRatings.add(Integer.parseInt(rate));
        for (Film film : arr) {
            if (film.getId().equals(id)) {
                film.addRating(rate);
                break;
            }
        }

    }

    public void removeRatedFilms(String id, ArrayList<Film> arr) {
        int a = this.filmIds.indexOf(id);
        int rating = this.filmRatings.get(a);
        this.filmIds.remove(a);
        this.filmRatings.remove(a);

        for (Film film : arr) {
            if (film.getId().equals(id)) {
                film.removeRating(rating);
                break;
            }
        }


    }

    public void editRatedFilms(String filmId, String newPoint, ArrayList<Film> arr) {
        int a = this.filmIds.indexOf(filmId);
        int oldRating = this.filmRatings.get(a);
        this.filmRatings.set(a, Integer.parseInt(newPoint));

        for (Film film : arr) {
            if (film.getId().equals(filmId)) {
                film.editRating(oldRating, newPoint);
            }
        }


    }

    public ArrayList<String> getFilmNames(ArrayList<Film> films, ArrayList<String> filmIds) {
        ArrayList<String> filmNames = new ArrayList<>();

        for (String id : filmIds) {
            for (Film film : films) {
                if (film.getId().equals(id)) {
                    filmNames.add(film.getFilmTitle());
                }
            }

        }
        return filmNames;
    }
}

