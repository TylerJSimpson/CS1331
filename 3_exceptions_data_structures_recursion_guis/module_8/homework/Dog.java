public class Dog extends Pet {
    
    // Variables

    private double droolRate;

    // Constructors

    public Dog(String name, double health, int painLevel) {

        super(name, health, painLevel);
        this.droolRate = 5.0;
    }

    public Dog(String name, double health, int painLevel, double droolRate) {
        
        super(name, health, painLevel);
        // If droolRate is <= 0 set to 0.5
        if (droolRate <= 0) {
            this.droolRate = 0.5;
        } else {
            this.droolRate = droolRate;
        }
    }

    // Getters

    public double getDroolRate() {
        return droolRate;
    }

    // Methods

    


}
