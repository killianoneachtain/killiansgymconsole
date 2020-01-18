import java.util.ArrayList;
import java.util.List;


import java.lang.Math;

/**
 * A utility class containing only static methods to calculate Body Mass Index(BMI) and Ideal Weight.
 * There is also BMI categorization based on World Health Organisation guidelines.
 */
public class GymUtility
{
    /**
     * Gets a type double value to two decimal places($.##)
     * @param num this type double that is to be rounded to two decimal places
     * @return the float value to two decimal places
     */
    public static float toTwoDecimalPlaces(double num)
    {
        return (float) ((int) (num *100 ) /100.0);
    }

    public static float toOneDecimalPlace(double num)
    {
        return (float) (Math.round(num * 10) / 10.0);
    }
    /**
     * Checks if the member is at their Ideal Body Weight by their most recent Assessment weight value
     * @param member this members details
     * @param assessment this assessments  details
     * @return true where this members most recent assessment weight is ±2kg of Ideal Weight
     * @see Member, Assessment
     */
    public static boolean isIdealBodyWeight(Member member, Assessment assessment)
    {
        double idealBodyWeight = 0.0;

        if (member.getGender().equals("M"))
        {
            idealBodyWeight = 50.00 + ((((member.getHeight()*100)  - 152.4) / 2.54) * 2.3);
        }
        else if ((member.getGender().equals("F")) || (member.getGender().equals("Unspecified")))
        {
            idealBodyWeight = 45.5 + ((((member.getHeight()*100) - 152.4) / 2.54) * 2.3);
        }

        if((assessment.getWeight() >= (idealBodyWeight - 0.3)) &&
                ((assessment.getWeight() <= (idealBodyWeight + 0.3))))
        {

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Calculates the Body Mass Index(BMI) of this member
     * @param member this members details
     * @param assessment this members assessment to be used in calculations
     * @return the BMI value correct to two decimal places
     * @see #toTwoDecimalPlaces(double), Member, Assessment
     */
    public static double calculateBMI(Member member, Assessment assessment)
    {

        return toTwoDecimalPlaces(assessment.getWeight() / Math.pow(member.getHeight(), 2.0));
    }

    /**
     * Determines the BMI category based on this value, if the value doesn't satisfy any of the
     * categories, then "No Verdict" is returned.
     * @param bmiValue this BMI value, based on this Member and this Assessment
     * @return an object of type String containing the category the BMI value is in
     * @see #calculateBMI(Member, Assessment)
     */
    public static String determineBMICategory(double bmiValue)
    {

        String verdict= "No Verdict";


        if((bmiValue >0) &&(bmiValue < 16))
        {
            verdict = "SEVERELY UNDERWEIGHT";
        }

        if((bmiValue >=16) && (bmiValue <18.5))
        {
            verdict = "UNDERWEIGHT";
        }

        if((bmiValue >= 18.5) && (bmiValue <25))
        {
            verdict="NORMAL";
        }

        if((bmiValue >= 25) && (bmiValue <30))
        {
            verdict="OVERWEIGHT";
        }

        if((bmiValue >= 30) && (bmiValue <35))
        {
            verdict="MODERATELY OBESE";
        }

        if(bmiValue >= 35)
        {
            verdict="SEVERELY OBESE";
        }

        return verdict;
    }

}