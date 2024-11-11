import java.io.*;
import java.util.List;

public class LibrarySerializer {

    /**
     * Saves the list of books to a specified file.
     *
     * @param books    the list of books to save
     * @param fileName the name of the file to save the books to
     */
    public void saveLibrary(List<Book> books, String fileName) {
        if (books == null || books.isEmpty()) {
            System.out.println("No books to save.");
            return;  // Handle empty list case
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(books);  // Serialize the list of books
            System.out.println("Library saved successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving library: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of books from a specified file.
     *
     * @param fileName the name of the file to load books from
     * @return the list of books, or null if the file does not exist or is empty
     */
    public List<Book> loadLibrary(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) {
            System.out.println("File does not exist or is empty: " + fileName);
            return null;  // Return null if the file does not exist or is empty
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Book>) ois.readObject();  // Deserialize the list of books
        } catch (EOFException e) {
            System.err.println("The file is empty or corrupted: " + fileName);
            return null;  // Handle empty or corrupted file
        } catch (IOException e) {
            System.err.println("Error loading library: " + e.getMessage());
            e.printStackTrace();  // Handle IO exceptions
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found during deserialization: " + e.getMessage());
        }
        return null;  // Return null if an exception occurred
    }
}