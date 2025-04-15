package library.members;

import library.items.Item;
import library.items.Copy;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library member.
 * A member can borrow and return items, and their borrowing history is tracked.
 * 
 * <p>This class is intended to be extended by specific member types such as students, faculty, etc.</p>
 * 
 * @author mianm
 */
public class Member {
    private final String id, name, email;
    private final List<Item> currBorrowedItems;
    private final List<Item> allBorrowedItems;
    private int totborrowCount, borrowLimit, loyalityPoints;

    /**
     * Initializes a new instance of the Member class.
     *
     * @param id    The unique ID of the member.
     * @param name  The name of the member.
     * @param email The email address of the member.
     */
    public Member(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.totborrowCount = 0;
        this.borrowLimit = 3;
        this.loyalityPoints = 0;
        this.currBorrowedItems = new ArrayList<>();
        this.allBorrowedItems = new ArrayList<>();
    }

    /**
     * Attempts to borrow an item.
     *
     * @param res The item to be borrowed.
     * @return True if the item was successfully borrowed, false otherwise.
     */
    public boolean borrowResource(Item res) {
        if (currBorrowedItems.size() < borrowLimit && res.borrowResource()) {
            currBorrowedItems.add(res);
            return true;
        }
        return false;
    }

    /**
     * Returns an item to the library.
     *
     * @param res    The item to be returned.
     * @param damage True if the item is damaged, false otherwise.
     */
    public void returnResource(Item res, boolean damage) {
        List<Item> itemsToRemove = new ArrayList<>();
        for (Item i : currBorrowedItems) {
            if (i.getId().equals(res.getId())) {
                itemsToRemove.add(i);
                allBorrowedItems.add(i);
                totborrowCount++;
                for (Copy cp : i.getCopies()) {
                    if (!cp.getAvailable()) {
                        i.returnResource(cp);
                        if (damage) {
                            cp.setDamaged(true);
                            cp.increaseDamage();
                        }
                        break;
                    }
                }
                if (!damage && totborrowCount % 3 == 0) {
                    loyalityPoints++;
                    borrowLimit++;
                }
            }
        }
        currBorrowedItems.removeAll(itemsToRemove);
    }

    // Getters with Javadoc omitted for brevity

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Item> getCurrBorrowedItems() {
        return currBorrowedItems;
    }

    public List<Item> getAllBorrowedItems() {
        return allBorrowedItems;
    }

    public int getTotborrowCount() {
        return totborrowCount;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public int getLoyalityPoints() {
        return loyalityPoints;
    }

}