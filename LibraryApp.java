import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryCatalog catalog = new LibraryCatalog();

        List<User> users = new ArrayList<>();
        users.add(new Librarian("Alice", "L002"));
        users.add(new Member("Bob", "M002"));
        User currentUser = users.get(0); // Default to first user

        catalog.addItem(new Book("1984", "B002", "George Orwell"));
        catalog.addItem(new DVD("The Matrix", "D002", "Wachowski Sisters"));

        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("Current User: " + currentUser.getName() + " (" + currentUser.getClass().getSimpleName() + ")");

            if (currentUser instanceof Librarian) {
                // Librarian Menu
                System.out.println("1. Add Item to Catalog");
                System.out.println("2. Search Catalog");
                System.out.println("3. Display All Items");
                System.out.println("4. Add User");
                System.out.println("5. Switch User");
                System.out.println("6. Show All Users");
                System.out.println("7. Delete Member");
                System.out.println("8. Exit");
                System.out.print("Enter your choice (1-8): ");
            } else {
                // Member Menu
                System.out.println("1. Borrow Item");
                System.out.println("2. Return Item");
                System.out.println("3. Search Catalog");
                System.out.println("4. Display Borrowed Books");
                System.out.println("5. Switch User");
                System.out.println("6. Show All Users");
                System.out.println("7. Exit");
                System.out.print("Enter your choice (1-7): ");
            }

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            if (currentUser instanceof Librarian) {
                switch (choice) {
                    case 1:
                        System.out.print("Enter item type (Book/DVD): ");
                        String itemType = scanner.nextLine().trim().toLowerCase();
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine().trim();
                        System.out.print("Enter item ID: ");
                        String itemId = scanner.nextLine().trim();

                        LibraryItem item;
                        if (itemType.equals("book")) {
                            System.out.print("Enter author: ");
                            String author = scanner.nextLine().trim();
                            item = new Book(title, itemId, author);
                        } else if (itemType.equals("dvd")) {
                            System.out.print("Enter director: ");
                            String director = scanner.nextLine().trim();
                            item = new DVD(title, itemId, director);
                        } else {
                            System.out.println("Invalid item type. Use 'Book' or 'DVD'.");
                            break;
                        }

                        catalog.addItem(item);
                        System.out.println(currentUser.performAction(item));
                        break;

                    case 2:
                        System.out.print("Enter search term: ");
                        String searchTerm = scanner.nextLine().trim();
                        List<LibraryItem> searchResults = catalog.searchByTitle(searchTerm);
                        if (searchResults.isEmpty()) {
                            System.out.println("No items found for '" + searchTerm + "'.");
                        } else {
                            System.out.println("Search Results:");
                            for (LibraryItem searchItem : searchResults) {
                                System.out.println(searchItem.displayDetails() + " | " + searchItem.checkAvailability());
                            }
                        }
                        break;

                    case 3:
                        catalog.displayCatalog();
                        break;

                    case 4:
                        System.out.print("Enter user type (Librarian/Member): ");
                        String userType = scanner.nextLine().trim().toLowerCase();
                        System.out.print("Enter user name: ");
                        String userName = scanner.nextLine().trim();
                        System.out.print("Enter user ID: ");
                        String userId = scanner.nextLine().trim();
                        User newUser;
                        if (userType.equals("librarian")) {
                            newUser = new Librarian(userName, userId);
                        } else if (userType.equals("member")) {
                            newUser = new Member(userName, userId);
                        } else {
                            System.out.println("Invalid user type.");
                            break;
                        }
                        users.add(newUser);
                        System.out.println("User added: " + userName + " (" + userType + ")");
                        break;

                    case 5:
                        System.out.println("Available users:");
                        for (int i = 0; i < users.size(); i++) {
                            User u = users.get(i);
                            System.out.println((i + 1) + ". " + u.getName() + " (" + u.getClass().getSimpleName() + ")");
                        }
                        System.out.print("Enter user number to switch: ");
                        try {
                            int userNum = Integer.parseInt(scanner.nextLine());
                            if (userNum < 1 || userNum > users.size()) {
                                System.out.println("Invalid user number.");
                            } else {
                                currentUser = users.get(userNum - 1);
                                System.out.println("Switched to user: " + currentUser.getName());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                        break;

                    case 6:
                        System.out.println("All users:");
                        for (User u : users) {
                            System.out.println(u.getName() + " (" + u.getClass().getSimpleName() + "), ID: " + u.getUserId());
                        }
                        break;

                    case 7:
                        // Only members can be deleted
                        if (users.size() == 1) {
                            System.out.println("Cannot delete the last user.");
                            break;
                        }
                        System.out.println("Available members to delete:");
                        List<Integer> memberIndexes = new ArrayList<>();
                        for (int i = 0; i < users.size(); i++) {
                            User u = users.get(i);
                            if (u instanceof Member) {
                                memberIndexes.add(i);
                                System.out.println(memberIndexes.size() + ". " + u.getName() + " (Member)");
                            }
                        }
                        if (memberIndexes.isEmpty()) {
                            System.out.println("No members to delete.");
                            break;
                        }
                        System.out.print("Enter member number to delete: ");
                        try {
                            int delNum = Integer.parseInt(scanner.nextLine());
                            if (delNum < 1 || delNum > memberIndexes.size()) {
                                System.out.println("Invalid member number.");
                            } else {
                                int userIndex = memberIndexes.get(delNum - 1);
                                if (users.get(userIndex) == currentUser) {
                                    System.out.println("Cannot delete the current user. Switch to another user first.");
                                } else {
                                    User removed = users.remove(userIndex);
                                    System.out.println("Deleted member: " + removed.getName());
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                        break;

                    case 8:
                        System.out.println("Thank you for using the Library Management System. Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                }
            } else {
                // Member menu
                switch (choice) {
                    case 1:
                        System.out.print("Enter item ID to borrow: ");
                        String borrowId = scanner.nextLine().trim();
                        LibraryItem borrowItem = catalog.findItemById(borrowId);
                        if (borrowItem == null) {
                            System.out.println("Item with ID " + borrowId + " not found.");
                        } else {
                            System.out.println(currentUser.performAction(borrowItem));
                        }
                        break;

                    case 2:
                        System.out.print("Enter item ID to return: ");
                        String returnId = scanner.nextLine().trim();
                        LibraryItem returnItem = catalog.findItemById(returnId);
                        if (returnItem == null) {
                            System.out.println("Item with ID " + returnId + " not found.");
                        } else {
                            System.out.println(((Member) currentUser).returnItem(returnItem));
                        }
                        break;

                    case 3:
                        System.out.print("Enter search term: ");
                        String searchTerm = scanner.nextLine().trim();
                        List<LibraryItem> searchResults = catalog.searchByTitle(searchTerm);
                        if (searchResults.isEmpty()) {
                            System.out.println("No items found for '" + searchTerm + "'.");
                        } else {
                            System.out.println("Search Results:");
                            for (LibraryItem searchItem : searchResults) {
                                System.out.println(searchItem.displayDetails() + " | " + searchItem.checkAvailability());
                            }
                        }
                        break;

                    case 4:
                        // Display all borrowed items (books and DVDs)
                        boolean found = false;
                        for (LibraryItem catalogItem : catalog.searchByTitle("")) {
                            if (catalogItem.isCheckedOut()) {
                                System.out.println(catalogItem.displayDetails() + " | " + catalogItem.checkAvailability());
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("No borrowed items in the catalog.");
                        }
                        break;

                    case 5:
                        System.out.println("Available users:");
                        for (int i = 0; i < users.size(); i++) {
                            User u = users.get(i);
                            System.out.println((i + 1) + ". " + u.getName() + " (" + u.getClass().getSimpleName() + ")");
                        }
                        System.out.print("Enter user number to switch: ");
                        try {
                            int userNum = Integer.parseInt(scanner.nextLine());
                            if (userNum < 1 || userNum > users.size()) {
                                System.out.println("Invalid user number.");
                            } else {
                                currentUser = users.get(userNum - 1);
                                System.out.println("Switched to user: " + currentUser.getName());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                        break;

                    case 6:
                        System.out.println("All users:");
                        for (User u : users) {
                            System.out.println(u.getName() + " (" + u.getClass().getSimpleName() + "), ID: " + u.getUserId());
                        }
                        break;

                    case 7:
                        System.out.println("Thank you for using the Library Management System. Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            }
        }
    }
}

