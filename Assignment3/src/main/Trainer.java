/**
 * Represents a Person who is a Trainer at the Gym. This is regarded as a subclass of Object Person.
 * A Trainer can view, add and delete members from the Gym.
 */

public class Trainer extends Person
{
    /**
     * The specialty knowledge that the Trainer has in the gym.
     */
    private String specialty;

    /**
     * Gets the specialty of the Trainer.
     * @return this Trainer's specialty.
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Changes the specialty of this Trainer.
     * @param specialty This Trainer's new specialty.
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /**
     * Creates a new Trainer with the given email, name, address, gender and specialty.
     * @param email This Trainer's email address.
     * @param name This Trainer's name.
     * @param address This Trainer's address.
     * @param gender This Trainer's gender.
     * @param specialty This Trainer's specialty.
     */
    public Trainer(String email, String name, String address, String gender, String specialty)
    {
        super(email, name, address, gender);
        this.specialty = specialty;
    }

    /**
     * Creates a String object that holds the current Trainer values of the superclass Person.toString() and
     * specialty of this Trainer.
     * @return a string showing this Trainer's field values.
     * @see Person
     */
    public String toString()
    {
        String person = super.toString();
        return person + ", Specialty : " + specialty;
    }
}
