import java.util.Random;

public class Die {

    //instance variables
    private Random rand;
    private int faceValue;

    //static constants/variables
    public static final int SIDES = 6;

    //constructors
    public Die() {
        faceValue = 1;
        rand = new Random();
    }

    //methods
    public int roll() {
        faceValue = rand.nextInt(SIDES) + 1;
        return faceValue;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public String toString() {
        return "Die with face value: " + faceValue;
    }

    //test method
    public static void main(String[] args) {
        Die die1 = new Die();

        System.out.println(die1);
        System.out.println(die1.roll());
        System.out.println(die1.roll());
    }

}