
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.*;

/**
 * Represents a member of a gym. A member can have many assessments, which are stored
 * using a HashMap. The 'key' of this HashMap is the assessment date, and the value is
 * an object of type Assessment. This is a subclass of Object type Person.
 */

public abstract class Member extends Person
{
    /**
     * The height of this Person, measured in metres(m).
     */
    private float height;

    /**
     * The startWeight of this Person, measured in kilogrammes(Kg).
     */
    private float startWeight;

    /**
     * The package of the gym that this Person is currently subscribed to.
     */
    protected String chosenPackage;

    private char user;


    char getUser() {
        return user;
    }

    void setUser(char user) {
        this.user = user;
    }

    //http://www.java2s.com/Tutorials/Java/Java_Collection/0110__Java_Sorted_Set.htm

    public String getChosenPackage() {
        return chosenPackage;
    }

    protected HashMap<String, Assessment> assessmentMap = new HashMap<>();

    public HashMap<String, Assessment> getAssessments()
    {
        return assessmentMap;
    }

    //protected SortedSet<String> sortedDates = new TreeSet<>();


    /**
     * Creates a new Member with the given email, name, address, gender, height from the superclass
     * constructor, Person, and startWeight, and chosenPackage from Member subclass.
     * @param email this Member's email address
     * @param name this Member's first and last name
     * @param address this Member's postal address
     * @param gender this Member's gender
     * @param height this Member's height, measured in metres(m)
     * @param startWeight this Member's starting weight, measured in kilogrammes(Kg)
     * @param chosenPackage this Member's chosen package at the gym
     * @see Person
     */
    public Member(String email, String name, String address,
                  String gender, float height, float startWeight, String chosenPackage)
    {
        super(email, name, address, gender);
        setHeight(height);
        setStartWeight(startWeight);
        setChosenPackage(chosenPackage);
        this.assessmentMap = getAssessments();
    }


    /**
     * Default constructor for Member
     */
    public Member(){

    }

    /**
     * Allows the setting of the parent class field name. This is implemented in the editProfile
     * methods of MenuController, to allow this member to amend certain fields.
     * @param name This Person's new name.
     */

    public void setName(String name)
    {
        super.setName(name);
    }

    /**
     * Allows the setting of the parent class field address. This is implemented in the editProfile
     * methods of MenuController, to allow this member to amend certain fields.
     * @param address This Person's new address.
     */
    public void setAddress(String address)
    {
        super.setAddress(address);
    }

    /**
     * Allows the setting of the parent class field gender. This is implemented in the editProfile
     * methods of MenuController, to allow this member to amend certain fields.
     * @param gender This Person's new gender.
     */
    public void setGender(String gender)
    {
        super.setGender(gender);
    }

    /**
     * Sets the package choice using constraints for Student or Premium. The appropriate package description
     * is called from a HashMap, created in the MenuController class.
     * @param chosenPackage
     */
    protected abstract void setChosenPackage(String chosenPackage);

    /**
     * Gets the height of this Member
     * @return this Member's height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Changes the height value of this Member
     * @param height This Member's new height
     */
    public void setHeight(float height)
    {
        if((height >= 1.00) && (height <= 3.00))
        {
            this.height = height;
        }
        else
        {
            this.height = 0;
        }
    }

    /**
     * Gets the startWeight of this Member
     * @return this Member's startWeight
     */
    public float getStartWeight()
    {
        return startWeight;
    }

    /**
     * Changes the startWeight of this Member
     * @param startWeight This Member's new startWeight
     */
    public void setStartWeight(float startWeight)
    {
        if((startWeight >= 35.00) && (startWeight <= 250.00))
        {
            this.startWeight = startWeight;
        } else
        {
            this.startWeight = 0;
        }
    }

    /**
     * Creates a SortedSet of dates, using keys of this assessment HashMap. It changes the HashSet of keys
     * to TreeSet of keys, which in turn is added to the SortedSet of dates.
     * @return this Member's set of assessement dates,sorted from
     */
    public SortedSet<String> sortedAssessmentDates()
    {
        //https://www.geeksforgeeks.org/program-to-convert-hashmap-to-treemap-in-java/
        //https://beginnersbook.com/2014/08/how-to-convert-a-hashset-to-a-treeset/
        SortedSet<String> sortedDates = new TreeSet<>();

        if (!getAssessments().isEmpty())
        {
            Set<String> keys = new HashSet<>();
            keys = getAssessments().keySet();
            Set<String> hashsetToTreeSet = new TreeSet<>(keys);
            hashsetToTreeSet.addAll(keys);
            sortedDates.addAll(keys);
            return sortedDates;
        }
        return sortedDates;
    }


    public abstract void setChosenPackage();


    /**
     * Creates a String object that holds the current Member values of the superclass Person.toString() and
     * the height, startWeight and chosenPackage of this Member.
     * @return a string showing this Member's field values.
     * @see Person
     */
    public String toString()
    {
        String person = super.toString();
        return  person + "\nHeight : " + height + "m, Start Weight : " + startWeight
                + "Kg, Chosen Package : " + chosenPackage + " ";
    }


    /**
     *
     * @return
     */
    public Assessment latestAssessment()
    {
        //Returns the latest assessment based on last entry (by calendar date).
        if(assessmentMap.isEmpty())
        {
            return null;
        }

        return assessmentMap.get(sortedAssessmentDates().last());


    }

    //https://www.geeksforgeeks.org/program-to-convert-hashmap-to-treemap-in-java/
    public static <K, V> Map<K, V> convertToTreeMap(Map<K, V> hashMap)
        {
            Map<K, V>
            treeMap = hashMap
                    .entrySet()
            .stream()
            .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue,
                             newValue)
                                    -> newValue,
                            TreeMap::new));

    // Return the TreeMap
    return treeMap;
    }





}

