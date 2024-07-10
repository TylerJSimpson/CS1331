public class Fly {
    
    //Instace variables
    private double mass;
    private double speed;

    //Static constants/variables
    public static final double initMass = 5;
    public static final double initSpeed = 10;

    //Constructors
    public Fly() {
        this(initMass, initSpeed);
    }

    public Fly(double mass) {
        this(mass, initSpeed);
    }

    public Fly(double mass, double speed) {
        this.mass = mass;
        this.speed = speed;
    }

    //Methods
    public double getMass() {
        return mass;
    }

    public double getSpeed() {
        return speed;
    }

    public void setMass(double mass) {
        if (mass >= 0) {
            this.mass = mass;
        }
    }

    public void setSpeed(double speed) {
        if (speed >= 0) {
            this.speed = speed;
        }
    }

    public void grow(int amount) {
        if (amount >= 0) {
            mass = mass + amount;
            if (mass < 20) {
                speed = speed + amount;
            }
            else {
                speed = speed + (amount / 2);
            }
        }

    }

    public boolean isDead() {
        if (mass == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public String toString() {
        String returnString;
        if (mass == 0) {
            returnString = "I'm dead, but I used to be a fly with a speed of " + String.format("%.2f",speed) + ".";
        }
        else {
            returnString = "I'm a speedy fly with " + String.format("%.2f",speed) + " speed and " + String.format("%.2f",mass) + " mass.";
        }
        return returnString;
    }

    //Main method
    public static void main(String[] args) {
        //Test constructors and toString()
        Fly fly1 = new Fly();
        System.out.println("Fly 1:");
        System.out.println(fly1);
        Fly fly2 = new Fly(15);
        System.out.println("Fly 2:");        
        System.out.println(fly2);        
        Fly fly3 = new Fly(100, 200);
        System.out.println("Fly 3:");        
        System.out.println(fly3);        

        //Test getters and setters
        fly1.setMass(1000);
        System.out.println(fly1.getMass());
        fly1.setMass(-10);
        System.out.println(fly1.getMass());
        fly1.setMass(0);
        System.out.println(fly1.getMass());

        //Test grow() and isDead()
        System.out.println("Check if fly1 and fly2 are dead:");
        System.out.println(fly1.isDead());
        System.out.println(fly2.isDead());
        System.out.println("Grow fly2 under and over mass threshold:");
        System.out.println(fly2.getMass());
        System.out.println(fly2.getSpeed());
        fly2.grow(3);
        System.out.println(fly2.getMass());
        System.out.println(fly2.getSpeed());
        fly2.grow(5);
        System.out.println(fly2.getMass());
        System.out.println(fly2.getSpeed());
        fly2.grow(10);
        System.out.println(fly2.getMass());
        System.out.println(fly2.getSpeed());        
    }

}