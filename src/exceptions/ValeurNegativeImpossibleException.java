package exceptions;

public class ValeurNegativeImpossibleException extends Exception{

    /**
     * exception qui est appele lorsqu'une valeur negative est donne alors que seule les valeur positive ou nulle sont acceptee
     * @param valeur valeur donnee
     */
    public ValeurNegativeImpossibleException(double valeur){
        super("La valeur doit etre positive ou nulle, or la valeur vaut : " + valeur);
    }

}
