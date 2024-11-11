import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LibraryMenu {
    private Library library;
    private UserInteractionLogger logger = new UserInteractionLogger();
    private LibrarySerializer serializer = new LibrarySerializer();  // Added serializer

    public LibraryMenu(Library library) {
        this.library = library;

        // Load the library data when the program starts
        List<Book> books = serializer.loadLibrary("src/resources/data/library.ser");
        if (books != null) {
            library.setBooks(books);
            System.out.println("Library loaded successfully.");
        } else {
            System.out.println("No saved library found. Loading default books.");
            library.loadBooks("src/resources/data/books.txt");
        }
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Display the menu options
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. View All Books");
            System.out.println("2. Sort Books by Title");
            System.out.println("3. Sort Books by Author");
            System.out.println("4. Sort Books by Year");
            System.out.println("5. Search for a Book by keyword");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            // Read user input
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    library.viewAllBooks();
                    logger.logViewAllBooks();  // Log the action
                    break;
                case "2":
                    sortBooks(Comparator.comparing(Book::getTitle), "title");
                    break;
                case "3":
                    sortBooks(Comparator.comparing(Book::getAuthor), "author");
                    break;
                case "4":
                    sortBooks(Comparator.comparing(Book::getPublicationYear), "publication year");
                    break;
                case "5":
                    searchForBook(scanner);
                    break;
                case "6":
                    exitProgram();
                    return;  // Break out of the loop
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void sortBooks(Comparator<Book> comparator, String sortBy) {
        SortUtil.bubbleSort(library.getBooks(), comparator);
        System.out.println("Books sorted by " + sortBy + ".");
        logger.logSort(sortBy);  // Log the action
    }

    private void searchForBook(Scanner scanner) {
        System.out.print("Enter a keyword to search (title, author, or year): ");
        String keyword = scanner.nextLine();
        Book foundBook = library.searchBookByKeyword(keyword);
        if (foundBook != null) {
            System.out.println("Book found: " + foundBook);
            logger.logSearch(keyword);  // Log the action
        } else {
            System.out.println("Book not found.");
            logger.log("No book found for keyword: " + keyword);  // Log the action
        }
    }

    private void exitProgram() {
        logger.log("Exited the program.");  // Log the action
        System.out.println("Library saved successfully.");
        serializer.saveLibrary(library.getBooks(), "src/resources/data/library.ser"); // Save library on exit
    }
}