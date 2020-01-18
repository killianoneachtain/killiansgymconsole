import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Represents a PremiumMember, which is a subclass of Object type Member. It stores no additional
 * information from Member
 */
public class PremiumMember extends Member
{

    public Assessment latestAssessment() {
        //Returns the latest assessment based on last entry (by calendar date).
        if (assessmentMap.isEmpty()) {
            return null;
        }

        return assessmentMap.get(sortedAssessmentDates().last());
    }

    /**
     * Creates a new PremiumMember with the given email, name, address and gender from the superclass
     * constructor, Person; height and startWeight, and chosenPackage from Member superclass.
     * @param email this PremiumMember's email address
     * @param name this PremiumMember's first and last name
     * @param address this PremiumMember's postal address
     * @param gender this PremiumMember's gender
     * @param height this PremiumMember's height, measured in metres(m)
     * @param startWeight this PremiumMember's starting weight, measured in kilogrammes(Kg)
     * @param chosenPackage this PremiumMember's chosen package at the gym
     * @see Member
     */
    public PremiumMember(String email, String name, String address,
                         String gender, float height, float startWeight, String chosenPackage)
    {
        super(email, name, address, gender, height, startWeight, chosenPackage);
        super.setUser('p');
    }

    public PremiumMember() {
    }

    private void setUser()
    {
        super.setUser('p');
    }

    protected HashMap<String,Assessment> assessmentMap = new HashMap<>();

    public HashMap<String, Assessment> getAssessments() {
        return assessmentMap;
    }



    //https://www.geeksforgeeks.org/sortedset-java-examples/

    @Override
    protected void setChosenPackage(String chosenPackage)
    {
        if (chosenPackage.equals("Package 1"))
        {
            super.chosenPackage = "Package 1";
        }
        else if (chosenPackage.equals("Package 2"))
        {
            super.chosenPackage = "Package 2";
        }
        else if (chosenPackage.equals("Package 3"))
        {
            super.chosenPackage = "Package 3";
        }
        else
        {
            System.err.println("You chose an invalid option");
            super.chosenPackage = "No Package Chosen";
        }
    }

    @Override
    public void setChosenPackage() {

    }

    /**
     * Creates a String object that holds the current PremiumMember values of the superclass Member.toString()
     * @return a string showing this PremiumMember's field values.
     * @see Member
     */
    public String toString()
    {
        return super.toString();
    }
}
