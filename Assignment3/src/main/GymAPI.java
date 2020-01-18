import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.Arrays;


/**
 * This class operates between the model classes and the menu driver class (see later). It stores:
 *
 * an ArrayList of Member
 * an ArrayList of Trainer
 * an ArrayList of Assessment.
 */

public class GymAPI {
    /**
     * Stores an ArrayList of type Member called members.
     */
    public static ArrayList<Member> members;

    /**
     * Stores an ArrayList of type Trainer called trainers.
     */
    public static ArrayList<Trainer> trainers;

    /**
     * Stores an ArrayList of type Assessment called assessments.
     */
    public static ArrayList<Assessment> assessments;

    /**
     * Sets up an instance of ArrayList's to represent a gym type environment.
     * @see Member
     * @see Trainer
     * @see Assessment
     */
    protected GymAPI()
    {
        members = new ArrayList<Member>();
        trainers = new ArrayList<Trainer>();
    }



    /**
     * Gets the ArrayList of Members for this gym
     * @return this gym's Members
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * Gets the ArrayList of Trainers at this gym
     * @return this gym's Trainer's
     */
    public ArrayList<Trainer> getTrainers() { return trainers; }

    /**
     * Gets the ArrayList of Assessments for this gym
     * @return this gym's Assessments
     */


    /**
     * Adds an instance of type Member to the ArrayList, members, of this gym
     * @param member the instance of type Member to be added
     */
    public void addMember(Member member)
    {
        members.add(member);
    }

    /**
     * Adds an instance of type StudentMember to the ArrayList, members, of this gym
     * @param studentMember the instance of type StudentMember to be added
     */
    public void addStudentMember(StudentMember studentMember) { members.add(studentMember); }

    /**
     * Adds an instance of type PremiumMember to the ArrayList, members, of this gym
     * @param premiumMember the instance of type PremiumMember to be added
     */
    public void addPremiumMember(PremiumMember premiumMember) { members.add(premiumMember); }

    /**
     * Adds an instance of type Trainer to the ArrayList, Trainer, of this gym
     * @param trainer the instance of type Trainer to be added
     */
    public void addTrainer(Trainer trainer)
    {
        trainers.add(trainer);
    }

    /**
     * Adds an instance of type Assessment to the Arraylist, assessments, of this gym
     * @param assessment the instance of type Assessment to be added
     */
    public void addAssessment(Assessment assessment) {  assessments.add(assessment);  }

    /**
     * Gets the size of the Assessment ArrayList
     * @return size of this ArrayList, assessments
     */
    public int numberOfAssessments() { return assessments.size(); }

    /**
     * Gets the size of the Member ArrayList
     * @return size of the ArrayList, members
     */
    public int numberOfMembers() {
        return members.size();
    }

    /**
     * Gets the size of the Trainer ArrayList
     * @return size of the ArrayList, trainers
     */
    public int numberOfTrainers() {
        return trainers.size();
    }

    /**
     * Returns a boolean indicating if the index passed as a parameter is
     * a valid index for the member’s array list.
     * @param index the index number from the ArrayList, members
     * @return returns true if the number passed is a valid index for the ArrayList, members
     */
    public boolean isValidMemberIndex(int index)
    {
        return (index <= numberOfMembers() - 1) && (index >= 0);
    }

    /**
     * Returns a boolean indicating if the index passed as a parameter is
     * a valid index for the trainer’s array list.
     * @param index the index number from the ArrayList, trainers
     * @return returns true if the number passed is a valid index for the ArrayList, trainers
     */
    public boolean isValidTrainerIndex(int index)
    {
        return (index >= 0) && (index <= (numberOfTrainers() - 1));
    }

    /**
     * Searches the ArrayList, members, using the parameter 'email' as the search field.
     * Returns the member object that matches the email entered. If no member matches, return null.
     * @param emailEntered this email address used as the search criterion
     * @return this Object of type Member which has the matching searched email address
     */
    public Member searchMembersByEmail (String emailEntered)
    {
        Member isAMember = null;
        for (Member member:members)
        {
            if(member.getEmail().equals(emailEntered))
            {
               isAMember = member;
            }
        }
        return isAMember;
    }

