package library;

import java.util.Date;
import library.items.Item;
import library.members.Member;

/**
 * Represents a transaction in the library system, such as borrowing or returning an item.
 * Each transaction is associated with a member and an item.
 * 
 * @author mianm
 */
public class Transaction {
    private final String trascationId;
    private final String trascationType;
    private final Date date;
    private final Member member;
    private final Item item;

    /**
     * Initializes a new instance of the Transaction class.
     *
     * @param trascationId   The unique ID of the transaction.
     * @param trascationType The type of the transaction (e.g., "Borrow", "Return").
     * @param date           The date of the transaction.
     * @param member         The member involved in the transaction.
     * @param item           The item involved in the transaction.
     */
    public Transaction(String trascationId, String trascationType, Date date, Member member, Item item) {
        this.trascationId = trascationId;
        this.trascationType = trascationType;
        this.date = date;
        this.member = member;
        this.item = item;
    }

    /**
     * Retrieves the transaction ID.
     *
     * @return The transaction ID.
     */
    public String getTrascationId() {
        return trascationId;
    }

    /**
     * Retrieves the transaction type.
     *
     * @return The transaction type.
     */
    public String getTrascationType() {
        return trascationType;
    }

    /**
     * Retrieves the date of the transaction.
     *
     * @return The date of the transaction.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Retrieves the member involved in the transaction.
     *
     * @return The member involved in the transaction.
     */
    public Member getMember() {
        return member;
    }

    /**
     * Retrieves the item involved in the transaction.
     *
     * @return The item involved in the transaction.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns a string representation of the transaction.
     *
     * @return A string representation of the transaction.
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "trascationId='" + trascationId + '\'' +
                ", trascationType='" + trascationType + '\'' +
                ", date=" + date +
                ", member=" + member +
                ", item=" + item +
                '}';
    }
}