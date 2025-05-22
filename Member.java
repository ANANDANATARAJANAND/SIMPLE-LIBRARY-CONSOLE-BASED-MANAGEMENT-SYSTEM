public class Member extends User {
    public Member(String name, String userId) {
        super(name, userId);
    }

    @Override
    public String performAction(LibraryItem item) {
        if (!item.isCheckedOut()) {
            item.setCheckedOut(true);
            return getName() + " checked out \"" + item.getTitle() + "\".";
        } else {
            return item.getTitle() + " is already borrowed and cannot be checked out again.";
        }
    }

    public String returnItem(LibraryItem item) {
        if (item.isCheckedOut()) {
            item.setCheckedOut(false);
            return getName() + " returned \"" + item.getTitle() + "\" to the library.";
        }
        return "\"" + item.getTitle() + "\" was not checked out.";
    }
}