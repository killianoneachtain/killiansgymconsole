/**
 *  Represents a person who is registered at the gym. This is regarded as a superclass of which there
 *  are two direct subclasses, Member and Trainer. A person can be either a member or a trainer.
 */


public class Person {
    /**
     * The first and last name of this person.
     */
    private String name;

    /**
     * The email address of this person.
     */
    private String email;
    /**
     * The full address of this person.
     */
    private String address;
    /**
     * The gender of this person, stored as string of either "M" or "F".
     */
    private String gender;

    /**
     * Changes the name of this Person.
     * @param name This Person's new name.
     *             It should include their first and last name.
     */
    public void setName(String name)
    {
        if (name.length() > 30)
        {
            this.name = name.substring(0, 30);
        }
        else
        {
            this.name = name;
        }
    }

    /**
     * Changes the address of this Person.
     * @param address This Person's new address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Changes the gender of this Person.
     * @param gender This Person's new gender.
     *               Either "F" or "M".
     */
    public void setGender(String gender)
    {
        if ((gender.equals("F")) || (gender.equals("M")))
        {
            this.gender = gender;
        } else
        {
            gender = gender.replaceAll("(.*.)", "Unspecified");
            this.gender = gender;
        }
    }

    /**
     * Gets the first and last name of this Person.
     * @return this Person's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the email address of this Person.
     * @return this Person's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the address of this Person.
     * @return this Person's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the gender of this Person. (Either "M' of "F")
     * @return this Person's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * The default constructor of type Person.
     * Creates an object of type Person, with no parameters.
     */
    public Person()
    {

    }

    /**
     * Creates a new Person with the given email, name, address and gender.
     * @param email This Person's email address.
     * @param name This Person's name.
     * @param address This Person's address.
     * @param gender This Person's gender.
     */

    public Person(String email, String name, String address, String gender)
    {
        setName(name);
        this.email = email;
        this.address = address;
        setGender(gender);
    }

    /**
     * Creates a String object that holds the current values of name, email, address and gender of
     * this Person
     * @return a string showing this Person's field values.
     */
    public String toString()
    {
        return ("\nName : " + name + ", Email : " + email + ", Address : " + address + ", Gender : " + gender);
    }
}
