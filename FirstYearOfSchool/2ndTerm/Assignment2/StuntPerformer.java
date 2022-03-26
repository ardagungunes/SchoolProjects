import java.util.ArrayList;
import java.util.Arrays;

public class StuntPerformer extends Performer {
    private ArrayList<String> realIDs = new ArrayList<String>();
    private String height;

    public StuntPerformer(String id, String name, String surname, String country) {
        super(id, name, surname, country);
    }

    public StuntPerformer(String id, String name, String surname, String country, String height) {
        super(id, name, surname, country);

        this.height = height;
    }



    public String getHeight(){
        return this.height;
    }

    public ArrayList<String> getRealIDs() {
        return this.realIDs;
    }

    public void generateList(String[] arr){
        realIDs.addAll(Arrays.asList(arr));
    }
}
