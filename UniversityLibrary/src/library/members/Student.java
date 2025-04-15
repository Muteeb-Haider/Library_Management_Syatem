package library.members;

import java.util.UUID;

/**
 * Represents a student member in the library system.
 * A student has a unique student ID and inherits common member properties and behaviors.
 * 
 * <p>Extends the {@link Member} class.</p>
 * 
 * @author mianm
 */
public class Student extends Member {
    private final String studentId = UUID.randomUUID().toString();

    /**
     * Initializes a new instance of the Student class.
     *
     * @param id    The unique ID of the student.
     * @param name  The name of the student.
     * @param email The email address of the student.
     */
    public Student(String id, String name, String email) {
        super(id, name, email);
    }

    /**
     * Retrieves the unique student ID.
     *
     * @return The unique student ID.
     */
    public String getStudentId() {
        return studentId;
    }
}