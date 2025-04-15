package library.items;

/**
 * Represents a digital version of an item in the library system.
 * A digital item tracks the number of views and downloads.
 * 
 * <p>Used by the {@link Item} class to manage digital versions of items.</p>
 * 
 * @author mianm
 */
public class DigitalItem {
    private final String id;
    private int views;
    private int downloads;

    /**
     * Initializes a new instance of the DigitalItem class.
     *
     * @param id The unique ID of the digital item.
     */
    public DigitalItem(String id) {
        this.id = id;
        this.views = 0;
        this.downloads = 0;
    }

    /**
     * Retrieves the unique ID of the digital item.
     *
     * @return The unique ID of the digital item.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the number of views for the digital item.
     *
     * @return The number of views.
     */
    public int getViews() {
        return views;
    }

    /**
     * Sets the number of views for the digital item.
     *
     * @param views The new number of views.
     */
    public void setViews(int views) {
        this.views = views;
    }

    /**
     * Retrieves the number of downloads for the digital item.
     *
     * @return The number of downloads.
     */
    public int getDownloads() {
        return downloads;
    }

    /**
     * Sets the number of downloads for the digital item.
     *
     * @param downloads The new number of downloads.
     */
    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    /**
     * Increments the view count for the digital item.
     */
    public void viewDigital() {
        this.views++;
    }

    /**
     * Increments the download count for the digital item.
     */
    public void downloadsDigital() {
        this.downloads++;
    }
}