    /**
     * Searches the ArrayList, members, using the parameter 'name' as the search field.
     * Returns a list of member names that partially or entirely matches the entered name.
     * An empty array is returned when there are no matches.
     * @param nameEntered the 'name' used as the search criterion
     * @return this ArrayList of type String displaying the matching member(s) name
     */
    public ArrayList<String> searchMembersByName (String nameEntered)
    {
        ArrayList<String> matchingMembers = new ArrayList<>();
        for (Member member:members)
        {
            if (member.getName().contains(nameEntered))
            {
                matchingMembers.add(member.getName());
            }
        }

        return matchingMembers;
    }

    /**
     * Searches the ArrayList, trainers, using the parameter 'email' as the search field.
     * Returns the trainer object that matches the email entered. If no trainer matches, return null.
     * @param emailEntered this email address used as the search criterion
     * @return this Object of type Trainer which has the matching searched email address
     */
    public Trainer searchTrainersByEmail (String emailEntered)
    {
        Trainer isATrainer = null;
        for (Trainer trainer:trainers)
        {
            if (trainer.getEmail().equals(emailEntered))
            {
                isATrainer = trainer;
            }
        }
        return isATrainer;
        //Returns the trainer object that matches the email entered. If no trainer matches, return null.
    }

    /**
     * Searches the ArrayList, trainers, using the parameter 'name' as the search field.
     * Returns a list of member names that partially or entirely matches the entered name.
     * An empty array is returned when there are no matches.
     * @param nameEntered the 'name' used as the search criterion
     * @return this ArrayList of type String displaying the matching member(s) name
     */
    public ArrayList<String> searchTrainersByName (String nameEntered)
    {
        ArrayList<String> matchingTrainers = new ArrayList<>();
        for (Trainer trainer:trainers)
        {
            if (trainer.getName().contains(nameEntered))
            {
                matchingTrainers.add(trainer.getName());
            }
        }

        return matchingTrainers ;
    }

    /**
     * Returns a list containing all the members in the gym. Returns an empty list if none are found.
     * @return an ArrayList of type Member containing members of this gym
     */
    public static ArrayList<Member> listMembers()
    {
        if (members.size() == 0)
        {
            return null;
        }
        else
        {
            return members;
        }
    }

    /**
     * Returns a list containing all the trainers in the gym. Returns an empty list if none are found.
     * @return an ArrayList of type Trainer containing trainers of this gym
     */
    public static ArrayList<Trainer> listTrainers()
    {
        if (trainers.isEmpty()){
            return null;
        }
        else{
            return trainers;
        }
    }

    /**
     * Returns a list containing all the members details in the gym whose latest assessment weight
     * is an ideal weight (based on the devine formula). Returns an empty list if none are found.
     * @return ArrayList of type Member containing all the members who are ±2kg of their Ideal Weight
     */
    public ArrayList<Member> listMembersWithIdealWeight ()
    {
        ArrayList<Member> memberList = new ArrayList<>(getMembers());
        ArrayList<Member> idealWeightList = new ArrayList<>();


        if (memberList.isEmpty())
        {
            return memberList;
        }
        else
            {
            for (Member member : memberList)
            {
                if (member.getAssessments().isEmpty())
                {
                    return idealWeightList;
                }
                else
                {
                    if (GymUtility.isIdealBodyWeight(member, member.latestAssessment()))
                    {
                        idealWeightList.add(member);
                    }
                }
            }
            return idealWeightList;
        }
    }

