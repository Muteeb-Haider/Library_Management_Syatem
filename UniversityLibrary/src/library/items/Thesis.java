package library.items;

/**
 * Represents a thesis in the library system.
 * A thesis is a type of item that includes additional details such as the degree and university.
 * 
 * <p>Extends the {@link Item} class.</p>
 * 
 * @author mianm
 */
public class Thesis extends Item {
    private final String degree;
    private final String university;

    /**
     * Initializes a new instance of the Thesis class.
     *
     * @param degree      The degree associated with the thesis (e.g., "PhD").
     * @param university  The university where the thesis was submitted.
     * @param id          The unique ID of the thesis.
     * @param title       The title of the thesis.
     * @param author      The author of the thesis.
     * @param pageCount   The number of pages in the thesis.
     * @param topic       The topic of the thesis.
     * @param copiesCount The number of copies available in the library.
     */
    public Thesis(String degree, String university, String id, String title, String author, int pageCount, String topic, int copiesCount) {
        super(id, title, author, pageCount, topic, copiesCount);
        this.degree = degree;
        this.university = university;
    }

    /**
     * Retrieves the degree associated with the thesis.
     *
     * @return The degree of the thesis.
     */
    public String getDegree() {
        return degree;
    }

    /**
     * Retrieves the university where the thesis was submitted.
     *
     * @return The university of the thesis.
     */
    public String getUniversity() {
        return university;
    }
}