import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class runs the application and handles the Gym with its Members and Trainers.
 *
 * @author Killian O'Neachtain ID: 20023634
 * @version V3.0
 *
 * Version V3.0 is aiming for the 'excellent' grade for this Programming Assignment 3.
 */

public class MenuController {
    protected final HashMap<String, String> packageMap = new HashMap<String, String>(16, (float) 0.25);
    private Scanner sc = new Scanner(System.in);
    private GymAPI gym = new GymAPI();
    private String dateEntered;

    public static void main(String[] args) throws ParseException {
        MenuController app = new MenuController();
        app.run();
    }

    /**
     * Displays a welcome message, and author / student ID.
     */
    private void welcomeMenu() {
        System.out.println(" ********************************************   ");
        System.out.println(" *                                          *   ");
        System.out.println(" *       WELCOME TO KILLIAN'S GYM           *   ");
        System.out.println(" *       ------------------------           *   ");
        System.out.println(" *                                          *   ");
        System.out.println(" *   Created by : Killian O'Neachtain       *   ");
        System.out.println(" *   Student ID : 20023634                  *   ");
        System.out.println(" *                                          *   ");
        System.out.println(" ********************************************   ");
    }

    /**
     * Displays a menu on start up for the user to either register or login
     *
     * @return this users menu choice
     */

    private char homeMenu() {
        System.out.println("  Gym Menu");
        System.out.println("------------------");
        System.out.println("   1) Register (r)");
        System.out.println("   2) Login (l)");
        System.out.println("------------------");
        System.out.println("   0) Exit");
        System.out.println("------------------");
        System.out.println("==>>");
        char option = sc.next().charAt(0);
        if ((option == 'r') || (option == 'R') || (option == '1')) {
            option = 'r';
            return option;

        } else if ((option == 'l') || (option == 'L') || (option == '2')) {
            option = 'l';
            return option;
        } else
            return option;
    }

    /**
     * Displays a home-menu for logged-in members to navigate
     *
     * @return this users menu choice
     */
    private int memberMenu() {
        System.out.println("  Gym Member Menu");
        System.out.println("------------------------------------");
        System.out.println("   1) View Profile");
        System.out.println("   2) Update you Profile");
        System.out.println("------------------------------------");
        System.out.println("   3) View your Current Information ");
        System.out.println("   4) View your Progress ");
        System.out.println("------------------------------------");
        System.out.println("   5) View your Assessments ");
        System.out.println("------------------------------------");
        System.out.println("   0) Logout");
        System.out.println("------------------------------------");
        System.out.println("==>>");
        int option = sc.nextInt();
        return option;
    }

    /**
     * Displays a progress-menu to a logged-in member user
     *
     * @return this users menu choice
     */
    private int progressMenu() {
        System.out.println("  Gym Member Progress Menu");
        System.out.println("----------------------------------------");
        System.out.println("   1) View Progress by Weight");
        System.out.println("   2) View Progress by Waist Measurement");
        System.out.println("   3) View Progress by BMI Measurement");
        System.out.println("----------------------------------------");
        System.out.println("   0) Return to Member Menu");
        System.out.println("----------------------------------------");
        System.out.println("==>>");
        int option = sc.nextInt();
        return option;
    }

    /**
     * Displays a home-menu to a logged-in trainer user
     *
     * @return this users menu choice
     */
    private int trainerMenu() {
        System.out.println("   Gym Trainer Menu");
        System.out.println("----------------------------------------------------");
        System.out.println("   1) Add a New Member");
        System.out.println("   2) List All Members");
        System.out.println("   3) List All Members Metric and Imperial Values");
        System.out.println("   4) List All Members With Ideal Weight");
        System.out.println("   5) List All Members By Specific BMI Category");
        System.out.println("----------------------------------------------------");
        System.out.println("   6) Search for Member by E-mail ");
        System.out.println("   7) Search for Member by Name ");
        System.out.println("   8) Search for Trainer by E-mail ");
        System.out.println("----------------------------------");
        System.out.println("   9) Assessment Modifications ");
        System.out.println("----------------------------------");
        System.out.println("   0) Logout");
        System.out.println("----------------------------------");
        System.out.println("==>>");
        int option = sc.nextInt();
        return option;
    }

    /**
     * Displays an assessment-menu to a logged-in trainer
     *
     * @return this users menu choice
     */
    private int assessmentMenu() {
        System.out.println("   Gym Trainer Assessment Menu");
        System.out.println("--------------------------------------");
        System.out.println("   1) Add an Assessment for a Member");
        System.out.println("   2) Add / Update Comment to an Assessment");
        System.out.println("--------------------------------------");
        System.out.println("   0) Return to Trainer Menu");
        System.out.println("----------------------------------");
        System.out.println("==>>");
        int option = sc.nextInt();
        return option;
    }

    /**
     * This is the method that controls the loop.
     */

