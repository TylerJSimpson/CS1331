public class Frog extends Pond {

    //Instance variables
    private String name;
    private int age;
    private double tongueSpeed;
    private boolean isFroglet;
    private static String species = "Rare Pepe";

    //Static constants/variables
    public static final int initAge = 5;
    public static final double initTongueSpeed = 5;
    public static final boolean initIsFroglet = false;

    //Constructors
    public Frog(String name) {
        this(name, initAge, initTongueSpeed);
    }

    public Frog(String name, double ageInYears, double tongueSpeed) {
        this(name, (int) (ageInYears *12), tongueSpeed);
    }

    public Frog(String name, int age, double tongueSpeed) {
        this.name = name;
        this.age = age;
        this.tongueSpeed = tongueSpeed;
        this.isFroglet = ((age > 1) && (age < 7));
    }

    //Methods
    public String getSpecies() {
        return species;
    }

    public static void setSpecies(String setSpecies) {
        species = setSpecies;
    }

    public void grow() {
        age++;

        if (age < 12) {
            tongueSpeed += 1;
        } else if (age >= 30 && tongueSpeed > 5) {
            tongueSpeed -= 1;
        }

        if ((age > 1) && (age < 7)) {
            isFroglet = true;
        } else {
            isFroglet = false;
        }
    }

    public void grow(int inputAge) {
        for (int counter = 0; counter < inputAge; counter++) {
            age++;
    
            if (age <= 12) {
                tongueSpeed += 1;
            } else if (age > 30 && tongueSpeed > 5) {
                tongueSpeed -= 1;
            }
    
            if ((age > 1) && (age < 7)) {
                isFroglet = true;
            } else {
                isFroglet = false;
            }
        }
    }
    
    public void eat(Fly fly) {
        if (fly.isDead()) {
            return;
        }
    
        if (tongueSpeed > fly.getSpeed()) {
            if (fly.getMass() >= (0.5 * age)) {
                grow();
            }
            fly.setMass(0);
        } else {
            fly.grow(1);
        }
    }

    public String toString() {
        String returnString;
        if (isFroglet) {
            returnString = "My name is " + name + " and I'm a rare froglet! I'm " + age + " months old and my tongue has a speed of " + String.format("%.2f", tongueSpeed) + ".";
        } else {
            returnString = "My name is " + name + " and I'm a rare frog. I'm " + age + " months old and my tongue has a speed of " + String.format("%.2f", tongueSpeed) + ".";
        }
        return returnString;
    }


    //Main method
    public static void main(String[] args) {
        //Test constructors and toString()
        System.out.println("Creating a Frog with just name and printing object:");
        Frog frog1 = new Frog("testname1");
        System.out.println(frog1);

        System.out.println("Creating a Frog with just name and printing object:");
        Frog frog2 = new Frog("testname2", 2.7, 100);
        System.out.println(frog2);

        System.out.println("Creating a Frog with just name and printing object:");
        Frog frog3 = new Frog("testname3", 6, 50);
        System.out.println(frog3);

        //Test getters and setters
        System.out.println(frog1.getSpecies());
        frog1.setSpecies("New Species!");
        System.out.println(frog1.getSpecies());
        System.out.println(frog2.getSpecies());

        //Test methods
        frog1.grow();
        System.out.println(frog1);
        frog1.grow();
        System.out.println(frog1);
        frog1.grow(20);
        System.out.println(frog1);
        frog1.grow(25);
        System.out.println(frog1);

        Fly fly10 = new Fly();
        System.out.println(fly10);
        frog2.eat(fly10);
        System.out.println(fly10);
        System.out.println(frog2);

        Fly fly11 = new Fly(200);
        System.out.println(fly11);
        frog1.eat(fly11);
        System.out.println(fly11);
        System.out.println(frog1);

    }

}