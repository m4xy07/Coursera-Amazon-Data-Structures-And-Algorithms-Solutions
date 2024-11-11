package C3M4L1.library;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LibrarySerializer serializer = new LibrarySerializer();
        UserInteractionLogger logger = new UserInteractionLogger(); // Initialize logger

        // Load the library from a serialized file if it exists and is valid
        List<Book> books = serializer.loadLibrary("resources/data/library.ser");
        if (books != null) {
            library.setBooks(books);
            System.out.println("Library loaded successfully from resources/data/library.ser");
            logger.log("Library loaded successfully from resources/data/library.ser"); // Log this action
        } else {
            System.out.println("Loading data from books.txt...");
            library.loadBooks("resources/data/books.txt");
            logger.log("Loaded data from books.txt"); // Log this action
        }

        // Create an instance of the LibraryMenu class, passing the library object
        LibraryMenu menu = new LibraryMenu(library, logger, serializer);

        // Display the menu to interact with the user
        menu.displayMenu();

        // Save the library state before exiting
        serializer.saveLibrary(library.getBooks(), "resources/data/library.ser");
        System.out.println("Library saved successfully to resources/data/library.ser");
        logger.log("Library saved successfully to resources/data/library.ser"); // Log this action
    }
}
