package library.items;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an abstract item in the library system.
 * An item can be borrowed, returned, and may have a digital version.
 * 
 * <p>This class is intended to be extended by specific item types such as books, theses, etc.</p>
 * 
 * @author mianm
 */
public abstract class Item {
    private final String id, title, author, topic;
    private final int pageCount;
    private int borrowCount;
    private List<Copy> copies;
    private DigitalItem digitalVersion;
    private int copiesCount;

    /**
     * Initializes a new instance of the Item class.
     *
     * @param id          The unique ID of the item.
     * @param title       The title of the item.
     * @param author      The author of the item.
     * @param pageCount   The number of pages in the item.
     * @param topic       The topic of the item.
     * @param copiesCount The number of copies available in the library.
     */
    public Item(String id, String title, String author, int pageCount, String topic, int copiesCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.topic = topic;
        this.copiesCount = copiesCount;
        this.copies = new ArrayList<>();
        this.borrowCount = 0;
        makeCopies(copiesCount);
    }

    /**
     * Attempts to borrow a copy of the item.
     *
     * @return True if a copy was successfully borrowed, false otherwise.
     */
    public boolean borrowResource() {
        for (Copy c : copies) {
            if (c.getAvailable()) {
                c.setAvailable(false);
                this.borrowCount++;
                this.copiesCount--;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a copy of the item to the library.
     *
     * @param cp The copy being returned.
     */
    public void returnResource(Copy cp) {
        for (Copy c : copies) {
            if (c.getCopyId().equals(cp.getCopyId())) {
                if (!cp.getDamaged()) {
                    c.setAvailable(true);
                    this.copiesCount++;
                }
                break;
            }
        }
    }

    /**
     * Retrieves the unique ID of the item.
     *
     * @return The unique ID of the item.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the title of the item.
     *
     * @return The title of the item.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the author of the item.
     *
     * @return The author of the item.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Retrieves the topic of the item.
     *
     * @return The topic of the item.
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Retrieves the number of pages in the item.
     *
     * @return The number of pages in the item.
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Retrieves the borrow count of the item.
     *
     * @return The number of times the item has been borrowed.
     */
    public int getBorrowCount() {
        return borrowCount;
    }

    /**
     * Sets the borrow count of the item.
     *
     * @param borrowCount The new borrow count.
     */
    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }

    /**
     * Retrieves the list of copies of the item.
     *
     * @return A list of copies of the item.
     */
    public List<Copy> getCopies() {
        return copies;
    }

    /**
     * Retrieves the digital version of the item.
     *
     * @return The digital version of the item, or null if it does not exist.
     */
    public DigitalItem getDigitalVersion() {
        return digitalVersion;
    }

    /**
     * Sets the digital version of the item.
     *
     * @param digitalVersion The digital version to set.
     */
    public void setDigitalVersion(DigitalItem digitalVersion) {
        this.digitalVersion = digitalVersion;
    }

    /**
     * Retrieves the number of available copies of the item.
     *
     * @return The number of available copies.
     */
    public int getCopiesCount() {
        return copiesCount;
    }

    /**
     * Adds a new copy of the item to the library.
     */
    public void addNewCopy() {
        copies.add(new Copy());
        copiesCount++;
    }

    /**
     * Creates a digital version of the item if it does not already exist.
     */
    public void createDigVersion() {
        if (this.digitalVersion == null) {
            digitalVersion = new DigitalItem(this.id);
        }
    }

    /**
     * Repairs a damaged copy of the item.
     * If the damage count exceeds 5, the copy is removed from the library.
     *
     * @param c The copy to repair.
     */
    public void repair(Copy c) {
        if (c.getDamageCount() <= 5) {
            c.setDamaged(false);
        } else {
            copies.remove(c);
        }
    }

    /**
     * Retrieves the number of available copies of the item.
     *
     * @return The number of available copies.
     */
    public int AvailableCopyCount() {
        int count = 0;
        for (Copy c : copies) {
            if (c.getAvailable()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Creates the specified number of copies for the item.
     *
     * @param copiesCount The number of copies to create.
     */
    private void makeCopies(int copiesCount) {
        for (int i = 0; i < copiesCount; i++) {
            copies.add(new Copy());
        }
    }
}