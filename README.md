# Library Management System

This is a simple Java-based Library Management System that allows librarians and members to manage library items such as books and DVDs. The system supports adding, searching, borrowing, and returning items, as well as managing users.

## Features

- Add books and DVDs to the library catalog
- Search for items by title
- Display all items in the catalog
- Borrow and return items (members)
- Add and delete users (librarians)
- Switch between users

## Project Structure

- `LibraryApp.java` - Main application entry point and user interface
- `LibraryCatalog.java` - Manages the collection of library items
- `LibraryItem.java` - Abstract base class for library items
- `Book.java` - Represents a book item
- `DVD.java` - Represents a DVD item
- `User.java` - Abstract base class for users
- `Member.java` - Represents a library member
- `Librarian.java` - Represents a librarian

## How to Run

1. Compile all `.java` files:
    ```
    javac *.java
    ```
2. Run the main application:
    ```
    java LibraryApp
    ```

## Usage

- Follow the on-screen menu to perform actions as a librarian or member.
- Librarians can add items and users, search, display, and delete members.
- Members can borrow and return items, search, and view borrowed items.



This project is for educational purposes.
