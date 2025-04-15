package library;

import library.items.Book;
import library.items.Item;
import library.members.Faculty;
import library.members.Student;
import library.members.Member;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Unit test class for testing the functionality of the UniversityLibrary system.
 * Contains test cases for adding items, registering members, borrowing and returning items,
 * searching items by title and author, and getting the most popular item.
 */
public class UniversityLibraryTest {

    private UniversityLibrary library;
    private Item book1;
    private Item book2;
    private Member student;
    private Member faculty;

    /**
     * Sets up the test environment by initializing the library, items, and members,
     * and adding them to the system.
     */
    @Before
    public void setUp() {
        library = new UniversityLibrary();
        book1 = new Book("1", "Clean Code", "Robert Martin", 464, "Software Engineering", 3);
        book2 = new Book("2", "Design Patterns", "Erich Gamma", 395, "Software Engineering", 2);
        student = new Student("100", "Alice", "alice@uni.edu");
        faculty = new Faculty("200", "Bob", "bob@uni.edu");
        library.addItem(book1);
        library.addItem(book2);
        library.registerMember(student);
        library.registerMember(faculty);
    }

    /**
     * Test case to verify that items and members are added correctly to the library system.
     */
    @Test
    public void testAddItemAndRegisterMember() {
        assertEquals(2, library.getItems().size());
        assertEquals(2, library.getMembers().size());
    }

    /**
     * Test case to verify that an item can be successfully borrowed by a valid member.
     */
    @Test
    public void testBorrowItemSuccess() {
        assertTrue(library.borrowItem("100", "1"));
        assertEquals(1, library.getActiveTransactions().size());
    }

    /**
     * Test case to verify that borrowing an item with an invalid item ID fails.
     */
    @Test
    public void testBorrowItemInvalidItem() {
        assertFalse(library.borrowItem("100", "999"));
    }

    /**
     * Test case to verify that borrowing an item with an invalid member ID fails.
     */
    @Test
    public void testBorrowItemInvalidMember() {
        assertFalse(library.borrowItem("999", "1"));
    }

    /**
     * Test case to verify that an item can be successfully returned by a member.
     */
    @Test
    public void testReturnItem() {
        library.borrowItem("100", "1");
        library.returnItem("100", "1", false);
        assertEquals(2, library.getActiveTransactions().size());
    }

    /**
     * Test case to verify that searching for an item by its title returns the correct item.
     */
    @Test
    public void testSearchByTitle() {
        List<Item> result = library.searchByTitle("Clean");
        assertEquals(1, result.size());
        assertEquals("Clean Code", result.get(0).getTitle());
    }

    /**
     * Test case to verify that searching for an item by its author returns the correct item.
     */
    @Test
    public void testSearchByAuthor() {
        List<Item> result = library.searchByAuthor("Gamma");
        assertEquals(1, result.size());
        assertEquals("Design Patterns", result.get(0).getTitle());
    }

    /**
     * Test case to verify that the most popular item is returned based on the number of borrow transactions.
     */
    @Test
    public void testMostPopularItem() {
        library.borrowItem("100", "1");
        library.returnItem("100", "1", false);
        library.borrowItem("200", "1");
        library.returnItem("200", "1", false);
        library.borrowItem("100", "2");
        library.returnItem("100", "2", false);
        assertEquals("Clean Code", library.getMostPopularItem().getTitle());
    }
}
