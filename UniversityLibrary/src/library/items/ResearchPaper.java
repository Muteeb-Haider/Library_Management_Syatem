package library.items;

/**
 * Represents a research paper in the library system.
 * A research paper is a type of item that includes additional details such as the DOI and conference.
 * 
 * <p>Extends the {@link Item} class.</p>
 * 
 * @author mianm
 */
public class ResearchPaper extends Item {
    private final String doi;
    private final String conference;

    /**
     * Initializes a new instance of the ResearchPaper class.
     *
     * @param doi          The Digital Object Identifier (DOI) of the research paper.
     * @param conference   The conference where the research paper was presented.
     * @param id           The unique ID of the research paper.
     * @param title        The title of the research paper.
     * @param author       The author of the research paper.
     * @param pageCount    The number of pages in the research paper.
     * @param topic        The topic of the research paper.
     * @param copiesCount  The number of copies available in the library.
     */
    public ResearchPaper(String doi, String conference, String id, String title, String author, int pageCount, String topic, int copiesCount) {
        super(id, title, author, pageCount, topic, copiesCount);
        this.doi = doi;
        this.conference = conference;
    }

    /**
     * Retrieves the DOI of the research paper.
     *
     * @return The DOI of the research paper.
     */
    public String getDoi() {
        return doi;
    }

    /**
     * Retrieves the conference where the research paper was presented.
     *
     * @return The conference of the research paper.
     */
    public String getConference() {
        return conference;
    }
}