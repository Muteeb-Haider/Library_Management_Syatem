package library.items;

import java.util.UUID;

/**
 * Represents a physical copy of an item in the library system.
 * Each copy has a unique ID and can be marked as available, damaged, or repaired.
 * 
 * <p>Used by the {@link Item} class to manage multiple copies of an item.</p>
 * 
 * @author mianm
 */
public class Copy {

    private final String copyId;
    private boolean available;
    private boolean damaged;
    private int damageCount;

    /**
     * Initializes a new instance of the Copy class.
     * The copy is initially available and undamaged.
     */
    public Copy() {
        this.copyId = UUID.randomUUID().toString();
        this.available = true;
        this.damaged = false;
        this.damageCount = 0;
    }

    /**
     * Retrieves the unique ID of the copy.
     *
     * @return The unique ID of the copy.
     */
    public String getCopyId() {
        return copyId;
    }

    /**
     * Retrieves the damage count of the copy.
     *
     * @return The damage count of the copy.
     */
    public int getDamageCount() {
        return damageCount;
    }

    /**
     * Sets the damage count of the copy.
     *
     * @param damageCount The new damage count.
     */
    public void setDamageCount(int damageCount) {
        this.damageCount = damageCount;
    }

    /**
     * Checks if the copy is available.
     *
     * @return True if the copy is available, false otherwise.
     */
    public boolean getAvailable() {
        return available;
    }

    /**
     * Sets the availability status of the copy.
     *
     * @param available True to mark the copy as available, false otherwise.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Checks if the copy is damaged.
     *
     * @return True if the copy is damaged, false otherwise.
     */
    public boolean getDamaged() {
        return damaged;
    }

    /**
     * Sets the damaged status of the copy.
     *
     * @param damaged True to mark the copy as damaged, false otherwise.
     */
    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    /**
     * Increases the damage count of the copy by 1.
     */
    public void increaseDamage() {
        this.damageCount++;
    }
}