import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();

    // Method to load books from a file
    public void loadBooks(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into parts; assuming the format is "title,author,year"
                String[] parts = line.split(","); // Adjust the delimiter based on your file format
                if (parts.length == 3) { // Ensure there are three parts
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    int year;
                    try {
                        year = Integer.parseInt(parts[2].trim()); // Parse the year
                        books.add(new Book(title, author, year)); // Create a new Book object and add it to the list
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid year format for line: " + line);
                    }
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
            System.out.println("Loaded " + books.size() + " books.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to view all books
    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Method to search for a book by keyword
    public Book searchBookByKeyword(String keyword) {
        for (Book book : books) {
            // Check if the keyword is in the title or author (case-insensitive)
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                return book; // Return the first matching book
            }
        }
        return null; // Return null if no match is found
    }

    // Method to remove a book by title
    public boolean removeBookByTitle(String title) {
        return books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    // Method to get a list of books by a specific author
    public List<Book> getBooksByAuthor(String author) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }

    // Method to save the current list of books back to a file
    public void saveBooks(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Book book : books) {
                writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getPublicationYear() + "\n");
            }
            System.out.println("Books saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}