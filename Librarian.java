public class Librarian extends User {
    public Librarian(String name, String userId) {
        super(name, userId);
    }

    @Override
    public String performAction(LibraryItem item) {
        return getName() + " added \"" + item.getTitle() + "\" to the library catalog.";
    }
}