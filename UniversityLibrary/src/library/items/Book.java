package library.items;

import java.util.UUID;

/**
 * Represents a book in the library system.
 * A book is a type of item that includes an ISBN.
 * 
 * <p>Extends the {@link Item} class.</p>
 * 
 * @author mianm
 */
public class Book extends Item {
    private final String isbn = UUID.randomUUID().toString();

    /**
     * Initializes a new instance of the Book class.
     *
     * @param id          The unique ID of the book.
     * @param title       The title of the book.
     * @param author      The author of the book.
     * @param pageCount   The number of pages in the book.
     * @param topic       The topic of the book.
     * @param copiesCount The number of copies available in the library.
     */
    public Book(String id, String title, String author, int pageCount, String topic, int copiesCount) {
        super(id, title, author, pageCount, topic, copiesCount);
    }

    /**
     * Retrieves the ISBN of the book.
     *
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }
}