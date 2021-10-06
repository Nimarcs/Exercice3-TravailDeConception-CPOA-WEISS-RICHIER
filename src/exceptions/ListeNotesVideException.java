package exceptions;

public class ListeNotesVideException extends Throwable {

    /**
     * exception appelle lorsque la liste de note d'une matiere est vide
     * @param nom nom de la matiere
     */
    public ListeNotesVideException(String nom){
        super("La liste de note de " + nom + " est vide");
    }
}
