package library.members;

/**
 * Represents an alumni member in the library system.
 * Alumni inherit common member properties and behaviors.
 * 
 * <p>Extends the {@link Member} class.</p>
 * 
 * @author mianm
 */
public class Alumni extends Member {

    /**
     * Initializes a new instance of the Alumni class.
     *
     * @param id    The unique ID of the alumni.
     * @param name  The name of the alumni.
     * @param email The email address of the alumni.
     */
    public Alumni(String id, String name, String email) {
        super(id, name, email);
    }
}