import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String peopleFile = args[0];
        String filmsFile = args[1];
        String commandsFile = args[2];
        String outFile = args[3];

        // I decided to hold the data in different arraylists because some fields are unique so we have to hold
        // them in different data collectors.

        // People data
        ArrayList<Performer> performerArrayList = new ArrayList<Performer>();
        ArrayList<User> userArrayList = new ArrayList<User>();
        ArrayList<Actor> actorArrayList = new ArrayList<Actor>();
        ArrayList<ChildActor> childActorArrayList = new ArrayList<ChildActor>();
        ArrayList<StuntPerformer> stuntPerformerArrayList = new ArrayList<StuntPerformer>();
        ArrayList<Director> directorArrayList = new ArrayList<Director>();
        ArrayList<Writer> writerArrayList = new ArrayList<Writer>();

        createArtistsArrays(peopleFile,userArrayList, actorArrayList, childActorArrayList, stuntPerformerArrayList, directorArrayList, writerArrayList, performerArrayList);




        // Films data
        /* In films we may want to access the rating of specified film so I decided to make a general collection except from
         different collectors. */

        ArrayList<Film> filmArrayList = new ArrayList<Film>();
        ArrayList<FeatureFilm> featureFilmArrayList = new ArrayList<FeatureFilm>();
        ArrayList<ShortFilm> shortFilmArrayList = new ArrayList<ShortFilm>();
        ArrayList<Documentary> documentaryArrayList = new ArrayList<Documentary>();
        ArrayList<TvSeries> tvSeriesArrayList = new ArrayList<TvSeries>();

        createFilmsArrays(filmsFile,filmArrayList, featureFilmArrayList, shortFilmArrayList, documentaryArrayList, tvSeriesArrayList);


        Scanner commandFile = new Scanner(new File(commandsFile));
        File outputFile = new File(outFile);
        FileWriter myWriter = new FileWriter(outFile);

        while (commandFile.hasNextLine()) {
            String line = commandFile.nextLine();
            String[] commandParts = line.split("\t");


            if (commandParts[0].equals("RATE")) {
                String userId = commandParts[1];
                String filmId = commandParts[2];
                String ratingPoint = commandParts[3];
                Film instanceFilm = null; // to write the type of the film getClass().getSimpleName()

                myWriter.write("RATE\t" + userId + "\t" + filmId + "\t" + ratingPoint);
                myWriter.write("\n\n");

                // These are the flags to check whether the object exists or not.
                int flagForUser = 0;
                int flagForFilm = 0;
                int flagForIllegalRate = 0;

                for (User user : userArrayList) {
                    if (user.getId().equals(userId)) {
                        if (user.getFilmIds().contains(filmId)) {
                            flagForIllegalRate++;
                        }
                        flagForUser++;
                        break;
                    }
                }
                for (Film film : filmArrayList) {
                    if (film.getId().equals(filmId)) {
                        instanceFilm = film;
                        flagForFilm++;
                        break;
                    }
                }
                if (flagForIllegalRate == 1) {
                    myWriter.write("This film was earlier rated" + "\n");
                    myWriter.write("-----------------------------------------------------------------------------------------------------\n");
                    continue;

                }

                if (flagForUser == 1 && flagForFilm == 1 ) {
                    if(Integer.parseInt(ratingPoint) >= 1 && Integer.parseInt(ratingPoint) <= 10) {
                        for (User user : userArrayList) {
                            if (user.getId().equals(userId)) {
                                user.addFilmsAndRatings(filmId, ratingPoint, filmArrayList);
                                myWriter.write("Film rated successfully\nFilm type: " + instanceFilm.getClass().getSimpleName() + "\nFilm title: " +
                                        instanceFilm.getFilmTitle());

                            }
                        }
                    }else {
                        System.out.println("Rating point should be 1-11 integer");
                    }
                } else {
                    myWriter.write("Command failed\nUser ID: " + userId + "\nFilm ID: " + filmId);
                }
                //myWriter.write("\n\n");
                //myWriter.write("-----------------------------------------------------------------------------------------------------\n");
            } else if (commandParts[0].equals("ADD")) {
                myWriter.write(commandParts[0] + "\t" + commandParts[1] + "\t" + commandParts[2] + "\t" + commandParts[3] + "\t"
                        + commandParts[4] + "\t" + commandParts[5] + "\t" + commandParts[6] + "\t" + commandParts[7] + "\t" +
                        commandParts[8] + "\t" + commandParts[9] + "\t" + commandParts[10] + "\t" + commandParts[11] + "\t" + commandParts[12] + "\n\n");

                if (isFilmIdDirectorPerformerWriterInSystem(commandParts, featureFilmArrayList, directorArrayList,
                        performerArrayList, writerArrayList)) {
                    myWriter.write("Feature film added successfully\nFilm ID: " + commandParts[2] + "\nFilmTitle: " + commandParts[3]);


                    featureFilmArrayList.add(new FeatureFilm(commandParts[2], commandParts[3], commandParts[4], commandParts[6], commandParts[7],
                            commandParts[10], commandParts[12]));
                    int lastElementIndex = featureFilmArrayList.size() - 1;
                    featureFilmArrayList.get(lastElementIndex).generateDirectorsList(commandParts[5].split(","));
                    featureFilmArrayList.get(lastElementIndex).generatePerformersList(commandParts[8].split(","));
                    featureFilmArrayList.get(lastElementIndex).generateGenres(commandParts[9].split(","));
                    featureFilmArrayList.get(lastElementIndex).generateWriters(commandParts[11].split(","));

                    filmArrayList.add(new FeatureFilm(commandParts[2], commandParts[3], commandParts[4], commandParts[6], commandParts[7]));
                    int lastElementF = filmArrayList.size() - 1;
                    filmArrayList.get(lastElementF).generateDirectorsList(commandParts[5].split(","));
                    filmArrayList.get(lastElementF).generatePerformersList(commandParts[8].split(","));


                } else {
                    myWriter.write("Command Failed\nFilm ID: " + commandParts[2] + "\nFilmTitle: " + commandParts[3]);
                }
                //myWriter.write("\n\n");
                //myWriter.write("-----------------------------------------------------------------------------------------------------\n");
            } else if (commandParts[0].equals("VIEWFILM")) {
                String filmId = commandParts[1];
                String filmType = getFilmType(filmArrayList, filmId);
                myWriter.write("VIEWFILM\t" + filmId + "\n\n");


                if (filmType.equals("")) {
                    myWriter.write("Command Failed\nFilm ID: " + filmId);
                } else {

                    if (filmType.equals("FeatureFilm")) {
                        FeatureFilm film = getSpecifiedFeatureFilm(featureFilmArrayList, filmId);
                        Film generalFilm = null;
                        for (Film film1 : filmArrayList) {
                            if (film1.getId().equals(filmId)) {
                                generalFilm = film1;
                            }
                        }


                        assert film != null;

                        assert generalFilm != null;
                        int ratedUser = generalFilm.getRatings().size();
                        ArrayList<String> writerNames = getWriterNames(film, writerArrayList);
                        ArrayList<String> directorNames = getDirectorNames(film, directorArrayList);
                        ArrayList<String> performerNames = getStarNames(film, performerArrayList);
                        ArrayList<String> genres = film.getGenres();


                        myWriter.write(film.getFilmTitle() + " (" + film.getReleaseDate().split("\\.")[2] + ")\n");
                        myWriter.write(Arrays.toString(genres.toArray()).replace("[", "").replace("]", "") + "\n");
                        myWriter.write("Writers: " + Arrays.toString(writerNames.toArray()).replace("[", "").replace("]", "") + "\n");
                        myWriter.write("Directors: " + Arrays.toString(directorNames.toArray()).replace("[", "").replace("]", "") + "\n");
                        myWriter.write("Stars: " + Arrays.toString(performerNames.toArray()).replace("[", "").replace("]", "") + "\n");

                        if (ratedUser == 0) {
                            myWriter.write("Awaiting for votes");
                        } else {

                            myWriter.write("Ratings: " + Double.toString(generalFilm.getAverageRating()).substring(0,3).replace(".", ",").replace(",0","").replace("0,","").replace("[","").replace("]","") + "/10 from " + ratedUser + " users");
                        }

                    } else if (filmType.equals("ShortFilm")) {
                        ShortFilm film = getSpecifiedShortFilm(shortFilmArrayList, filmId);
                        Film generalFilm = getGeneralFilm(filmId, filmArrayList);

                        assert film != null;
                        assert generalFilm != null;
                        int ratedUser = generalFilm.getRatings().size();
                        ArrayList<String> writerNames = getWriterNames(film, writerArrayList);
                        ArrayList<String> directorNames = getDirectorNames(film, directorArrayList);
                        ArrayList<String> performerNames = getStarNames(film, performerArrayList);
                        ArrayList<String> genres = film.getGenres();

                        myWriter.write(film.getFilmTitle() + " (" + film.getReleaseDate().split("\\.")[2] + ")\n");
                        myWriter.write(Arrays.toString(genres.toArray()).replace("[", "").replace("]", "") + "\n");
                        myWriter.write("Writers: " + Arrays.toString(writerNames.toArray()).replace("[", "").replace("]", "") + "\n");
                        myWriter.write("Directors: " + Arrays.toString(directorNames.toArray()).replace("[", "").replace("]", "") + "\n");
                        myWriter.write("Stars: " + Arrays.toString(performerNames.toArray()).replace("[", "").replace("]", "") + "\n");

                        if (ratedUser == 0) {
                            myWriter.write("Awaiting for votes");
                        } else {

                            myWriter.write("Ratings: " + Double.toString(generalFilm.getAverageRating()).substring(0,3).replace(".", ",").replace(",0","").replace("0,","").replace("[","").replace("]","") + "/10 from " + ratedUser + " users");
                        }

                    } else if (filmType.equals("Documentary")) {
                        Documentary film = getSpecifiedDocumentary(documentaryArrayList, filmId);
                        Film generalFilm = getGeneralFilm(filmId, filmArrayList);

                        assert film != null;
                        assert generalFilm != null;
                        int ratedUser = generalFilm.getRatings().size();

                        ArrayList<String> directorNames = getDirectorNames(film, directorArrayList);
                        ArrayList<String> performerNames = getStarNames(film, performerArrayList);


                        myWriter.write(film.getFilmTitle() + " (" + film.getReleaseDate().split("\\.")[2] + ")\n");


                        myWriter.write("Directors: " + Arrays.toString(directorNames.toArray()).replace("[", "").replace("]", "") + "\n");
                        myWriter.write("Stars: " + Arrays.toString(performerNames.toArray()).replace("[", "").replace("]", "") + "\n");

                        if (ratedUser == 0) {
                            myWriter.write("Awaiting for votes");
                        } else {

                            myWriter.write("Ratings: " + Double.toString(generalFilm.getAverageRating()).substring(0,3).replace(".", ",").replace(",0","").replace("0,","").replace("[","").replace("]","") + "/10 from " + ratedUser + " users");
                        }
                    } else if (filmType.equals("TvSeries")) {
                        TvSeries film = getSpecifiedTvSeries(tvSeriesArrayList, filmId);
                        Film generalFilm = getGeneralFilm(filmId, filmArrayList);

                        assert film != null;
                        assert generalFilm != null;
                        int ratedUser = generalFilm.getRatings().size();

                        ArrayList<String> writerNames = getWriterNames(film, writerArrayList);
                        ArrayList<String> directorNames = getDirectorNames(film, directorArrayList);
                        ArrayList<String> performerNames = getStarNames(film, performerArrayList);
                        ArrayList<String> genres = film.getGenres();

                        String startDate = film.getStartDate().split("\\.")[2];

                        String endDate = film.getEndDate().split("\\.")[2];

                        myWriter.write(film.getFilmTitle() + " (" + startDate + "-" + endDate + ")\n");
                        myWriter.write(film.getSeasons() + " seasons, " + film.getEpisodes() + " episodes\n");
                        myWriter.write(Arrays.toString(genres.toArray()).replace("[", "").replace("]", "") + "\n");
                        myWriter.write("Writers: " + Arrays.toString(writerNames.toArray()).replace("[", "").replace("]", "") + "\n");
                        myWriter.write("Directors: " + Arrays.toString(directorNames.toArray()).replace("[", "").replace("]", "") + "\n");
                        myWriter.write("Stars: " + Arrays.toString(performerNames.toArray()).replace("[", "").replace("]", "") + "\n");

                        if (ratedUser == 0) {
                            myWriter.write("Awaiting for votes");
                        } else {

                            myWriter.write("Ratings: " + Double.toString(generalFilm.getAverageRating()).substring(0,3).replace(".", ",").replace(",0","").replace("0,","").replace("[","").replace("]","") + "/10 from " + ratedUser + " users");
                        }

                    }
                }
            } else if (commandParts[1].equals("USER")) {
                myWriter.write("LIST\tUSER\t" + commandParts[2] + "\t" + "RATES\n\n");
                User user = getSpecifiedUser(commandParts[2], userArrayList);

                if (user == null) {
                    myWriter.write("Command Failed\nUser ID: " + commandParts[2]);
                } else {
                    if (user.getFilmRatings().size() == 0) {
                        myWriter.write("There is not ratings so far");
                    } else {
                        //assert user != null;
                        ArrayList<String> filmIds = user.getFilmIds();
                        ArrayList<String> filmNames = user.getFilmNames(filmArrayList, filmIds);
                        int index = 0;
                        for (String filmName : filmNames) {
                            myWriter.write(filmName + ": " + user.getFilmRatings().get(index) + "\n");
                            index++;
                        }

                    }
                }


            } else if (commandParts[0].equals("EDIT")) {
                myWriter.write("EDIT\tRATE\t" + commandParts[2] + "\t" + commandParts[3] + "\t" + commandParts[4] + "\n\n");

                User user = getSpecifiedUser(commandParts[2], userArrayList);
                Film film = getGeneralFilm(commandParts[3], filmArrayList);

                if (user == null || film == null) {
                    myWriter.write("Command Failed\nUser ID: " + commandParts[2] + "\nFilm ID: " + commandParts[3]);
                } else {
                    if (!(user.getFilmIds().contains(commandParts[3]))) {
                        myWriter.write("Command Failed\nUser ID: " + commandParts[2] + "\nFilm ID: " + commandParts[3]);
                    } else {
                        myWriter.write("New ratings done successfully\nFilm Title: " + film.getFilmTitle() + "\nYour rating: " +
                                commandParts[4]);
                        user.editRatedFilms(commandParts[3], commandParts[4], filmArrayList);
                    }
                }


            } else if (commandParts[0].equals("REMOVE")) {
                myWriter.write("REMOVE\tRATE\t" + commandParts[2] + "\t" + commandParts[3] + "\n\n");

                User user = getSpecifiedUser(commandParts[2], userArrayList);
                Film film = getGeneralFilm(commandParts[3], filmArrayList);

                if (user == null || film == null) {
                    myWriter.write("Command Failed\nUser ID: " + commandParts[2] + "\nFilm ID: " + commandParts[3]);
                } else {
                    if (!(user.getFilmIds().contains(commandParts[3]))) {
                        myWriter.write("Command Failed\nUser ID: " + commandParts[2] + "\nFilm ID: " + commandParts[3]);
                    } else {
                        myWriter.write("Your film rating was removed successfully\nFilm title: " + film.getFilmTitle());
                        user.removeRatedFilms(commandParts[3], filmArrayList);
                    }

                }
            } else if (commandParts[1].equals("FILM")) {
                myWriter.write("LIST\tFILM\tSERIES\n\n");

                if (tvSeriesArrayList.size() == 0) {
                    myWriter.write("No result");
                } else {
                    for (TvSeries tvSeries : tvSeriesArrayList) {
                        myWriter.write(tvSeries.getFilmTitle() + " (" + tvSeries.getStartDate().split("\\.")[2] +
                                "-" + tvSeries.getEndDate().split("\\.")[2] + ")\n" + tvSeries.getSeasons() + " seasons and " +
                                tvSeries.getEpisodes() + " episodes\n\n");
                    }
                }
            } else if (commandParts[1].equals("FILMS")) {
                if (commandParts[3].equals("COUNTRY")) {
                    myWriter.write("LIST\tFILMS\tBY\tCOUNTRY\t" + commandParts[4] + "\n\n");
                    String country = commandParts[4];
                    if(getFilmByCountry(country, filmArrayList).size() == 0){
                        myWriter.write("No result");
                    }else{
                        for(Film film : getFilmByCountry(country, filmArrayList)){
                            myWriter.write("Film title: " + film.getFilmTitle() + "\n" + film.getLength() + " min\nLanguage: " +
                                    film.getLanguage() + "\n\n" );
                        }
                    }

                }else if(commandParts[3].equals("RATE")) {
                    myWriter.write("LIST\tFILMS\tBY\tRATE\tDEGREE\n\n");
                    myWriter.write("FeatureFilm:\n");
                    String filmId = "";

                    if (filmToFeature(filmArrayList).size() == 0) {
                        myWriter.write("No result\n\n");
                    }else{

                    for (Film film : filmToFeature(filmArrayList)) {
                        filmId = film.getId();
                        FeatureFilm specifiedFeatureFilm = getSpecifiedFeatureFilm(featureFilmArrayList, filmId);
                        assert specifiedFeatureFilm != null;
                        String date = specifiedFeatureFilm.getReleaseDate().split("\\.")[2];



                        myWriter.write(film.getFilmTitle() + " (" + date + ") Ratings: " + Double.toString(film.getAverageRating()).replace(".", ",").substring(0, 3).replace(",0", "").replace("0,", "0") + "/10 from " + film.getRatings().size() + " users\n");
                    }

                }

                    myWriter.write("\nShortFilm:\n");

                    if(filmToShort(filmArrayList).size() == 0){
                        myWriter.write("No result\n\n");
                    }else{
                        for (Film film : filmToShort(filmArrayList)) {
                            filmId = film.getId();
                            ShortFilm specifiedFeatureFilm = getSpecifiedShortFilm(shortFilmArrayList, filmId);
                            assert specifiedFeatureFilm != null;
                            String date = specifiedFeatureFilm.getReleaseDate().split("\\.")[2];



                            myWriter.write(film.getFilmTitle() + " (" + date + ") Ratings: " +
                                    Double.toString(film.getAverageRating()).replace(".", ",").substring(0, 3).replace(",0", "").replace("0,", "0") +
                                    "/10 from " +
                                    film.getRatings().size() + " users\n");
                        }
                    }

                    myWriter.write("\nDocumentary:\n");

                    if (filmToDocumentary(filmArrayList).size() == 0){
                        myWriter.write("No result\n\n");
                    }else{
                        for(Film film : filmToDocumentary(filmArrayList)){
                            filmId = film.getId();
                            Documentary specifiedFeatureFilm = getSpecifiedDocumentary(documentaryArrayList, filmId);
                            assert specifiedFeatureFilm != null;
                            String date = specifiedFeatureFilm.getReleaseDate().split("\\.")[2];



                            myWriter.write(film.getFilmTitle() + " (" + date + ") Ratings: " +
                                    Double.toString(film.getAverageRating()).replace(".", ",").substring(0, 3).replace(",0", "").replace("0,", "0") +
                                    "/10 from " +
                                    film.getRatings().size() + " users\n");

                        }
                    }

                    myWriter.write("\nTvSeries\n");

                    if(filmToTv(filmArrayList).size() == 0){
                        myWriter.write("No result\n\n");
                    }else{
                        for(Film film : filmToTv(filmArrayList)){
                            filmId = film.getId();
                            TvSeries specifiedFeatureFilm = getSpecifiedTvSeries(tvSeriesArrayList, filmId);
                            assert specifiedFeatureFilm != null;
                            String date = specifiedFeatureFilm.getStartDate().split("\\.")[2];
                            String endDate = specifiedFeatureFilm.getEndDate().split("\\.")[2];



                            myWriter.write(film.getFilmTitle() + " (" + date +"-" + endDate +  ") Ratings: " +
                                    Double.toString(film.getAverageRating()).replace(".", ",").substring(0, 3).replace(",0", "").replace("0,", "0") +
                                    "/10 from " +
                                    film.getRatings().size() + " users\n");
                        }
                    }

                }
            }else if(commandParts[1].equals("FEATUREFILMS")){
                if(commandParts[2].equals("BEFORE")){
                    myWriter.write("LIST\tFEATUREFILMS\tBEFORE\t" + commandParts[3] + "\n\n");
                    if(getFilmBeforeYear(commandParts[3],featureFilmArrayList).size() == 0){
                        myWriter.write("No result");
                    }else {
                        for(FeatureFilm film : getFilmBeforeYear(commandParts[3],featureFilmArrayList)){
                            myWriter.write("Film title: " + film.getFilmTitle() + " (" + film.getReleaseDate().split("\\.")[2] + ")\n" +
                                    film.getLength() + " min\n" + "Language: " + film.getLanguage() + "\n\n");
                        }
                    }

                }else if(commandParts[2].equals("AFTER")){
                    myWriter.write("LIST\tFEATUREFILMS\tAFTER\t" + commandParts[3] + "\n\n");
                    if(getFilmAfterYear(commandParts[3], featureFilmArrayList).size() == 0){
                        myWriter.write("No result");
                    }else{
                        for(FeatureFilm film : getFilmAfterYear(commandParts[3], featureFilmArrayList)){
                            myWriter.write("Film title: " + film.getFilmTitle() + " (" + film.getReleaseDate().split("\\.")[2] + ")\n" +
                                    film.getLength() + " min\n" + "Language: " + film.getLanguage() + "\n\n");
                        }
                    }
                }
            }else if(commandParts[1].equals("ARTISTS")){
                myWriter.write("LIST\tARTISTS\tFROM\t" + commandParts[3]+"\n\n");

                myWriter.write("Directors:\n");

                if(getDirectorsByCountry(commandParts[3], directorArrayList).size() == 0){
                    myWriter.write("No result\n\n");
                }else{
                    for(String director : getDirectorsByCountry(commandParts[3], directorArrayList)){
                        myWriter.write(director + "\n");
                    }
                }
                myWriter.write("\nWriters:\n");

                if (getWritersByCountry(commandParts[3],writerArrayList).size() == 0){
                    myWriter.write("No result\n\n");
                }else{
                    for(String writer : getWritersByCountry(commandParts[3],writerArrayList)){
                        myWriter.write(writer + "\n");
                    }
                }

                myWriter.write("\nActors:\n");

                if(getActorsByCountry(commandParts[3], actorArrayList).size() == 0){
                    myWriter.write("No result\n\n");
                }else{
                    for(String actor : getActorsByCountry(commandParts[3], actorArrayList)){
                        myWriter.write(actor + "\n");
                    }
                }

                myWriter.write("\nChildActors:\n");

                if(getChildActorsByCountry(commandParts[3], childActorArrayList).size() == 0){
                    myWriter.write("No result\n\n");
                }else{
                    for(String childActor : getChildActorsByCountry(commandParts[3], childActorArrayList)){
                        myWriter.write(childActor + "\n");
                    }
                }

                myWriter.write("\nStuntPerformers:\n");

                if(getStuntByCountry(commandParts[3],stuntPerformerArrayList).size() == 0){
                    myWriter.write("No result\n\n");
                }else{
                    for(String stuntPerformer : getStuntByCountry(commandParts[3],stuntPerformerArrayList)){
                        myWriter.write(stuntPerformer + "\n");
                    }
                }

            }

            myWriter.write("\n\n");
            myWriter.write("-----------------------------------------------------------------------------------------------------\n");
        }
        myWriter.close();
    }


    // These createArtistsArrays and createFilmsArrays creates ArrayLists from people.txt and films.txt

    public static void createArtistsArrays(String file,ArrayList<User> users, ArrayList<Actor> actors, ArrayList<ChildActor> childActors,
                                           ArrayList<StuntPerformer> stuntPerformers,
                                           ArrayList<Director> directors, ArrayList<Writer> writers, ArrayList<Performer> performers) throws FileNotFoundException {
        Scanner peopleFile = new Scanner(new File(file));

        while (peopleFile.hasNextLine()) {
            String line = peopleFile.nextLine();
            String[] fields = line.split("\t");


            if (fields[0].equals("User:")) {
                users.add(new User(fields[1], fields[2], fields[3], fields[4]));
            } else if (fields[0].equals("Actor:")) {
                performers.add(new Performer(fields[1], fields[2], fields[3], fields[4]));
                actors.add(new Actor(fields[1], fields[2], fields[3], fields[4], fields[5]));
            } else if (fields[0].equals("ChildActor:")) {
                performers.add(new Performer(fields[1], fields[2], fields[3], fields[4]));
                childActors.add(new ChildActor(fields[1], fields[2], fields[3], fields[4], fields[5]));
            } else if (fields[0].equals("StuntPerformer:")) {
                performers.add(new Performer(fields[1], fields[2], fields[3], fields[4]));
                String[] realActorIds = fields[6].split(",");
                stuntPerformers.add(new StuntPerformer(fields[1], fields[2], fields[3], fields[4], fields[5]));
                int lastElementIndex = stuntPerformers.size() - 1;
                stuntPerformers.get(lastElementIndex).generateList(realActorIds);
            } else if (fields[0].equals("Director:")) {
                directors.add(new Director(fields[1], fields[2], fields[3], fields[4], fields[5]));
            } else if (fields[0].equals("Writer:")) {
                writers.add(new Writer(fields[1], fields[2], fields[3], fields[4], fields[5]));
            }
        }
        peopleFile.close();
    }

    public static void createFilmsArrays(String file,ArrayList<Film> films, ArrayList<FeatureFilm> featureFilms, ArrayList<ShortFilm> shortFilms,
                                         ArrayList<Documentary> documentaries, ArrayList<TvSeries> tvSeries) throws FileNotFoundException {

        Scanner filmFile = new Scanner(new File(file));

        while (filmFile.hasNextLine()) {
            String line = filmFile.nextLine();
            String[] fields = line.split("\t");

            String[] directorIds = fields[4].split(",");
            String[] performersIds = fields[7].split(",");


            if (fields[0].equals("FeatureFilm:")) {
                films.add(new FeatureFilm(fields[1], fields[2], fields[3], fields[5], fields[6]));
                int lastElementIndex = films.size() - 1;
                films.get(lastElementIndex).generateDirectorsList(directorIds);
                films.get(lastElementIndex).generatePerformersList(performersIds);

                String[] genres = fields[8].split(",");
                String[] writersIds = fields[10].split(",");

                featureFilms.add(new FeatureFilm(fields[1], fields[2], fields[3], fields[5], fields[6], fields[9], fields[11]));
                int lastElementF = featureFilms.size() - 1;
                featureFilms.get(lastElementF).generateDirectorsList(directorIds);
                featureFilms.get(lastElementF).generatePerformersList(performersIds);
                featureFilms.get(lastElementF).generateGenres(genres);
                featureFilms.get(lastElementF).generateWriters(writersIds);

            } else if (fields[0].equals("ShortFilm:")) {

                if(Integer.parseInt(fields[5]) <=40) {
                    films.add(new ShortFilm(fields[1], fields[2], fields[3], fields[5], fields[6]));
                    int lastElementIndex = films.size() - 1;
                    films.get(lastElementIndex).generateDirectorsList(directorIds);
                    films.get(lastElementIndex).generatePerformersList(performersIds);

                    String[] genres = fields[8].split(",");
                    String[] writersIds = fields[10].split(",");

                    shortFilms.add(new ShortFilm(fields[1], fields[2], fields[3], fields[5], fields[6], fields[9]));
                    int lastElementF = shortFilms.size() - 1;
                    shortFilms.get(lastElementF).generateDirectorsList(directorIds);
                    shortFilms.get(lastElementF).generatePerformersList(performersIds);
                    shortFilms.get(lastElementF).generateGenres(genres);
                    shortFilms.get(lastElementF).generateWriters(writersIds);
                }else{
                    System.out.println("Short Films lenght should be less or equal than 40");
                }

            } else if (fields[0].equals("Documentary:")) {
                films.add(new Documentary(fields[1], fields[2], fields[3], fields[5], fields[6]));
                int lastElementIndex = films.size() - 1;
                films.get(lastElementIndex).generateDirectorsList(directorIds);
                films.get(lastElementIndex).generatePerformersList(performersIds);

                documentaries.add(new Documentary(fields[1], fields[2], fields[3], fields[5], fields[6], fields[8]));
                int lastElementF = documentaries.size() - 1;
                documentaries.get(lastElementF).generateDirectorsList(directorIds);
                documentaries.get(lastElementF).generatePerformersList(performersIds);

            } else if (fields[0].equals("TVSeries:")) {
                films.add(new TvSeries(fields[1], fields[2], fields[3], fields[5], fields[6]));
                int lastElementIndex = films.size() - 1;
                films.get(lastElementIndex).generateDirectorsList(directorIds);
                films.get(lastElementIndex).generatePerformersList(performersIds);

                String[] genres = fields[8].split(",");
                String[] writersIds = fields[9].split(",");

                tvSeries.add(new TvSeries(fields[1], fields[2], fields[3], fields[5], fields[6], fields[10], fields[11], fields[12], fields[13]));
                int lastElementF = tvSeries.size() - 1;
                tvSeries.get(lastElementF).generateDirectorsList(directorIds);
                tvSeries.get(lastElementF).generatePerformersList(performersIds);
                tvSeries.get(lastElementF).generateGenres(genres);
                tvSeries.get(lastElementF).generateWriters(writersIds);
            }


        }
        filmFile.close();
    }


    //To check
    public static boolean isFilmIdDirectorPerformerWriterInSystem(String[] filmFields, ArrayList<FeatureFilm> featureFilms,
                                                                  ArrayList<Director> directors, ArrayList<Performer> performers, ArrayList<Writer>
                                                                          writers) {
        String filmId = filmFields[2];
        String[] directorIds = filmFields[5].split(",");
        String[] performerIds = filmFields[8].split(",");
        String[] writerIds = filmFields[11].split(",");

        int flagForId = 0;
        int flagForDirectors = 0;
        int flagForPerformers = 0;
        int flagForWriters = 0;

        for (FeatureFilm featureFilm : featureFilms) {
            if (featureFilm.getId().equals(filmId)) {
                flagForId++;
                break;
            }
        }

        for (String directorId : directorIds) {
            flagForDirectors = 0;
            for (Director director : directors) {
                if (directorId.equals(director.getId())) {
                    flagForDirectors++;

                }
            }
        }

        for (String performerId : performerIds) {
            flagForPerformers = 0;
            for (Performer performer : performers) {
                if (performerId.equals(performer.getId())) {
                    flagForPerformers++;

                }
            }
        }

        for (String writerId : writerIds) {
            flagForWriters = 0;
            for (Writer writer : writers) {
                if (writerId.equals(writer.getId())) {
                    flagForWriters++;

                }
            }
        }
        return (flagForId == 0 && flagForDirectors > 0 && flagForPerformers > 0 && flagForWriters > 0);


    }


    //In VIEWFILM command this method enables us to return the class simple name
    //If there is no stated filmId in the system it returns empty string
    public static String getFilmType(ArrayList<Film> films, String filmId) {

        for (Film film : films) {
            if (filmId.equals(film.getId())) {
                return film.getClass().getSimpleName();
            }
        }
        return "";

    }


    //These 4 method returns specifiedFilm according to filmId
    public static FeatureFilm getSpecifiedFeatureFilm(ArrayList<FeatureFilm> featureFilms
            , String id) {

        for (FeatureFilm featureFilm : featureFilms) {
            if (featureFilm.getId().equals(id)) {
                return featureFilm;
            }
        }
        return null;
    }

    public static ShortFilm getSpecifiedShortFilm(ArrayList<ShortFilm> shortFilms, String id) {
        for (ShortFilm shortFilm : shortFilms) {
            if (shortFilm.getId().equals(id)) {
                return shortFilm;
            }
        }
        return null;
    }

    public static Documentary getSpecifiedDocumentary(ArrayList<Documentary> documentaries, String id) {
        for (Documentary documentary : documentaries) {
            if (documentary.getId().equals(id)) {
                return documentary;
            }
        }
        return null;
    }

    public static TvSeries getSpecifiedTvSeries(ArrayList<TvSeries> tvSeries, String id) {
        for (TvSeries tvSeries1 : tvSeries) {
            if (tvSeries1.getId().equals(id)) {
                return tvSeries1;
            }
        }
        return null;
    }

    public static ArrayList<String> getWriterNames(FeatureFilm film, ArrayList<Writer> writers) {
        ArrayList<String> writerIds = film.getWriters();
        ArrayList<String> writerNames = new ArrayList<String>();

        for (String id : writerIds) {
            for (Writer writer : writers) {
                if (id.equals(writer.getId())) {
                    writerNames.add(writer.getName() + " " + writer.getSurname());
                }
            }
        }
        return writerNames;
    }

    public static ArrayList<String> getWriterNames(ShortFilm film, ArrayList<Writer> writers) {
        ArrayList<String> writerIds = film.getWriters();
        ArrayList<String> writerNames = new ArrayList<String>();

        for (String id : writerIds) {
            for (Writer writer : writers) {
                if (id.equals(writer.getId())) {
                    writerNames.add(writer.getName() + " " + writer.getSurname());
                }
            }
        }
        return writerNames;
    }

    public static ArrayList<String> getWriterNames(TvSeries film, ArrayList<Writer> writers) {
        ArrayList<String> writerIds = film.getWriters();
        ArrayList<String> writerNames = new ArrayList<String>();

        for (String id : writerIds) {
            for (Writer writer : writers) {
                if (id.equals(writer.getId())) {
                    writerNames.add(writer.getName() + " " + writer.getSurname());
                }
            }
        }
        return writerNames;
    }

    public static ArrayList<String> getDirectorNames(FeatureFilm film, ArrayList<Director> directors) {
        ArrayList<String> directorIds = film.getDirectorIds();
        ArrayList<String> directorNames = new ArrayList<String>();

        for (String id : directorIds) {
            for (Director director : directors) {
                if (id.equals(director.getId())) {
                    directorNames.add(director.getName() + " " + director.getSurname());
                }
            }
        }
        return directorNames;
    }

    public static ArrayList<String> getDirectorNames(ShortFilm film, ArrayList<Director> directors) {
        ArrayList<String> directorIds = film.getDirectorIds();
        ArrayList<String> directorNames = new ArrayList<String>();

        for (String id : directorIds) {
            for (Director director : directors) {
                if (id.equals(director.getId())) {
                    directorNames.add(director.getName() + " " + director.getSurname());
                }
            }
        }
        return directorNames;
    }

    public static ArrayList<String> getDirectorNames(Documentary film, ArrayList<Director> directors) {
        ArrayList<String> directorIds = film.getDirectorIds();
        ArrayList<String> directorNames = new ArrayList<String>();

        for (String id : directorIds) {
            for (Director director : directors) {
                if (id.equals(director.getId())) {
                    directorNames.add(director.getName() + " " + director.getSurname());
                }
            }
        }
        return directorNames;
    }

    public static ArrayList<String> getDirectorNames(TvSeries film, ArrayList<Director> directors) {
        ArrayList<String> directorIds = film.getDirectorIds();
        ArrayList<String> directorNames = new ArrayList<String>();

        for (String id : directorIds) {
            for (Director director : directors) {
                if (id.equals(director.getId())) {
                    directorNames.add(director.getName() + " " + director.getSurname());
                }
            }
        }
        return directorNames;
    }

    public static ArrayList<String> getStarNames(FeatureFilm film, ArrayList<Performer> performers) {
        ArrayList<String> performerIds = film.getPerformersId();
        ArrayList<String> performerNames = new ArrayList<String>();

        for (String id : performerIds) {
            for (Performer performer : performers) {
                if (id.equals(performer.getId())) {
                    performerNames.add(performer.getName() + " " + performer.getSurname());
                }
            }
        }
        return performerNames;
    }

    public static ArrayList<String> getStarNames(ShortFilm film, ArrayList<Performer> performers) {
        ArrayList<String> performerIds = film.getPerformersId();
        ArrayList<String> performerNames = new ArrayList<String>();

        for (String id : performerIds) {
            for (Performer performer : performers) {
                if (id.equals(performer.getId())) {
                    performerNames.add(performer.getName() + " " + performer.getSurname());
                }
            }
        }
        return performerNames;
    }

    public static ArrayList<String> getStarNames(Documentary film, ArrayList<Performer> performers) {
        ArrayList<String> performerIds = film.getPerformersId();
        ArrayList<String> performerNames = new ArrayList<String>();

        for (String id : performerIds) {
            for (Performer performer : performers) {
                if (id.equals(performer.getId())) {
                    performerNames.add(performer.getName() + " " + performer.getSurname());
                }
            }
        }
        return performerNames;
    }

    public static ArrayList<String> getStarNames(TvSeries film, ArrayList<Performer> performers) {
        ArrayList<String> performerIds = film.getPerformersId();
        ArrayList<String> performerNames = new ArrayList<String>();

        for (String id : performerIds) {
            for (Performer performer : performers) {
                if (id.equals(performer.getId())) {
                    performerNames.add(performer.getName() + " " + performer.getSurname());
                }
            }
        }
        return performerNames;
    }


    public static Film getGeneralFilm(String filmId, ArrayList<Film> films) {
        for (Film film : films) {
            if (filmId.equals(film.getId())) {
                return film;
            }
        }
        return null;
    }

    public static User getSpecifiedUser(String userId, ArrayList<User> users) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public static Film getSpecifiedFilm(String filmId, ArrayList<Film> films) {
        for (Film film : films) {
            if (film.getId().equals(filmId)) {
                return film;
            }
        }
        return null;
    }
    public static ArrayList<Film> getFilmByCountry(String country, ArrayList<Film> films){
        ArrayList<Film> films1 = new ArrayList<>();

        for(Film film : films){
            if(film.getCountry().equals(country)){
                films1.add(film);
            }
        }return films1;


    }

    public static ArrayList<FeatureFilm> getFilmBeforeYear(String year, ArrayList<FeatureFilm> featureFilms){
        ArrayList<FeatureFilm> featureFilms1 = new ArrayList<>();

        for(FeatureFilm featureFilm : featureFilms){
            if(Integer.parseInt(featureFilm.getReleaseDate().split("\\.")[2]) < Integer.parseInt(year)){
                featureFilms1.add(featureFilm);
            }
        }
        return featureFilms1;
    }

    public static ArrayList<FeatureFilm> getFilmAfterYear(String year, ArrayList<FeatureFilm> featureFilms){
        ArrayList<FeatureFilm> featureFilms1 = new ArrayList<>();

        for(FeatureFilm featureFilm : featureFilms){
            if(Integer.parseInt(featureFilm.getReleaseDate().split("\\.")[2]) >= Integer.parseInt(year)){
                featureFilms1.add(featureFilm);
            }
        }
        return featureFilms1;
    }

    public static ArrayList<Film> filmToFeature(ArrayList<Film> films){
        ArrayList<Film> featureFilms = new ArrayList<>();

        for(Film film : films){
            if(film.getClass().getSimpleName().equals("FeatureFilm")){
                featureFilms.add(film);

            }
        }

        Collections.sort(featureFilms);
        Collections.reverse(featureFilms);

        return featureFilms;
    }

    public static ArrayList<Film> filmToShort(ArrayList<Film> films){
        ArrayList<Film> shortFilms = new ArrayList<>();

        for(Film film : films){
            if(film.getClass().getSimpleName().equals("ShortFilm")){
                shortFilms.add(film);
            }
        }
        Collections.sort(shortFilms);
        Collections.reverse(shortFilms);

        return shortFilms;
    }

    public static ArrayList<Film> filmToTv(ArrayList<Film> films){
        ArrayList<Film> tvSeries = new ArrayList<>();

        for(Film film : films){
            if(film.getClass().getSimpleName().equals("TvSeries")){
                tvSeries.add(film);
            }
        }
        Collections.sort(tvSeries);
        Collections.reverse(tvSeries);

        return tvSeries;
    }

    public static ArrayList<Film> filmToDocumentary(ArrayList<Film> films){
        ArrayList<Film> documentary = new ArrayList<>();

        for(Film film : films){
            if(film.getClass().getSimpleName().equals("Documentary")){
                documentary.add(film);
            }
        }
        Collections.sort(documentary);
        Collections.reverse(documentary);

        return documentary;
    }

    public static ArrayList<String> getDirectorsByCountry(String country, ArrayList<Director> directors){
        ArrayList<String> directorList = new ArrayList<>();

        for(Director director : directors){
            if(director.getCountry().equals(country)){
                directorList.add(director.getName() + " " + director.getSurname() + " " + director.getAgent());
            }
        }

        return directorList;
    }

    public static ArrayList<String> getWritersByCountry(String country, ArrayList<Writer> writers){
        ArrayList<String> writerList = new ArrayList<>();

        for(Writer writer : writers){
            if(writer.getCountry().equals(country)){
                writerList.add(writer.getName() + " " + writer.getSurname() + " " + writer.getWritingStyle());
            }
        }
        return writerList;
    }

    public static ArrayList<String> getActorsByCountry(String country, ArrayList<Actor> actors){
        ArrayList<String> actorList = new ArrayList<>();

        for(Actor actor : actors){
            if(actor.getCountry().equals(country)){
                actorList.add(actor.getName() + " " + actor.getSurname() + " " + actor.getHeight() + " cm");
            }
        }
        return actorList;
    }

    public static ArrayList<String> getChildActorsByCountry(String country, ArrayList<ChildActor> childActors){
        ArrayList<String> childActorList = new ArrayList<>();

        for(ChildActor childActor : childActors){
            if(childActor.getCountry().equals(country)){
                childActorList.add(childActor.getName() + " " + childActor.getSurname() + " " + childActor.getAge());
            }
        }
        return childActorList;
    }

    public static ArrayList<String> getStuntByCountry(String country, ArrayList<StuntPerformer> stuntPerformers){
        ArrayList<String> stuntPerformerList = new ArrayList<>();

        for(StuntPerformer stuntPerformer : stuntPerformers){
            if(stuntPerformer.getCountry().equals(country)){
                stuntPerformerList.add(stuntPerformer.getName() + " " + stuntPerformer.getSurname() + " " + stuntPerformer.getHeight() + " cm");
            }
        }
        return stuntPerformerList;
    }
}