    /**
     * Returns an Arraylist of type Member with all the members who are in the chosen category.
     * It makes a copy of the ArrayList of members, removes the members who are not in the BMI category,
     * and returns an ArrayList of the members remaining members.
     * @param category This chosen BMI category
     * @return ArrayList of members whose BMI category matches the search criterion
     */
    public ArrayList<Member> listMembersBySpecificBMICategory (String category) {
        ArrayList<Member> memberList = new ArrayList<>(getMembers());
        ArrayList<Member> categoryList = new ArrayList<>();


        String[] categories = {"No Verdict", "SEVERELY UNDERWEIGHT", "UNDERWEIGHT", "NORMAL", "OVERWEIGHT",
                "MODERATELY OBESE", "SEVERELY OBESE"};

        category = category.trim().toUpperCase();

        //determines if the category entered is a valid category according to categories array
        boolean validSearch = Arrays.asList(categories).contains(category);
        //https://stackoverflow.com/questions/1128723/how-do-i-determine-whether-an-array-contains-a-particular-value-in-java

        if (memberList.isEmpty())
        {
            return memberList;
        }
        else {
            if (validSearch)
            {
                for (Member member : memberList)
                {
                    if (member.getAssessments().isEmpty())
                    {
                        return categoryList;
                    }
                    else {
                        double bmi = GymUtility.calculateBMI(member, member.latestAssessment());
                        String bmiCat = GymUtility.determineBMICategory(bmi);

                        //Input is a single category
                        if (bmiCat.equals(category)) {
                            categoryList.add(member);
                        }
                    }
                }
                return categoryList;
            }
            else
                {
                for (Member member : memberList)
                {
                    if (member.getAssessments().isEmpty())
                    {
                        return categoryList;
                    } else {
                        double bmi = GymUtility.calculateBMI(member, member.latestAssessment());
                        String bmiCat = GymUtility.determineBMICategory(bmi);
                        String searchCategory = "";

                        boolean partial = bmiCat.contains(category);
                        if (partial) {
                            searchCategory = category.replace(category, bmiCat);
                        }

                        if (bmiCat.equals(searchCategory)) {
                            categoryList.add(member);
                        }
                    }
                }

                if (categoryList.size() > 0) {
                    return categoryList;
                } else {
                    memberList.clear();
                    return memberList;
                }
            }
        }


    }

    /**
     * Creates a String with a members height and weight displayed in metric and imperial values.
     * Displayed on one line,. The String is appended throughout
     * the iterations to include all of this gym's Members values.
     * If the ArrayList is empty, the string "No registered members" is returned.
     * @return all members height and weight in metric and imperial values, or if empty, "No registered members."
     * {@link 'http://www.geeksforgeeks.org/stringbuilder-append-method-in-java-with-examples/' }
     */
    public String listMemberDetailsImperialAndMetric()
    {
        ArrayList<Member> memberList = getMembers();
        String output1;
        String output2;
        String output;
        String name;
        int weightKg;
        int weightLb;

        float heightM;
        int heightInches;

        StringBuilder imperialAndMetric = new StringBuilder();
        int counter = 0;

        if (memberList.size() == 0)
        {
            return "No registered members";
        }
        else
        {
            for (Member member : memberList)
            {
                if(member.getAssessments().isEmpty())
                {
                    weightKg = Math.round(member.getStartWeight());
                }
                else
                {
                    weightKg = (int) Math.round((member.latestAssessment()).getWeight());
                }

                name = member.getName();

                weightLb = (int)Math.round(weightKg*2.20462);

                heightM = GymUtility.toOneDecimalPlace(member.getHeight());

                heightInches = (int)(Math.round(heightM * 39.3701));

                if (counter == 0)
                {
                    output1 = name + ": " + weightKg + " kg (" + weightLb + " lbs) " +
                            heightM + " metres (" + heightInches + " inches).\n";
                    //output = output1;
                    imperialAndMetric.append(output1);

                }
                if(counter == 1)
                {
                    output2 = name + ": " + weightKg + " kg (" + weightLb + " lbs) " +
                            heightM + " metres (" + heightInches + " inches).\n";
                    //output =output2;
                    imperialAndMetric.append(output2);
                    counter = 0;
                }
                counter++;
            }
        }
        return imperialAndMetric.toString();
    }


    protected void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        // ------------------ PREVENT SECURITY WARNINGS---------------------------
        Class<?>[] classes = new Class[] { Person.class, Member.class, Trainer.class, PremiumMember.class, StudentMember.class, Assessment.class };
        // The Person, Member, Trainer and Assessment class is what we are reading in
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        // -----------------------------------------------------------------------
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("persons.xml"));
        members = (ArrayList<Member>) is.readObject();
        trainers = (ArrayList<Trainer>) is.readObject();
        is.close();
    }

    protected void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        // ------------------ PREVENT SECURITY WARNINGS---------------------------
        Class<?>[] classes = new Class[] { Person.class, Member.class,Trainer.class, PremiumMember.class, StudentMember.class, Assessment.class };

        /* The Person, Member, Trainer and Assessment class is what we are reading in */
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        // -----------------------------------------------------------------------
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("persons.xml"));
        out.writeObject(members);
        out.writeObject(trainers);
        out.close();
    }

}
