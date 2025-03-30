public class InvalidPetException extends RuntimeException{
    
    // Constructors

    public InvalidPetException() {
        super("Your pet is invalid!");
    }

    public InvalidPetException(String s) {
        super(s);
    }

}
