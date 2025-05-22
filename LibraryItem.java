public abstract class LibraryItem {
    private String title;
    private String itemId;
    private boolean isCheckedOut;

    public LibraryItem(String title, String itemId) {
        this.title = title;
        this.itemId = itemId;
        this.isCheckedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemId() {
        return itemId;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.isCheckedOut = checkedOut;
    }

    public abstract String displayDetails();

    public String checkAvailability() {
        return isCheckedOut ? "\"" + title + "\" is currently checked out."
                           : "\"" + title + "\" is available for checkout.";
    }
}