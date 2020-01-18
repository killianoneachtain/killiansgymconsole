import java.util.SortedSet;

/**
 * Represents an assessment taken by a Trainer.  It stores weight, thigh, waist, comment fields and
 * a Trainer that entered the member’s assessment (i.e. personal trainer).
 */
public class Assessment
{
    /**
     * The weight of this Assessment in kilogrammes (Kg)
     */
    private double weight;

    /**
     * The thigh measurement of this Assessment, in metres(m)
     */
    private double thigh;

    /**
     * The waist measurement of this Assessment, in metres(m)
     */
    private double waist;

    /**
     * The comment on this Assessment (normally added by a trainer)
     */
    private String comment;

    /**
     * An instance of Object type Trainer, stores the trainer information who has added this Assessment
     */
    public Trainer trainer;




    /**
     * Changes the weight value of this Assessment
     * @param weight This Assessment's new weight value
     */
    public void setWeight(double weight) {  this.weight = weight;  }

    /**
     * Changes the thigh value of this Assessment
     * @param thigh This Assessment's new thigh value
     */
    public void setThigh(double thigh) {  this.thigh = thigh;  }

    /**
     * Changes the waist value of this Assessment
     * @param waist This Assessment's new waist value
     */
    public void setWaist(double waist) { this.waist = waist; }

    /**
     * Changes the comment String of this Assessment
     * @param comment This Assessment's new comment
     */
    public void setComment(String comment) { this.comment = comment; }

    /**
     * Changes the Trainer of this Assessment
     * @param trainer This Assessment's new Trainer
     * @see Trainer
     */
    public void setTrainer(Trainer trainer) { this.trainer = trainer; }


    /**
     * Gets the weight of this Assessment
     * @return this Assessment's weight
     */
    public double getWeight() { return weight; }

    /**
     * Gets the thigh value of this Assessment
     * @return this Assessment's thigh value
     */
    public double getThigh() { return thigh;  }

    /**
     * Gets the waist value of this Assessment
     * @return this Assessment's waist value
     */
    public double getWaist() { return waist;  }

    /**
     * Gets the comment String of this Assessment
     * @return this Assessment's comment String
     */
    public String getComment() { return comment;  }

    /**
     * Gets the Trainer of this Assessment
     * @return this Assessment's Trainer
     * @see Trainer
     */
    public Trainer getTrainer() { return trainer;  }

    /**
     * Creates a new instance of type Assessment containing weight, thigh, waist and comment
     * @param weight this Assessment's weight value, measured by metres(m)
     * @param thigh this Assessment's thigh value, measured by kilogrammes(Kg)
     * @param waist this Assessment's waist value, measured by kilogrammes(Kg)
     * @param comment this Assessment's comment String
     */
    public Assessment(double weight, double thigh, double waist, String comment)
    {
        this.weight = weight;
        this.thigh = thigh;
        this.waist = waist;
        this.comment = comment;
    }

    public Assessment()
    {

    }

    /**
     * Creates a String instance containing the details of this Assessment
     * @return this Assessment's values for weight, thigh, waist and comment
     */
    public String toString()
    {
        return ("Weight : " + weight + ("Kg, Thigh : " + thigh + "m, Waist : "
                + waist + "m, Comment : " + comment + "Added by Trainer : " + trainer.getName() ));
    }
}