    private void run() throws ParseException {
        fillPackageMap(packageMap);

        char option = 0;


        /**
         * Used as a switch to navigate in and out of do-while loops
         */
        boolean goodInput = false;

        welcomeMenu(); // displays the welcome method

        /**
         * Loads a database(.xml) file for the program to work with
         * @exception e.printStackTrace()
         */
        try {
            gym.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * Loop to display the homeMenu until correct input from the user.
         * @exception InputMismatchException
         */
        do {
            try {
                option = homeMenu();
                goodInput = true;

            } catch (Exception InputMismatchException) {
                System.err.println("Error : A character was entered!");
                sc.nextLine();
            }
        } while (!goodInput);

        /**
         * While loop to display the menu until '0' is entered, on which it will exit
         */
        while (option != '0') {
            switch (option) {

                case 'r':
                    registerPerson(isMemberOrTrainer());
                    break;

                case 'l':
                    login();
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;

            }
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress Enter / Return to continue...");
            sc.nextLine();
            sc.nextLine();  //this second read is required - bug in Scanner class; a String read is
            // ignored straight after reading an int.

            //display the main menu again
            goodInput = false;

            do {
                try {
                    option = homeMenu();
                    goodInput = true;
                } catch (Exception InputMismatchException) {
                    System.err.println("Error : A character was entered!");
                    sc.nextLine();
                }
            } while (!goodInput);
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye because option was : " + option);
        saveOnExit();
        System.exit(0);
    }


    /**
     * This log's in a member into the program. It reads in an email, as a String, and checks this email
     * against the Arraylist of Members or Trainers in the .xml database
     */
    private void login() throws ParseException {
        Member member;
        Trainer trainer;
        boolean goodInput = false;
        int option = 0;
        char user;
        String email = "";

        sc.nextLine();

        /**
         * determines if the user is member(m) or trainer(t)
         */
        user = isMemberOrTrainer();

        do {
            try {
                System.out.println("Please Enter Your E-mail : ");
                email = sc.nextLine();


                // If the user is a member, the program searches the members for a matching e-mail.
                // If there is a match then the program displays a Member menu for navigation.
                if ((user == 'm') && (gym.searchMembersByEmail(email) != null)) {
                    //member = gym.searchMembersByEmail(email);
                    goodInput = true;
                    option = memberMenu();
                }
                // If the user is a trainer, the program searches the trainers for a matching e-mail.
                // If there is a match then the program displays a Trainer menu for navigation.
                else if ((user == 't') && (gym.searchTrainersByEmail(email) != null)) {
                    goodInput = true;
                    option = trainerMenu();
                } else {
                    //If no match is found, display message and exit program.
                    System.err.println("Access is Denied!! E-mail was not found");
                    goodInput = false;

                    System.exit(0);
                }
            } catch (Exception InputMismatchException) {
                sc.nextLine();
                System.err.println("Invalid String entered at Email! Email was :" + email);
            }
        } while (!goodInput);

        // Assigns the variable instance of Member to the details found by the users email input.
        member = gym.searchMembersByEmail(email);


        if (user == 'm') {
            while (option != 0) {

                // the methods for the member menu are called by the user menu choice
                // '0' will log out the member and return to the Home menu.
                switch (option) {
                    case 1:
                        System.out.println(viewMemberProfile(member));
                        break;

                    case 2:
                        updateMemberProfile(member);
                        break;

                    case 3:
                        currentBodyDetails(member);
                        break;

                    case 4:
                        progressMenu(member);
                        break;

                    case 5:
                        listAssessments(member);
                        break;

                    default:
                        System.out.println("Invalid option entered: " + option);
                        break;
                }

                //pause the program so that the user can read what we just printed to the terminal window
                System.out.println("\nPress Enter / Return to continue...");
                sc.nextLine();
                sc.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

                //display the Member Menu  menu again
                do {
                    try {
                        option = memberMenu();
                        goodInput = true;
                    } catch (Exception InputMismatchException) {
                        System.err.println("Error : A character was entered!");
                        sc.nextLine();
                    }
                } while (!goodInput);

            }
        }


        if (user == 't') {
            trainer = gym.searchTrainersByEmail(email);
            while (option != 0) {

                // the methods for the trainer menu are called by the user menu choice
                // '0' will log out the trainer and return to the Home menu
                switch (option) {
                    case 1:
                        registerPerson('m');
                        break;

                    case 2:
                        listAllMembers();
                        break;

                    case 3:
                        listMemberDetailsImperialAndMetric();
                        break;

                    case 4:
                        idealWeight();
                        break;

                    case 5:
                        bmiCategory();
                        break;

                    case 6:
                        searchMemberByEmail();
                        break;

                    case 7:
                        searchMemberByName();
                        break;

                    case 8:
                        searchTrainerByEmail();
                        break;

                    case 9:
                        assessmentModifications(trainer);
                        break;

                    default:
                        System.out.println("Invalid option entered: " + option);
                        break;
                }

                //pause the program so that the user can read what we just printed to the terminal window
                System.out.println("\nPress Enter / Return to continue...");
                sc.nextLine();
                sc.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

                //display the Member Menu  menu again
                do {
                    try {
                        option = trainerMenu();
                        goodInput = true;
                    } catch (Exception InputMismatchException) {
                        System.err.println("Error : A character was entered!");
                        sc.nextLine();
                    }
                } while (!goodInput);

            }
        }
    }

    /**
     * Displays the members who fall into the Ideal Weight category, ±0.3Kg.
     *
     * @see GymAPI#listMembersWithIdealWeight()
     */
    private void idealWeight() {
        ArrayList<Member> idealWeightMembers = gym.listMembersWithIdealWeight();
        System.out.println("The Members with Ideal Weight are : \n" + idealWeightMembers.toString());

    }


    /**
     * List member details in either Metric or Imperial values
     *
     * @see GymAPI#listMemberDetailsImperialAndMetric()
     */
    private void listMemberDetailsImperialAndMetric() {
        String out = gym.listMemberDetailsImperialAndMetric();
        System.out.println(out);
    }

    /**
     * Allows the user to search for members by a BMI category. Partial, not case sensitive or invalid searches
     * are dealt with in the GymAPI class method.
     *
     * @see GymAPI#listMembersBySpecificBMICategory(String)
     */
    private void bmiCategory() {
        sc.nextLine();
        System.out.println("Please Enter the BMI Category of the Members to search : ");
        String category = sc.nextLine().trim();
        sc.nextLine();
        ArrayList<Member> bmiCategory = gym.listMembersBySpecificBMICategory(category);
        System.out.println("The Members is the category, " + category + ", are : \n" + bmiCategory.toString());
    }


    /**
     * Allows this user to add assessments, update / add comments for members
     *
     * @param trainer This Trainer who is making changes or adding assessments for Members.
     * @throws ParseException
     */
    private void assessmentModifications(Trainer trainer) throws ParseException {
        int option = 0;
        boolean goodInput = false;
        // do-while loop for catching a character entered by the user and send an error message
        // to the console if there is an error encountered.
        do {
            try {
                option = assessmentMenu();
                goodInput = true;
            } catch (Exception InputMismatchException) {
                System.err.println("Error : A character was entered!");
                sc.nextLine();
            }
        } while (!goodInput);

        while (option != 0) {

            switch (option) {
                case 1:
                    addAssessment(trainer);
                    break;

                case 2:
                    addComment(trainer);
                    break;

                default:
                    System.err.println("Invalid option entered: " + option);
                    break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress Enter / Return to continue...");
            sc.nextLine();
            sc.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            //display the main menu again
            do {
                try {
                    option = assessmentMenu();
                    goodInput = true;
                } catch (Exception InputMismatchException) {
                    System.err.println("Error : A character was entered!");
                    sc.nextLine();
                }
            } while (!goodInput);
        }
    }

    /**
     * Allows this Trainer to add an assessment for a particular Member, which is verified by email.
     *
     * @param trainer This Trainer who is adding an assessment
     * @throws ParseException
     */
    private void addAssessment(Trainer trainer) throws ParseException {
        double weight = 0.0;
        double thigh = 0.0;
        double waist = 0.0;
        String comment = "";
        String date = "";
        boolean isStudent = false;

        ArrayList<Trainer> trainerArrayList = gym.getTrainers(); //Gets all registered Trainers
        int trainerIndex = trainerArrayList.indexOf(trainer);
        boolean validTrainer = gym.isValidTrainerIndex(trainerIndex); //Checks this trainer has a valid index in the arraylist.

        PremiumMember premiumMember = new PremiumMember();
        StudentMember studentMember = new StudentMember();

        String email;

        boolean goodInput = false;

        sc.nextLine();

        do {
            try {
                sc.nextLine();
                System.out.println("Please enter the Email of the Member, whose Assessment you would like to add  : ");
                email = sc.nextLine().trim();
                goodInput = isValid(email);

                if (gym.searchMembersByEmail(email) == null) {
                    System.err.println("The email : " + email + " could not be found.");
                    goodInput = false;
                }

                if ((gym.searchMembersByEmail(email) != null)) {
                    sc.nextLine();
                    if (memberIsStudent(email)) {
                        isStudent = true;
                        studentMember = (StudentMember) gym.searchMembersByEmail(email);
                        System.out.println(studentMember.getName());
                        System.out.println("Is this member correct? ( y / n ) :");
                        String answer = sc.nextLine().trim().toLowerCase();
                        answer = getFirstChar(answer);
                        if (answer.equals("y")) {
                            goodInput = true;
                        }

                    } else {
                        premiumMember = (PremiumMember) gym.searchMembersByEmail(email);
                        System.out.println(premiumMember.getName());
                        System.out.println("Is this member correct? ( y / n ) :");
                        String answer = sc.nextLine().trim().toLowerCase();
                        answer = getFirstChar(answer);
                        if (answer.equals("y")) {
                            goodInput = true;
                        } else {
                            System.out.println("Not what you were looking for!");
                            goodInput = false;
                        }
                    }
                }

            } catch (Exception InputMismatchException) {
                sc.nextLine();
                System.err.println("Invalid character entered ");
            }

        } while (!goodInput);

        goodInput = false;

        do {
            try {
                System.out.println("Please enter the Assessment Weight : ");
                weight = GymUtility.toTwoDecimalPlaces(sc.nextDouble());
                if ((weight >= 35.00) && (weight <= 250.00)) {
                    weight = GymUtility.toTwoDecimalPlaces(weight);
                    goodInput = true;
                } else {
                    System.err.println("Your Weight needs to be between 35.00Kg and 250.00Kg. " +
                            "\nPlease Try Again. ");
                    goodInput = false;
                }

            } catch (Exception InputMismatchException) {
                sc.nextLine();
                System.out.println("Invalid character entered ");
            }
        } while (!goodInput);

        goodInput = false;

        do {
            try {
                System.out.println("Please enter the Thigh measurement ( 0.10 - 1.00 metres) : ");

                thigh = GymUtility.toTwoDecimalPlaces(sc.nextDouble());
                if ((thigh >= 0.10) && (thigh <= 1.00)) {
                    thigh = GymUtility.toTwoDecimalPlaces(thigh);
                    goodInput = true;
                } else {
                    System.err.println("Your Thigh circumference needs to be between 0.10 m and 1.00 m. " +
                            "\nPlease enter measurement Again : ");
                    goodInput = false;
                }

            } catch (Exception InputMismatchException) {
                sc.nextLine();
                System.err.println("Invalid character entered ");
            }
        } while (!goodInput);

        goodInput = false;

        do {
            try {
                sc.nextLine();
                System.out.println("Please enter the Waist measurement ( 0.20 - 1.50 metres) : ");

                waist = GymUtility.toTwoDecimalPlaces(sc.nextDouble());
                if ((waist >= 0.20) && (waist <= 1.50)) {
                    waist = GymUtility.toTwoDecimalPlaces(waist);
                    goodInput = true;
                } else {
                    System.err.println("Your Thigh circumference needs to be between 0.20 m and 1.50 m. ");
                    goodInput = false;
                }

            } catch (Exception InputMismatchException) {
                sc.nextLine();
                System.err.println("Invalid character entered ");
            }
        } while (!goodInput);

        goodInput = false;

        do {
            try {
                sc.nextLine();
                System.out.println("Please enter Your Comment (Max 60 characters): ");
                comment = sc.nextLine().trim();
                if (comment.length() > 60) {
                    comment = comment.substring(0, 59);
                    System.err.println("The comment was too long and truncated to : " + comment);
                }

                goodInput = true;

            } catch (Exception InputMismatchException) {
                sc.nextLine();
                System.out.println("Invalid character entered ");
            }
        } while (!goodInput);

        do {
            try {
                System.out.println("Please enter Date of the Assessment (dd/MM/yy) : ");
                date = sc.nextLine().trim();
                goodInput = DateValidation(date);
            } catch (Exception InputMismatchException) {
                sc.nextLine();
                System.out.println("Invalid character entered ");
            }
        } while (!goodInput);

        Assessment assessment = new Assessment(weight, thigh, waist, comment);


        if (validTrainer) {
            assessment.trainer = trainer;
        }

        if (!isStudent) {
            System.out.println("The Premium Member is : " + premiumMember.getName());

            premiumMember.getAssessments().put(date, assessment);


            System.out.println("The Trainer that added the assessment is :" + assessment.getTrainer().getName());

        } else {
            System.out.println("The Student Member is : " + studentMember.getName());

            studentMember.getAssessments().put(date, assessment);

            System.out.println("The Trainer that added the assessment is :" + assessment.getTrainer().getName());

        }
    }

    /**
     * Allows this Trainer to add a comment to a particular assessment.
     * @param trainer This Trainer who is adding the comment
     */
    private void addComment(Trainer trainer) {
        String comment = "";
        String date = "";
        String email;
        boolean isStudent = false;

        PremiumMember premiumMember = new PremiumMember();
        StudentMember studentMember = new StudentMember();

        ArrayList<Trainer> trainerArrayList = gym.getTrainers(); // Arraylist of trainers currently at the Gym
        int trainerIndex = trainerArrayList.indexOf(trainer);
        boolean validTrainer = gym.isValidTrainerIndex(trainerIndex);

        boolean goodInput = false;

        sc.nextLine();

        do {
            try {
                sc.nextLine();
                System.out.println("Please enter the Email of the Member, whose Assessment you would like to comment on  : ");
                email = sc.nextLine().trim();
                goodInput = isValid(email);

                if (gym.searchMembersByEmail(email) == null) {
                    System.err.println("The email : " + email + " could not be found.");
                    goodInput = false;
                }

                if ((gym.searchMembersByEmail(email) != null)) {
                    sc.nextLine();
                    if (memberIsStudent(email))
                    {
                        isStudent = true;
                        studentMember = (StudentMember) gym.searchMembersByEmail(email); // cast member to student member
                        System.out.println(studentMember.getName());
                        System.out.println("Is this member correct? ( y / n ) :");
                        String answer = sc.nextLine().trim().toLowerCase();
                        answer = getFirstChar(answer);

                        // Outputs the assessment dates of the member to the trainer
                        if (answer.equals("y")) {
                            System.out.println("Assessment Dates are: " + studentMember.getAssessments().keySet());
                            goodInput = true;
                        } else {
                            goodInput = false;
                        }

                    } else {
                        premiumMember = (PremiumMember) gym.searchMembersByEmail(email);
                        System.out.println(premiumMember.getName());
                        System.out.println("Is this member correct? ( y / n ) :");
                        String answer = sc.nextLine().trim().toLowerCase();
                        answer = getFirstChar(answer);
                        if (answer.equals("y")) {
                            System.out.println("Assessment Dates are: " + premiumMember.getAssessments().keySet());
                            goodInput = true;
                        } else {
                            goodInput = false;
                        }
                    }
                }

            } catch (Exception InputMismatchException) {
                sc.nextLine();
                System.err.println("Invalid character entered ");
            }

        } while (!goodInput);

        goodInput = false;

        do {
            try {
                sc.nextLine();
                System.out.println("Enter the DATE of the assessment you would like to comment on (dd/MM/yy) : ");
                date = sc.nextLine();

                if ((DateValidation(date)) && (!isStudent)) {
                    System.out.println("The Assessment chosen is : \n");
                    System.out.println(premiumMember.getAssessments().get(date).toString());
                    System.out.println("Please Enter Your Comment :\n");
                    comment = sc.nextLine();
                    premiumMember.getAssessments().get(date).setComment(comment);
                    System.out.println("Assessment : " + premiumMember.getAssessments().get(date).toString());
                    goodInput = true;

                } else {
                    System.out.println("The Assessment chosen is : \n");
                    System.out.println(studentMember.getAssessments().get(date).toString());
                    System.out.println("Please Enter Your Comment :\n");
                    comment = sc.nextLine();
                    studentMember.getAssessments().get(date).setComment(comment);
                    System.out.println("Updated Assessment : " + studentMember.getAssessments().get(date).toString());
                    goodInput = true;
                }
                goodInput = DateValidation(date);
            } catch (Exception InputMismatchException) {
                sc.nextLine();
                System.out.println("Invalid character entered ");
            }
        } while (!goodInput);
    }

    /**
     * validate an entered date as a dd/MM/yy format
     * @param dateEntered A date, as a String, 
     * @return valid date format
     * @link https://kodejava.org/how-do-i-check-if-a-string-is-a-valid-date/
     */
    private boolean DateValidation(String dateEntered)
    
    {
        this.dateEntered = dateEntered;
        DateFormat format = new SimpleDateFormat("dd/MM/yy");

        // Input to be parsed should strictly follow the defined date format
        // above.
        format.setLenient(false);

        //String date = "29/18/17";
        try {
            format.parse(dateEntered);
            return true;
        } catch (ParseException e) {
            System.err.println("Date " + dateEntered + " is not valid according to " +
                    ((SimpleDateFormat) format).toPattern() + " pattern.");
            return false;
        }
    }

    /**
     * to display details for this Member. If there are no assessments, then this displays calculations
     * based on this Member's startingWeight value.
     * @param member This Member that is logged in 
     */
    private void currentBodyDetails(Member member)
    {
        if (member.getAssessments().isEmpty())
        {
            System.out.println("\tYou need to add an Assessment to achieve a BMI score.\n");
            System.out.println("Hello, " + member.getName() + " , your current weight is : " +
                    (member.getStartWeight()) + "Kg's. \nYour current height is : " + member.getHeight() + "m.");

        }
        else
        {
            System.out.println("Hello, " + member.getName() + " , your current weight is : " +
                    (member.latestAssessment().getWeight()) + "Kg's. \nYour current height is : " + member.getHeight() + "m.");

            double BMI = GymUtility.calculateBMI(member, member.latestAssessment());

            System.out.println(member.getName() + "'s current Weight Category :  \n" +
                    "BMI is " + GymUtility.toTwoDecimalPlaces(BMI) +
                    "\nThis is Categorized as : " + GymUtility.determineBMICategory(BMI));
        }

        double idealBodyWeight = 0.0;

        if (member.getGender().equals("M")) {
            idealBodyWeight = 50.00 + ((((member.getHeight() * 100) - 152.4) / 2.54) * 2.3);
            System.out.println("Your Ideal Body Weight, " + member.getName() +
                    ", is : " + GymUtility.toTwoDecimalPlaces(idealBodyWeight) + "Kg.");
        } else if ((member.getGender().equals("F")) || (member.getGender().equals("Unspecified"))) {
            idealBodyWeight = 45.5 + ((((member.getHeight() * 100) - 152.4) / 2.54) * 2.3);
            System.out.println("Your Ideal Body Weight, " + member.getName() +
                    ", is : " + GymUtility.toTwoDecimalPlaces(idealBodyWeight) + "Kg.");
        }

        double differential = Math.abs(idealBodyWeight - member.getStartWeight());
        System.out.println("You are " + GymUtility.toTwoDecimalPlaces(differential) + "Kg's from your Ideal Weight.");
    }


    private void progressMenu(Member member)
    {
        int option=0;
        boolean goodInput = false;
        // do-while loop for catching a character entered by the user and send an error message
        // to the console if there is an error encountered.
        do{
            try{
                option = progressMenu();
                goodInput =true;
            }
            catch (Exception InputMismatchException)
            {
                System.err.println("Error : A character was entered!");
                sc.nextLine();
            }
        } while (!goodInput);

        while (option != 0)
        {

            switch (option)
            {
                case 1:   progressByWeight(member);
                    break;

                case 2:   progressByWaist(member);
                    break;

                case 3:   progressByBMI(member);
                    break;

                default:    System.err.println("Invalid option entered: " + option);
                    break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress Enter / Return to continue...");
            sc.nextLine();
            sc.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            //display the main menu again
            do{
                try{
                    option = progressMenu();
                    goodInput =true;
                }
                catch (Exception InputMismatchException)
                {
                    System.err.println("Error : A character was entered!");
                    sc.nextLine();
                }
            } while (!goodInput);
        }
    }

    /**
     * This iterates through this HashMap of assessments, using a SortedSet of dates(String) as key.
     * It displays the dates(ascending) and assessment weight.
     * @param member This Member used to access the assessment HashMap
     * @see #progressIndicator(double, double)
     * @link http://www.java2s.com/Code/JavaAPI/java.util/SortedSetiterator.html
     * @link https://stackoverflow.com/questions/5920135/printing-hashmap-in-java
     */
    private void progressByWeight(Member member)
    {
        HashMap<String, Assessment> assessmentHashMap = member.getAssessments();

        double previous = 0.0;

        if(assessmentHashMap.isEmpty())
        {
            System.out.println("There are no assessments.");
        }
        else
        {
            System.out.println("Your progress by Weight is :\n");
            SortedSet<String> assessmentOrder = member.sortedAssessmentDates();

            Iterator it = assessmentOrder.iterator();
            while (it.hasNext())
            {
                Object element = it.next(); // date
                double weight =  assessmentHashMap.get(element).getWeight(); // weight

                String weightString = Double.toString(weight);
                System.out.println(element + " : " + weightString );

                previous = progressIndicator(weight,previous);
            }
        }
    }

    private void progressByWaist(Member member)
    {
        HashMap<String, Assessment> assessmentHashMap = member.getAssessments();
        double previous = 0.0;

        if(assessmentHashMap.isEmpty())
        {
            System.out.println("There are no assessments.");
        }
        else
        {
            System.out.println("Your progress by Waist Measurement is :\n");
            SortedSet<String> assessmentOrder = member.sortedAssessmentDates();

            Iterator it = assessmentOrder.iterator();
            while (it.hasNext())
            {
                Object element = it.next(); // date
                double waist =  assessmentHashMap.get(element).getWaist(); // waist

                String waistString = Double.toString(waist);
                System.out.println(element + " : " + waistString );

                previous = progressIndicator(waist,previous);
            }
        }
    }

    private void progressByBMI(Member member)
    {
        //https://stackoverflow.com/questions/5920135/printing-hashmap-in-java
        //http://www.java2s.com/Code/JavaAPI/java.util/SortedSetiterator.htm

        HashMap<String, Assessment> assessmentHashMap = member.getAssessments();

        double previous = 0.0;

        if(assessmentHashMap.isEmpty())
        {
            System.out.println("There are no assessments.");
        }
        else
        {
            System.out.println("Your progress by BMI measurement is :\n");
            SortedSet<String> assessmentOrder = member.sortedAssessmentDates();

            Iterator it = assessmentOrder.iterator();
            while (it.hasNext())
            {
                Object element = it.next(); // date

                Assessment assessment =  assessmentHashMap.get(element); // Assessment instance

                double bmi = GymUtility.toTwoDecimalPlaces(GymUtility.calculateBMI(member,assessment));

                String category = GymUtility.determineBMICategory(bmi);

                String bmiString = Double.toString(bmi);

                String bmiCut = bmiString.substring(0,5);

                System.out.println(element + " : " + bmiCut + " Category : " + category );

                previous = progressIndicator(bmi,previous);
            }
        }
    }

    /**
     * Checks if a value is greater than, equal to, or less than another value.
     * @param measurement double type value for this method
     * @param previous double type value for thes method
     * @return the measurement value read in is returned unchanged after arithmetic is performed
     */
    private double progressIndicator(double measurement, double previous)
    {

        if(previous == 0.0)
        {
            System.out.println("This is your first Assessment.");
        } else
        if (measurement > previous)
        {
            System.out.println("Higher than previous Assessment.");
        }
        else if (measurement < previous)
        {
            System.out.println("Lower than previous Assessment.");
        }
        else if (measurement == previous)
        {
            System.out.println("No Change since previous Assessment.");
        }
        return measurement;
    }




    /**
     * listAllMembers() - This method iterates through all the members in the Arraylist
     * of Members, and displays each member's profile.     *
     *
     */

    private void listAllMembers()
    {
        ArrayList<Member> membersList = gym.listMembers();
        for(Member member:membersList)
        {
            System.out.println(member.toString());
        }

    }

    private void searchMemberByEmail()
    {
        String email;
        sc.nextLine();
        System.out.println("Please enter the Email you would like to find : ");

        email = sc.nextLine().trim();

        Member member = gym.searchMembersByEmail(email);

        if(member != null)
        {
            System.out.println(member.toString());
        }
        else
        {
            System.out.println("Sorry, the Email " + email + " wasn't found!");
        }

    }

    private void searchMemberByName()
    {

        sc.nextLine();

        System.out.println("Please enter the Name you would like to find  : \n(Note : 1.CaSe SenSitIve 2.Partial and Full Matches Returned)");

        String name = sc.nextLine().trim();

        ArrayList<String> nameList = gym.searchMembersByName(name);

        if(nameList != null)
        {
            for (String String:nameList)
            {
                System.out.println(String);
            }
        }
        else
        {
            System.out.println("Sorry, the name " + name + " wasn't found!");
        }
    }

    private void searchTrainerByEmail()
    {
        String email;
        sc.nextLine();
        System.out.println("Please enter the Email you would like to find : ");

        email = sc.nextLine().trim();

        Trainer trainer = gym.searchTrainersByEmail(email);

        if(trainer != null)
        {
            System.out.println(trainer.toString());
        }
        else
        {
            System.out.println("Sorry, the Email " + email + " wasn't found!");
        }

    }

    private String viewMemberProfile(Member member)
    {
        if(member.getAssessments().isEmpty())
        {
            System.out.println("Your Profile details " + member.getName() + " are : \n");
            return member.toString() + "\n";
        }
        else
            {
        String assessment = (member.latestAssessment()).toString();
        System.out.println("Your Profile details " + member.getName() + " are : \n");
                return member.toString() + "\n" + assessment;
        }
    }

    private void updateMemberProfile(Member member)
    {
        sc.nextLine();
        int option = 0;
        boolean goodInput = false;

        do{
            try
            {
                System.out.println("Your current Profile is : " + member.toString() + "\n");
                option = editProfileMenu();
                goodInput = true;
            }
            catch (Exception InputMismatchException)
            {
                System.err.println("Error : A character was entered!");
                sc.nextLine();
            }
        } while (!goodInput);

        goodInput = false;

        while (option!=0)
        {
            switch(option)
            {
                case 1:     editName(member);
                                break;

                case 2:     editAddress(member);
                                break;

                case 3:     editGender(member);
                                break;

                case 4:     editHeight(member);
                                break;

                case 5:     editStartWeight(member);
                                break;

                case 6:     editChosenPackage(member);
                                break;

                case 10:    System.out.println(member.toString());
                                break;

                default:    System.err.println("Invalid option entered: " + option);
                    break;
            }
            System.out.println("\nPress Enter / Return to continue...");
            sc.nextLine();
            sc.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            //display the main menu again
            do{
                try{
                    option = editProfileMenu();
                    goodInput =true;
                }
                catch (Exception InputMismatchException)
                {
                    System.err.println("Error : A character was entered!");
                    sc.nextLine();
                }
            } while (!goodInput);
        }
    }

    private int editProfileMenu()
    {
        System.out.println("   Which Profile field would you like to Edit");
        System.out.println("--------------------------------------");
        System.out.println("   1) Edit Name");
        System.out.println("   2) Edit Address");
        System.out.println("   3) Edit Gender");
        System.out.println("--------------------------------------");
        System.out.println("   4) Edit Height");
        System.out.println("   5) Edit Starting Weight");
        System.out.println("   6) Edit Chosen Package");
        System.out.println("--------------------------------------");
        System.out.println("   10) View Profile");
        System.out.println("--------------------------------------");
        System.out.println("   0) Return to Member Menu");
        System.out.println("----------------------------------");
        System.out.println("==>>");
        int option = sc.nextInt();
        return option;
    }

    private void editName(Member member)
    {

    String name;
    boolean goodInput = false;

    //dummy read of String to clear the buffer - bug in Scanner class.
    sc.nextLine();
    do
    {
        try
        {
            System.out.println("Your current name is : " +  member.getName());
            System.out.print("Enter the new Name:  ");
            name = sc.nextLine();
            if(name.length() > 30)
            {
                name = name.substring(0, 29);
                System.err.println("The name was too long and truncated to : " + name);
                member.setName(name);
                System.out.println("Your name is : " + member.getName());
                goodInput = true;
            }
            else {
                member.setName(name);
                System.out.println("Your name is : " + member.getName());
                goodInput = true;
            }

        }
        catch(Exception InputMismatchException)
        {
            sc.nextLine();
            System.out.println("Invalid character entered ");
        }
    } while (!goodInput);

    }

    private void editAddress(Member member)
    {

        String address;
        boolean goodInput = false;

        //dummy read of String to clear the buffer - bug in Scanner class.
        sc.nextLine();
        do
        {
            try
            {
                System.out.println("Your current address is  : " + member.getAddress());
                System.out.print("Enter the new Address:  ");
                address = sc.nextLine();
                member.setAddress(address);
                System.out.println("Your NEW address is  : " + member.getAddress());
                goodInput = true;

            }
            catch(Exception InputMismatchException)
            {
                sc.nextLine();
                System.out.println("Invalid character entered ");
            }
        } while (!goodInput);

    }

    private void editGender(Member member)
    {

        String gender;
        boolean goodInput = false;

        //dummy read of String to clear the buffer - bug in Scanner class.
        sc.nextLine();
        do
        {
            try
            {
                System.out.println("Your current Gender is : " + member.getGender());
                System.out.print("Enter the new Gender (F / M) :  ");
                gender = sc.nextLine().trim().toUpperCase();

                if(gender.equals("F"))
                {
                    member.setGender(gender);
                    goodInput = true;
                } else
                if(gender.equals("M"))
                {
                    member.setGender(gender);
                    goodInput = true;
                }
                else {
                    gender = gender.replaceAll("(.*.)", "Unspecified");
                    member.setGender(gender);
                    goodInput = true;
                }
            }
            catch(Exception InputMismatchException)
            {
                sc.nextLine();
                System.err.println("Invalid character entered. \n Please enter Female or Male ( F or M ).  ");
            }
        } while (!goodInput);
    }

    private void editHeight(Member member)
    {
        float height;
        boolean goodInput = false;

        do{
            try
            {
                System.out.println("Your current Height is : " + member.getHeight());
                System.out.println("Please Enter Your New Height in Metres (1.00 - 3.00) : ");
                height = sc.nextFloat();
                if ((height >= 1.00) && (height <= 3.00))
                {
                    member.setHeight(height);
                    goodInput = true;
                }
                else
                {
                    System.err.println("Your height needs to be a METRIC value between 1.00m and 3.00m. " +
                            "\nPlease Try Again. ");
                    goodInput = false;
                }
            } catch(Exception InputMismatchException)
            {
                sc.nextLine();
                System.err.println("Invalid character entered. Please try again. ");
            }
        } while (!goodInput);
    }

    private void editStartWeight(Member member)
    {
        float startWeight;
        boolean goodInput = false;

        do{
            try
            {
                System.out.println("Your current Starting Weight is : " + member.getStartWeight() + "Kgs.");
                System.out.println("Please Enter Your Starting Weight in Kilogrammes ( Kg : 35.00 - 250.00 ) : ");
                startWeight = sc.nextFloat();
                if ((startWeight >= 35.00) && (startWeight <= 250.00))
                {
                    startWeight = GymUtility.toTwoDecimalPlaces(startWeight);
                    member.setStartWeight(startWeight);
                    goodInput = true;
                }
                else
                {
                    System.err.println("Your Starting Weight needs to be between 35.00Kg and 250.00Kg. " +
                            "\nPlease Try Again. ");
                    goodInput = false;
                }
            } catch(Exception InputMismatchException)
            {
                sc.nextLine();
                System.err.println("Invalid character type entered. Please try again. ");
            }
        } while (!goodInput);

    }

    private void editChosenPackage(Member member)
    {
        String chosenPackage;
        boolean goodInput = false;

        do{
            try
            {
                System.out.println("Your current package is : " + member.getChosenPackage());
                sc.nextLine();
                System.out.println("Package 1 : " + packageMap.get("Package 1") + "\n");
                System.out.println("Package 2 : " + packageMap.get("Package 2")+ "\n");
                System.out.println("Package 3 : " + packageMap.get("Package 3")+ "\n");
                System.out.println("Package WIT : " + packageMap.get("WIT")+ "\n");
                System.out.println("Please Enter Your New Chosen Package ( 1 / 2 / 3 / WIT ) : \n");

                chosenPackage = sc.nextLine().trim();

                System.out.println("You are user type: " + member.getUser());


                switch (chosenPackage) {
                    case "1":
                        chosenPackage = "Package 1";
                        member.setChosenPackage(chosenPackage);
                        System.out.println("Your NEW package is : " + member.getChosenPackage());
                        goodInput = true;
                        break;
                    case "2":
                        chosenPackage = "Package 2";
                        member.setChosenPackage(chosenPackage);
                        System.out.println("Your NEW package is : " + member.getChosenPackage());
                        goodInput = true;
                        break;
                    case "3":
                        chosenPackage = "Package 3";
                        member.setChosenPackage(chosenPackage);
                        System.out.println("Your NEW package is : " + member.getChosenPackage());
                        goodInput = true;
                        break;
                    case "WIT":
                        chosenPackage = "WIT";
                        member.setChosenPackage(chosenPackage);
                        System.out.println("Your NEW package is : " + member.getChosenPackage());
                        goodInput = true;
                        break;
                    default:
                        chosenPackage = "No Package";
                        member.setChosenPackage(chosenPackage);
                        System.out.println("Your NEW package is : " + member.getChosenPackage());
                        goodInput = true;
                        break;
                }

            } catch(Exception InputMismatchException)
            {
                sc.nextLine();
                System.err.println("Invalid character type entered. Please try again. ");
            }
        } while (!goodInput);
    }



    private void registerPerson(char choice)
    {
        String name = "";
        String email = "";
        String address = "";
        String gender = "";

        boolean goodInput = false;

        //dummy read of String to clear buffer
        sc.nextLine();

        do
        {
            try
            {
                System.out.println("Please Enter Your Name: ");
                name = sc.nextLine().trim();
                if(name.length() > 30)
                {
                    name = name.substring(0, 29);
                    System.err.println("The name was too long and truncated to : " + name);
                }

                goodInput = true;
            }
            catch(Exception InputMismatchException
            ){
                sc.nextLine();
                System.err.println("Invalid character entered. ");
            }
        } while (!goodInput);

        goodInput = false;

        do
        {
            try
            {
                System.out.println("Please Enter Your Email address (me@you.xx) : ");
                email = sc.nextLine().trim();

                if ((isValid(email)) && ((gym.searchMembersByEmail(email) != null) || (gym.searchTrainersByEmail(email) != null)))
                {
                    sc.nextLine();
                    System.err.println("Sorry! That E-mail is already registered.\n");
                    goodInput = false;
                }
                else
                {
                    goodInput = isValid(email);
                }
            }
            catch(Exception InputMismatchException)
            {
                sc.nextLine();
                System.err.println("Invalid character entered ");
            }
        } while (!goodInput);

        goodInput = false;

        do
        {
            try
            {
                System.out.println("Please Enter Your Address : ");
                address = sc.nextLine().trim();
                goodInput = true;
            }
            catch(Exception InputMismatchException
            ){
                sc.nextLine();
                System.err.println("Invalid character entered ");
            }
        } while (!goodInput);

        goodInput = false;

        do
        {
            try
            {
                System.out.println("Please Enter Your Gender ( Female ( F ) / Male ( M ) ) : ");

                gender = sc.nextLine().trim().toUpperCase();
                gender = getFirstChar(gender);

                if(gender.equals("F"))
                {
                    gender ="F";
                    goodInput = true;
                } else
                if(gender.equals("M"))
                {
                    gender ="M";
                    goodInput = true;
                }
                else {
                    gender = gender.replaceAll("(.*.)", "Unspecified");
                    goodInput = true;
                }
            }
            catch(Exception InputMismatchException)
            {
                sc.nextLine();
                System.err.println("Invalid character entered. \n Please enter Female or Male ( F or M ).  ");
            }
        } while (!goodInput);

        System.out.println("Your Gender is :" + gender);

        Person person = new Person(email, name, address, gender);

        registerUser(choice, person);

    }



    private void listAssessments(Member member)
    {
        HashMap<String,Assessment> assessmentHashMap = member.getAssessments();

        if(assessmentHashMap.isEmpty())
        {
            System.out.println("There are no assessments.");
        }
        else
        {
            System.out.println("Your Assessments are :\n");
            SortedSet<String> assessmentOrder = member.sortedAssessmentDates();

            for (Object element : assessmentOrder) {
                String assessment = assessmentHashMap.get(element).toString(); // Assessment instance

                System.out.println(element + " : " + assessment);
            }
        }
    }

    private void registerUser(char user, Person person)
    {
        float height = 0;
        float startWeight = 0;
        String chosenPackage = "";
        String collegeName = "";
        String specialty = "";
        int studentID = 0;
        boolean goodInput = false;

        sc.nextLine();



        if (user == 't')
        {
            System.out.println("Please Enter Your Trainer Specialty : ");
            specialty = sc.nextLine().trim();
            goodInput = true;

            gym.addTrainer(new Trainer(person.getEmail(),person.getName(),person.getAddress(),
                                person.getGender(), specialty));

        }

        if(user == 'm')
        {
            do{
                try
                {
                    System.out.println("Please Enter Your Height in Metres (1.00 - 3.00) : ");
                    height = sc.nextFloat();
                    if ((height >= 1.00) && (height <= 3.00))
                    {
                        goodInput = true;
                    }
                    else
                    {
                        System.err.println("Your height needs to be a METRIC value between 1.00m and 3.00m. " +
                                "\nPlease Try Again. ");
                        goodInput = false;
                    }
                } catch(Exception InputMismatchException)
                {
                    sc.nextLine();
                    System.err.println("Invalid character entered. Please try again. ");
                }
            } while (!goodInput);

            goodInput = false;

            do{
                try
                {
                    System.out.println("Please Enter Your Starting Weight in Kilogrammes ( Kg : 35.00 - 250.00 ) : ");
                    startWeight = sc.nextFloat();
                    if ((startWeight >= 35.00) && (startWeight <= 250.00))
                    {
                        GymUtility.toTwoDecimalPlaces(startWeight);
                        goodInput = true;
                    }
                    else
                    {
                        System.err.println("Your Starting Weight needs to be between 35.00Kg and 250.00Kg. " +
                                "\nPlease Try Again. ");
                        goodInput = false;
                    }
                } catch(Exception InputMismatchException)
                {
                    sc.nextLine();
                    System.err.println("Invalid character type entered. Please try again. ");
                }
            } while (!goodInput);

            goodInput = false;

            do{
                try
                {
                    sc.nextLine();
                    System.out.println("Please Enter Your Chosen Package ( 1 / 2 / 3 / WIT ) : \n");
                    System.out.println("Package 1 : " + packageMap.get("Package 1") + "\n");
                    System.out.println("Package 2 : " + packageMap.get("Package 2")+ "\n");
                    System.out.println("Package 3 : " + packageMap.get("Package 3")+ "\n");
                    System.out.println("Package WIT : " + packageMap.get("WIT")+ "\n");
                    chosenPackage = sc.nextLine().trim();

                    if(chosenPackage.equals("1"))
                    {
                        chosenPackage = "Package 1";
                        goodInput = true;
                    } else
                    if(chosenPackage.equals("2"))
                    {
                        chosenPackage = "Package 2";
                        goodInput = true;
                    } else
                    if(chosenPackage.equals("3"))
                    {
                        chosenPackage = "Package 3";
                        goodInput = true;
                    } else
                    if(chosenPackage.equals("WIT"))
                    {
                        chosenPackage = "WIT";
                        goodInput = true;
                    } else
                    {
                        chosenPackage = "No Package";
                        goodInput = true;
                    }

                } catch(Exception InputMismatchException)
                {
                    sc.nextLine();
                    System.err.println("Invalid character type entered. Please try again. ");
                }
            } while (!goodInput);

            System.out.println("Chosen Package is " + chosenPackage);

            goodInput = false;

            do{
                try
                {
                    sc.nextLine();
                    System.out.println("What is your chosen Membership? ( Premium ( p ) / Student ( s ) ) :");
                    String memberLevel = sc.nextLine().trim().toLowerCase();
                    memberLevel = getFirstChar(memberLevel);

                    if (memberLevel.equals("s"))
                    {
                        do{
                            try
                            {
                                sc.nextLine();
                                System.out.println("Please Enter Your 8-Digit Student ID number : ");
                                studentID = sc.nextInt();

                                if ((studentID < 10000000) || (studentID > 99999999 ))
                                // This means that any ID's with a leading Zero will not be accepted. The student ID would need
                                // to be stored as a string or parsed / decimal formatted to fully accept '0' leading ID's
                                {
                                    System.err.println("Invalid Student ID Number. \nPlease Enter your 8-Digit number : ");
                                    goodInput = false;
                                }
                                else { goodInput = true; }

                            }
                            catch(Exception InputMismatchException)
                            {
                                sc.nextLine();
                                System.err.println("Invalid character type entered. Please try again. ");
                            }
                        } while (!goodInput);

                        goodInput = false;

                        do{
                            try
                            {
                                sc.nextLine();
                                System.out.println("Please Enter Your College Name : ");

                                collegeName = sc.nextLine();

                                if (collegeName.length() > 60 )
                                {
                                    System.err.println("Invalid College Name. Please Enter again : ");
                                    goodInput = false;
                                }
                                else
                                    goodInput = true;

                            }
                            catch(Exception InputMismatchException)
                            {
                                sc.nextLine();
                                System.err.println("Invalid character type entered. Please try again. ");
                            }
                        } while (!goodInput);


                        gym.addStudentMember(new StudentMember(person.getEmail(),person.getName(), person.getAddress(),
                                person.getGender(), height,startWeight,chosenPackage, studentID, collegeName));

                    }

                    else if (memberLevel.equals("p"))
                    {
                        gym.addPremiumMember(new PremiumMember(person.getEmail(), person.getName(), person.getAddress(),
                                                    person.getGender(),height,startWeight,chosenPackage));
                        goodInput = true;
                    }
                    else
                    {
                        System.err.println("Invalid choice! Please enter ( Premium (p) or Student (s) ) : ");
                        goodInput = false;
                    }
                } catch(Exception InputMismatchException)
                {
                    sc.nextLine();
                    System.err.println("Invalid character type entered. Please try again ");
                }
            } while (!goodInput);

        }

    }

    // Method to validate email from https://www.tutorialspoint.com/validate-email-address-in-java

    /**
     * Validate this string to fit the criteria of standard email address of me@you.xx
     * This includes any further domains, such as .co.uk, .com.au.
     * @param email This Person's email as a String
     * @return when this email fits the requirement criteria (me@you.com)
     */
    private static boolean isValid(String email)
    {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    /**
     * determine whether the Person is a Member or Trainer
     * @return character 'm' or 't' indicating Member or Trainer, respectively
     */
    private char isMemberOrTrainer()
    {
        sc.nextLine(); //clear buffer
        boolean goodInput = false;
        char choice ='\0';
        do{
            try
            {
                System.out.println("Are you a Member ( m ) or a Trainer ( t ) ? ");
                String mOrT = sc.nextLine().trim().toLowerCase();
                mOrT = getFirstChar(mOrT);

                choice = mOrT.charAt(0);

                if ((choice =='m') || (choice == 't'))
                {
                    goodInput = true;
                }
                else
                {
                    goodInput=false;
                    System.err.println("Invalid option : " + choice + ". Please enter 'm' or 't'.");

                }

            } catch(Exception InputMismatchException)
            {
                sc.nextLine();
                System.err.println("Invalid character type entered. Please try again ");
            }
        }while(!goodInput);
        return choice;
    }

    /**
     * gets the first character of a String, in lower case form
     * @param string this String that we want the first character of
     * @return the first character of this String as a type String
     */
    private static String getFirstChar(String string)
    {
        string = string.trim().substring(0,1);
        return string;
    }

    /**
     * On exit, save all the data to an xml file through the GymAPI method of save.
     * @see GymAPI#save()
     */
    private void saveOnExit()
    {
        try{gym.save();}
        catch (Exception e){
            System.err.println("Error writing to file: " + e);
        }
    }

    private boolean memberIsStudent(String email)
    {
        boolean student = false;
        Member member = gym.searchMembersByEmail(email);
        if (member.getUser() == 's')
        {
            student = true;
        }

        return student;
    }

    private void fillPackageMap(HashMap<String, String> hashMap)
    {
        /**
         * Initialises the packageMap HashMap to the specific values
         */
        hashMap.put("Package 1", "Allowed access anytime to gym.\nFree access to all classes." +
                "\nAccess to all changing areas including deluxe changing rooms.");
        hashMap.put("Package 2", "Allowed access anytime to gym.\n€3 fee for all classes." +
                "\nAccess to all changing areas including deluxe changing rooms.");
        hashMap.put("Package 3", "Allowed access to gym at off-peak times.\n€5 fee for all classes. " +
                "\nNo access to deluxe changing rooms.");
        hashMap.put("WIT", "Allowed access to gym during term time.\n€4 fee for all classes. " +
                "\nNo access to deluxe changing rooms.");

    }

}
