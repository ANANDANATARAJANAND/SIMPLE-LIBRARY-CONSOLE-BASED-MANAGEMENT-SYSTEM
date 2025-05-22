import java.util.List;
import java.util.ArrayList;

public class LibraryCatalog {
    private List<LibraryItem> items;

    public LibraryCatalog() {
        this.items = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public List<LibraryItem> searchByTitle(String searchTerm) {
        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    public void displayCatalog() {
        if (items.isEmpty()) {
            System.out.println("The library catalog is empty.");
            return;
        }
        System.out.println("Library Catalog:");
        for (LibraryItem item : items) {
            System.out.println(item.displayDetails() + " | " + item.checkAvailability());
        }
    }

    public LibraryItem findItemById(String itemId) {
        for (LibraryItem item : items) {
            if (item.getItemId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }
}