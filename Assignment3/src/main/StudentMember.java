import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;

/**
 * Represents a StudentMember, which is a subclass of Object type Member. It stores Member information
 * and additional fields of studentID and collegeName.
 * @see Member
 */

public class StudentMember extends Member
{
    public ArrayList<String> assessmentDates = new ArrayList<>();



    /**
     * The studentID number of this StudentMember
     */
    private int studentId;

    /**
     * The college name of this StudentMember
     */
    private String collegeName;

    private void setUser()
    {
        super.setUser('s');
    }

    /**
     * Gets the studentID number of this StudentMember
     * @return This StudentMembers's Identification number
     */
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    public Assessment latestAssessment() {
        //Returns the latest assessment based on last entry (by calendar date).
        if (assessmentMap.isEmpty()) {
            return null;
        }

        return assessmentMap.get(sortedAssessmentDates().last());
    }

    /**
     * Gets the college name of this Student
     * @return This StudentMember's college name
     */
    public String getCollegeName() {
        return collegeName;
    }

    protected HashMap<String,Assessment> assessmentMap = new HashMap<>();

    public HashMap<String, Assessment> getAssessments() {
        return assessmentMap;
    }

    public StudentMember()
    {}

    /**
     * Creates a new StudentMember with the given email, name, address, gender, height from the superclass
     * constructor, Person; height, startWeight, and chosenPackage from Member subclass; it also stores
     * the studentID and collegeName of this StudentMember.
     * @param email this StudentMember's email address
     * @param name this StudentMember's first and last name
     * @param address this StudentMember's postal address
     * @param gender this StudentMember's gender
     * @param height this StudentMember's height, measured in metres(m)
     * @param startWeight this StudentMember's startWeight, measured in kilogrammes(Kg)
     * @param chosenPackage this StudentMember's chosenPackage (1/2/3/WIT)
     * @param studentId this StudentMember's student Identification number
     * @param collegeName this StudentMember's college name
     *
     * @see Person
     * @see Member
     */
    public StudentMember(String email, String name, String address,
                         String gender, float height, float startWeight, String chosenPackage,
                         int studentId, String collegeName)
    {
        super (email, name, address, gender, height, startWeight, chosenPackage);
        this.studentId = studentId;
        this.collegeName = collegeName;
        super.setUser('s');
    }

    @Override
    protected void setChosenPackage(String chosenPackage)
    {
        if (chosenPackage.equals("WIT") && (collegeName.equals("WIT")))
        {
            super.chosenPackage = "WIT";
        }
        else
        {
            super.chosenPackage = "Package 3";
        }
    }

    @Override
    public void setChosenPackage()
    {

    }

    /**
     * Creates a String object that holds the current StudentMember values of the superclass Member.toString()
     * the studentID and collegeNAme of this StudentMember.
     * @return a string showing this StudentMember's field values.
     * @see Member
     */
    public String toString()
    {
        String member = super.toString();
        return ", " + member + "Student ID Number : " + studentId + ", College Name : " + collegeName + ".";
    }

}
