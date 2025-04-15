package library;

import library.items.Book;
import java.io.*;
import library.items.Item;
import library.members.Alumni;
import library.members.Faculty;
import library.members.Member;
import library.members.Student;

/**
 * Entry point for the University Library system.
 * <p>
 * This class initializes the library, loads item and member data from files,
 * performs some demonstration operations, and handles errors in data loading.
 * </p>
 */
public class UniversityLibraryMain {
    private static final String ITEMS_FILE = "C:\\Users\\mianm\\Desktop\\oop\\UniversityLibrary\\src\\library\\items.txt";
    private static final String MEMBERS_FILE = "C:\\Users\\mianm\\Desktop\\oop\\UniversityLibrary\\src\\library\\members.txt";

    /**
     * Main method to start the library system.
     * Loads items and members from files and performs demo operations.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        UniversityLibrary library = new UniversityLibrary();

        System.out.println("Starting Library System...");
        System.out.println("Items file: " + ITEMS_FILE);
        System.out.println("Members file: " + MEMBERS_FILE);

        if (!new File(ITEMS_FILE).exists()) {
            System.err.println("ERROR: Items file not found at specified path");
            System.err.println("Please verify the file exists at: " + ITEMS_FILE);
            return;
        }

        if (!new File(MEMBERS_FILE).exists()) {
            System.err.println("ERROR: Members file not found at specified path");
            System.err.println("Please verify the file exists at: " + MEMBERS_FILE);
            return;
        }

        System.out.println("\nLoading data files...");
        loadItemsFromFile(ITEMS_FILE, library);
        loadMembersFromFile(MEMBERS_FILE, library);

        if (library.getItems().isEmpty()) {
            System.err.println("WARNING: No items were loaded - check items file format");
        }

        if (library.getMembers().isEmpty()) {
            System.err.println("WARNING: No members were loaded - check members file format");
        }

        if (library.getMembers().isEmpty() || library.getItems().isEmpty()) {
            System.err.println("Cannot continue - essential data missing");
            return;
        }

        System.out.println("\nLibrary initialized with:");
        System.out.println("- " + library.getItems().size() + " items");
        System.out.println("- " + library.getMembers().size() + " members");

        demoLibraryOperations(library);
    }

    /**
     * Loads library items from a specified file into the library.
     * Expected format per line: id title author pageCount topic type copies
     *
     * @param fileName The path to the items file.
     * @param library  The UniversityLibrary instance to populate.
     */
    public static void loadItemsFromFile(String fileName, UniversityLibrary library) {
        System.out.println("\nLoading items from: " + fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNum = 0;
            int loadedCount = 0;

            while ((line = reader.readLine()) != null) {
                lineNum++;
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] data = line.split(" ");
                if (data.length < 7) {
                    System.err.println("Line " + lineNum + ": Skipped - expected 7 fields, got " + data.length);
                    continue;
                }

                try {
                    String id = data[0];
                    String title = data[1].replace("_", " ");
                    String author = data[2].replace("_", " ");
                    int pageCount = Integer.parseInt(data[3]);
                    String topic = data[4].replace("_", " ");
                    String type = data[5];
                    int copies = Integer.parseInt(data[6]);

                    Item item;

                    switch (type.toLowerCase()) {
                        case "book":
                            item = new Book(id, title, author, pageCount, topic, copies);
                            break;
                        default:
                            System.err.println("Line " + lineNum + ": Unknown item type - " + type);
                            continue;
                    }

                    library.addItem(item);
                    loadedCount++;
                } catch (NumberFormatException e) {
                    System.err.println("Line " + lineNum + ": Skipped - invalid number format");
                }
            }
            System.out.println("Successfully loaded " + loadedCount + " items");
        } catch (IOException e) {
            System.err.println("Error loading items file: " + e.getMessage());
        }
    }

    /**
     * Loads library members from a specified file into the library.
     * Expected format per line: id name department category
     * where category is one of: student, faculty, alumni
     *
     * @param fileName The path to the members file.
     * @param library  The UniversityLibrary instance to populate.
     */
    public static void loadMembersFromFile(String fileName, UniversityLibrary library) {
        System.out.println("\nLoading members from: " + fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNum = 0;
            int loadedCount = 0;

            while ((line = reader.readLine()) != null) {
                lineNum++;
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] data = line.split(" ");
                if (data.length < 4) {
                    System.err.println("Line " + lineNum + ": Skipped - expected 4 fields, got " + data.length);
                    continue;
                }

                try {
                    Member member = null;
                    String category = data[3].trim().toLowerCase();

                    switch (category) {
                        case "student":
                            member = new Student(data[0].trim(), data[1].replace("_", " "), data[2].trim());
                            break;
                        case "faculty":
                            member = new Faculty(data[0].trim(), data[1].replace("_", " "), data[2].trim());
                            break;
                        case "alumni":
                            member = new Alumni(data[0].trim(), data[1].replace("_", " "), data[2].trim());
                            break;
                        default:
                            System.err.println("Line " + lineNum + ": Unknown member category - " + category);
                    }

                    if (member != null) {
                        library.registerMember(member);
                        loadedCount++;
                    }
                } catch (Exception e) {
                    System.err.println("Line " + lineNum + ": Skipped - invalid format");
                }
            }
            System.out.println("Successfully loaded " + loadedCount + " members");
        } catch (IOException e) {
            System.err.println("Error loading members file: " + e.getMessage());
        }
    }

    /**
     * Demonstrates some core operations of the library system:
     * borrowing, returning (with/without damage), and repair.
     *
     * @param library The UniversityLibrary instance to operate on.
     */
    private static void demoLibraryOperations(UniversityLibrary library) {
        System.out.println("\nRunning demo operations...");

        Member firstMember = library.getMembers().get(0);
        Item firstItem = library.getItems().get(0);

        System.out.println("\nTesting with first member: " + firstMember.getName());
        System.out.println("Testing with first item: " + firstItem.getTitle());

        System.out.println("\n1. Borrowing item:");
        boolean borrowResult = library.borrowItem(firstMember.getId(), firstItem.getId());
        System.out.println("Borrow result: " + (borrowResult ? "SUCCESS" : "FAILED"));

        System.out.println("\n2. Returning item:");
        library.returnItem(firstMember.getId(), firstItem.getId(), false);
        System.out.println("Return completed");

        System.out.println("\n3. Testing damaged return:");
        library.returnItem(firstMember.getId(), firstItem.getId(), true);
        System.out.println("Damaged return processed");

        System.out.println("\n4. Running repairs:");
        library.checkAndRepairDamagedItems();
        System.out.println("Repairs completed");

        System.out.println("\n5. Checking popular items:");
        Item popular = library.getMostPopularItem();
        System.out.println("Most popular item: " + (popular != null ? popular.getTitle() : "None"));
    }
}
