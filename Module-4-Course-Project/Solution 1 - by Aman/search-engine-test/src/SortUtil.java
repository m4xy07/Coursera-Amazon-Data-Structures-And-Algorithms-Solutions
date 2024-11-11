import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtil {

    /**
     * Performs a bubble sort on the list of books using the provided comparator.
     *
     * @param books     the list of books to sort
     * @param comparator the comparator to determine the order of the books
     */
    public static void bubbleSort(List<Book> books, Comparator<Book> comparator) {
        if (books == null || books.size() < 2) {
            return; // No need to sort if the list is null or has fewer than 2 elements
        }

        int n = books.size();
        boolean swapped;
        // Loop through all books
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Flag to track if a swap was made
            // Last i elements are already in place
            for (int j = 0; j < n - 1 - i; j++) {
                // Compare the adjacent elements using the provided comparator
                if (comparator.compare(books.get(j), books.get(j + 1)) > 0) {
                    // Swap if they are in the wrong order
                    Collections.swap(books, j, j + 1);
                    swapped = true; // Set the flag to true
                }
            }
            // If no swaps were made, the list is sorted
            if (!swapped) {
                break; // Break out of the loop early
            }
        }
    }

    /**
     * Performs an insertion sort on the list of books using the provided comparator.
     *
     * @param books     the list of books to sort
     * @param comparator the comparator to determine the order of the books
     */
    public static void insertionSort(List<Book> books, Comparator<Book> comparator) {
        if (books == null || books.size() < 2) {
            return; // No need to sort if the list is null or has fewer than 2 elements
        }

        for (int i = 1; i < books.size(); i++) {
            Book key = books.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(books.get(j), key) > 0) {
                books.set(j + 1, books.get(j));
                j = j - 1;
            }
            books.set(j + 1, key);
        }
    }

    /**
     * Performs a quick sort on the list of books using the provided comparator.
     *
     * @param books     the list of books to sort
     * @param comparator the comparator to determine the order of the books
     * @param low       the starting index of the sublist to sort
     * @param high      the ending index of the sublist to sort
     */
    public static void quickSort(List<Book> books, Comparator<Book> comparator, int low, int high) {
        if (books == null || books.size() < 2 || low >= high) {
            return; // No need to sort if the list is null, has fewer than 2 elements, or indices are invalid
        }

        if (low < high) {
            int pi = partition(books, comparator, low, high);
            quickSort(books, comparator, low, pi - 1);
            quickSort(books, comparator, pi + 1, high);
        }
    }

    private static int partition(List<Book> books, Comparator<Book> comparator, int low, int high) {
        Book pivot = books.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare(books.get(j), pivot) <= 0) {
                i++;
                Collections.swap(books, i, j);
            }
        }
        Collections.swap(books, i + 1, high);
        return i + 1;
    }
}