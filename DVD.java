public class DVD extends LibraryItem {
    private String director;

    public DVD(String title, String itemId, String director) {
        super(title, itemId);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String displayDetails() {
        return "DVD: \"" + getTitle() + "\", ID: " + getItemId() + ", Director: " + director;
    }
}