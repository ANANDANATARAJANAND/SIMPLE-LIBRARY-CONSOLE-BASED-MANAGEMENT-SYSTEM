public class Book extends LibraryItem {
    private String author;

    public Book(String title, String itemId, String author) {
        super(title, itemId);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String displayDetails() {
        return "Book: \"" + getTitle() + "\", ID: " + getItemId() + ", Author: " + author;
    }
}