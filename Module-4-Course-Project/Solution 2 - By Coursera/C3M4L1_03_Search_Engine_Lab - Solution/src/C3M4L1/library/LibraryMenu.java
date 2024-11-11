package C3M4L1.library;

import java.util.Comparator;
import java.util.Scanner;

public class LibraryMenu {
    private Library library;
    private UserInteractionLogger logger;
    private LibrarySerializer serializer;

    public LibraryMenu(Library library, UserInteractionLogger logger, LibrarySerializer serializer) {
        this.library = library;
        this.logger = logger;
        this.serializer = serializer;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Main Menu ===\n");
            System.out.println("1. View All Books");
            System.out.println("2. Sort Books by Title");
            System.out.println("3. Sort Books by Author");
            System.out.println("4. Sort Books by Year");
            System.out.println("5. Search for a Book by a keyword (Title, Author name, or Year)");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    library.viewAllBooks();
                    logger.logViewAllBooks(); // Log this interaction
                    break;
                case 2:
                    SortUtil.bubbleSort(library.getBooks(), Comparator.comparing(Book::getTitle));
                    System.out.println("Books sorted by title:");
                    library.viewAllBooks();
                    logger.logSort("title"); // Log this interaction
                    break;
                case 3:
                    SortUtil.insertionSort(library.getBooks(), Comparator.comparing(Book::getAuthor));
                    System.out.println("Books sorted by author:");
                    library.viewAllBooks();
                    logger.logSort("author"); // Log this interaction
                    break;
                case 4:
                    SortUtil.quickSort(library.getBooks(), Comparator.comparingInt(Book::getPublicationYear), 0, library.getBooks().size() - 1);
                    System.out.println("Books sorted by year:");
                    library.viewAllBooks();
                    logger.logSort("year"); // Log this interaction
                    break;
                case 5:
                    System.out.print("Enter a keyword (title, author name, or year): ");
                    String keyword = scanner.nextLine();
                    Book foundBook = library.searchBookByKeyword(keyword);
                    if (foundBook != null) {
                        System.out.println("Book found: " + foundBook);
                        logger.logSearch(keyword); // Log this interaction
                    } else {
                        System.out.println("Book not found.");
                        logger.logSearch(keyword + " - not found"); // Log unsuccessful search
                    }
                    break;
                case 6:
                    // Save the library data when exiting
                    serializer.saveLibrary(library.getBooks(), "resources/data/library.ser");
                    System.out.println("Library saved successfully. Exiting...");
                    logger.log("Library saved successfully. Exiting..."); // Log this interaction
                    return;  // Exit the menu
                default:
                    System.out.println("Invalid choice.");
                    logger.log("Invalid menu choice: " + choice); // Log invalid choice
            }
        }
    }
}
