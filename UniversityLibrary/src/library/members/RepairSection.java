package library.members;

import library.items.Item;
import library.items.Copy;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the repair section of the library.
 * Handles the repair and archiving of damaged items.
 * 
 * <p>Used by the {@link UniversityLibrary} class to manage damaged items.</p>
 * 
 * @author mianm
 */
public class RepairSection {
    private int repairCount;
    private final List<Item> archivedItems;

    /**
     * Initializes a new instance of the RepairSection class.
     */
    public RepairSection() {
        this.archivedItems = new ArrayList<>();
        this.repairCount = 0;
    }

    /**
     * Requests a repair for a damaged item.
     * Some items may be archived instead of repaired.
     *
     * @param item The item to be repaired.
     */
    public void requestRepair(Item item) {
        repairCount++;
        double random = Math.random();
        if (random <= 0.3) {
            archivedItems.add(item);
        } else {
            for (Copy c : item.getCopies()) {
                if (c.getDamaged()) {
                    c.setDamaged(false);
                    c.setAvailable(true);
                }
            }
        }
    }

    /**
     * Performs periodic repairs on archived items.
     */
    public void periodicRepair() {
        List<Item> itemsToRepair = new ArrayList<>(archivedItems);
        for (Item i : itemsToRepair) {
            List<Copy> copies = new ArrayList<>(i.getCopies());
            for (Copy c : copies) {
                if (c.getDamaged()) {
                    requestRepair(i);
                    break;
                }
            }
        }
    }

    /**
     * Retrieves the total number of repairs performed.
     *
     * @return The total number of repairs.
     */
    public int getRepairCount() {
        return repairCount;
    }

    /**
     * Retrieves the list of archived items.
     *
     * @return The list of archived items.
     */
    public List<Item> getArchivedItems() {
        return archivedItems;
    }
}