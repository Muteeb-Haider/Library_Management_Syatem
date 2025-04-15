package library;

import library.items.Item;
import library.members.Member;
import library.members.RepairSection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents the University Library system that manages items, members, transactions, and repairs.
 * Handles borrowing and returning of items, repair requests, and tracks the most popular or viewed items.
 */
public class UniversityLibrary {
    private List<Item> items;
    private List<Member> members;
    private List<Transaction> activeTransactions;
    private RepairSection repairSection;

    /**
     * Constructor that initializes the library with empty lists for items, members, and transactions,
     * and creates a new repair section for damaged items.
     */
    public UniversityLibrary() {
        items = new ArrayList<>();
        members = new ArrayList<>();
        activeTransactions = new ArrayList<>();
        repairSection = new RepairSection();
    }

    /**
     * Adds an item to the library's collection.
     * 
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Registers a member to the library system.
     * 
     * @param member The member to be registered.
     */
    public void registerMember(Member member) {
        members.add(member);
    }

    /**
     * Borrows an item for a member if the member and item are valid and the item is available.
     * 
     * @param memberId The ID of the member borrowing the item.
     * @param itemId The ID of the item being borrowed.
     * @return True if the borrowing is successful, otherwise false.
     */
    public boolean borrowItem(String memberId, String itemId) {
        Member member = findMemberById(memberId);
        Item item = findItemById(itemId);

        if (member != null && item != null && member.borrowResource(item)) {
            String transactionId = "TX" + (activeTransactions.size() + 1);
            Transaction transaction = new Transaction(transactionId, "Borrow", new Date(), member, item);
            activeTransactions.add(transaction);
            return true;
        }
        return false;
    }

    /**
     * Returns an item for a member and marks it as damaged if applicable.
     * Requests repair if the item is damaged.
     * 
     * @param memberId The ID of the member returning the item.
     * @param itemId The ID of the item being returned.
     * @param damaged Indicates whether the item is damaged or not.
     */
    public void returnItem(String memberId, String itemId, boolean damaged) {
        Member member = findMemberById(memberId);
        Item item = findItemById(itemId);

        if (member != null && item != null) {
            member.returnResource(item, damaged);
            String transactionId = "TX" + (activeTransactions.size() + 1);
            Transaction transaction = new Transaction(transactionId, "Return", new Date(), member, item);
            activeTransactions.add(transaction);
            if (damaged) {
                repairSection.requestRepair(item);
            }
        }
    }

    /**
     * Searches for a member by their ID.
     * 
     * @param memberId The ID of the member to find.
     * @return The member with the given ID, or null if not found.
     */
    private Member findMemberById(String memberId) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId().equals(memberId)) {
                return members.get(i);
            }
        }
        return null;
    }

    /**
     * Searches for an item by its ID.
     * 
     * @param itemId The ID of the item to find.
     * @return The item with the given ID, or null if not found.
     */
    private Item findItemById(String itemId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(itemId)) {
                return items.get(i);
            }
        }
        return null;
    }

    /**
     * Checks for damaged items and sends them for repair if needed.
     */
    public void checkAndRepairDamagedItems() {
        repairSection.periodicRepair();
    }

    /**
     * Searches for items by title.
     * 
     * @param title The title to search for.
     * @return A list of items whose titles contain the given title.
     */
    public List<Item> searchByTitle(String title) {
        List<Item> result = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            String itemTitle = items.get(i).getTitle();
            if (itemTitle.toLowerCase().contains(title.toLowerCase())) {
                result.add(items.get(i));
            }
        }
        return result;
    }

    /**
     * Searches for items by author.
     * 
     * @param author The author to search for.
     * @return A list of items written by the given author.
     */
    public List<Item> searchByAuthor(String author) {
        List<Item> result = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            String itemAuthor = items.get(i).getAuthor();
            if (itemAuthor.toLowerCase().contains(author.toLowerCase())) {
                result.add(items.get(i));
            }
        }
        return result;
    }

    /**
     * Retrieves the most popular items in the library based on the number of times they have been borrowed.
     * 
     * @return A list of the top 5 most popular items, sorted by borrow count.
     */
    public List<Item> getMostPopularItems() {
        List<Item> sorted = new ArrayList<>(items);

        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                if (sorted.get(j).getBorrowCount() < sorted.get(j + 1).getBorrowCount()) {
                    Item temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }

        List<Item> result = new ArrayList<>();
        int limit = Math.min(5, sorted.size());
        for (int i = 0; i < limit; i++) {
            result.add(sorted.get(i));
        }

        return result;
    }

    /**
     * Retrieves the most popular item in the library based on the number of times it has been borrowed.
     * 
     * @return The most popular item, or null if the library has no items.
     */
    public Item getMostPopularItem() {
        if (items.isEmpty()) return null;

        Item mostPopular = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            if (items.get(i).getBorrowCount() > mostPopular.getBorrowCount()) {
                mostPopular = items.get(i);
            }
        }
        return mostPopular;
    }

    /**
     * Retrieves the most viewed digital item.
     * 
     * @return The most viewed digital item, or null if there are no digital items or views.
     */
    public Item getMostViewedDigitalItem() {
        Item result = null;
        int maxViews = -1;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getDigitalVersion() != null) {
                int views = items.get(i).getDigitalVersion().getViews();
                if (views > maxViews) {
                    maxViews = views;
                    result = items.get(i);
                }
            }
        }

        return result;
    }

    /**
     * Retrieves the most downloaded digital item.
     * 
     * @return The most downloaded digital item, or null if there are no digital items or downloads.
     */
    public Item getMostDownloadedDigitalItem() {
        Item result = null;
        int maxDownloads = -1;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getDigitalVersion() != null) {
                int downloads = items.get(i).getDigitalVersion().getDownloads();
                if (downloads > maxDownloads) {
                    maxDownloads = downloads;
                    result = items.get(i);
                }
            }
        }

        return result;
    }

    /**
     * Retrieves a list of all items in the library.
     * 
     * @return A list of all items.
     */
    public List<Item> getItems() {
        List<Item> copy = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            copy.add(items.get(i));
        }
        return copy;
    }

    /**
     * Retrieves a list of all members in the library.
     * 
     * @return A list of all members.
     */
    public List<Member> getMembers() {
        List<Member> copy = new ArrayList<>();
        for (int i = 0; i < members.size(); i++) {
            copy.add(members.get(i));
        }
        return copy;
    }

    /**
     * Retrieves a list of active transactions in the library.
     * 
     * @return A list of active transactions.
     */
    public List<Transaction> getActiveTransactions() {
        return activeTransactions;
    }

    /**
     * Retrieves the repair section of the library.
     * 
     * @return The repair section.
     */
    public RepairSection getRepairSection() {
        return repairSection;
    }
}
