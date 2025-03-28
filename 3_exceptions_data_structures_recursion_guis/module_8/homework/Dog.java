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

    @Override
    public int treat() {

        double calculatedMinutes; 

        if (droolRate < 3.5) {
            calculatedMinutes = ((getPainLevel() * 2.0 ) / getHealth());
        } else if (droolRate <= 7.5) {
            calculatedMinutes = (getPainLevel() / getHealth());
        } else {
            calculatedMinutes = (getPainLevel() / (getHealth() * 2.0));
        }

        int treatmentMinutes = (int) Math.ceil(calculatedMinutes);

        heal();

        return treatmentMinutes;
    }

    @Override
    public void speak() {
        super.speak();
        
        StringBuilder bark = new StringBuilder();
        String barkSound = getPainLevel() > 5 ? "BARK" : "bark";
        
        for (int i = 0; i < getPainLevel(); i++) {
            bark.append(barkSound);
            if (i < getPainLevel() - 1) {
                bark.append(" ");
            }
        }
        
        System.out.println(bark.toString());
    }


    public boolean equals(Object o) {
        if (o instanceof Dog) {
            Dog dog = (Dog) o;
            return super.equals(o) && this.droolRate == dog.droolRate;
        }
        return false;
    }



    }

