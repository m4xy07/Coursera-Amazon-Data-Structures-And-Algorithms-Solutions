import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LibrarySerializer serializer = new LibrarySerializer();

        // Load the library data from the serialized file
        List<Book> books = serializer.loadLibrary("resources/data/library.ser");
        if (books != null) {
            library.setBooks(books);
            System.out.println("Library loaded successfully from src/resources/data/library.ser");
        } else {
            System.out.println("No saved library found. Loading default books from books.txt...");
            library.loadBooks("resources/data/books.txt");
            // Check if books were loaded successfully
            if (library.getBooks() != null && !library.getBooks().isEmpty()) {
                System.out.println("Default books loaded successfully from books.txt");
            } else {
                System.err.println("No books were loaded from books.txt. Please check the file and its format.");
            }
        }

        // Create a LibraryMenu instance and display the menu
        LibraryMenu menu = new LibraryMenu(library);
        menu.displayMenu();

        // Save the library data when exiting
        try {
            List<Book> booksToSave = library.getBooks();
            if (booksToSave != null && !booksToSave.isEmpty()) {
                serializer.saveLibrary(booksToSave, "resources/data/library.ser");
                System.out.println("Library saved successfully to src/resources/data/library.ser");
            } else {
                System.err.println("No books to save. Library is empty.");
            }
        } catch (Exception e) {
            System.err.println("Error saving library: " + e.getMessage());
        }
    }
}