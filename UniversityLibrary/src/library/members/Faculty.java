package library.members;

import java.util.UUID;

/**
 * Represents a faculty member in the library system.
 * A faculty member has a unique faculty ID and inherits common member properties and behaviors.
 * 
 * <p>Extends the {@link Member} class.</p>
 * 
 * @author mianm
 */
public class Faculty extends Member {
    private final String facultyId = UUID.randomUUID().toString();

    /**
     * Initializes a new instance of the Faculty class.
     *
     * @param id    The unique ID of the faculty member.
     * @param name  The name of the faculty member.
     * @param email The email address of the faculty member.
     */
    public Faculty(String id, String name, String email) {
        super(id, name, email);
    }

    /**
     * Retrieves the unique faculty ID.
     *
     * @return The unique faculty ID.
     */
    public String getFacultyId() {
        return facultyId;
    }
}