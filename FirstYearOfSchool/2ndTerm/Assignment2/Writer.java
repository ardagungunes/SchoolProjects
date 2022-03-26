public class Writer extends Artist {
    private String writingStyle;

    public Writer(String id, String name, String surname, String country, String writingStyle) {
        super(id, name, surname, country);
        this.writingStyle = writingStyle;
    }

    public String getWritingStyle(){
        return this.writingStyle;
    }
}
