public abstract class Pet {

    // Variables

    private String name;
    private double health;
    private int painLevel;

    // Constructors

    public Pet(String name, double health, int painLevel) {

        this.name = name;

        // Ensure health is between 0.0 and 1.0
        if (health <= 1.0 && health >= 0.0) {
            this.health = health;
        } else if (health > 1.0) {
            this.health = 1.0;
        } else {
            this.health = 0.0;
        }

        // Ensure painLevel is between 1 and 10
        if (painLevel <= 10 && painLevel >= 1) {
            this.painLevel = painLevel;
        } else if (painLevel > 10) {
            this.painLevel = 10;
        } else {
            this.painLevel = 1;
        }
    }

    // Getters

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public int getPainLevel() {
        return painLevel;
    }

    // Methods

    public abstract int treat();

    public void speak() {
        
        String baseString = "Hello! My name is ";
        // Print Pet's name, uppercase if painLevel > 5
        if (this.painLevel > 5) {
            System.out.println(baseString.toUpperCase() + this.name.toUpperCase());
        } else {
            System.out.println(baseString + this.name);
        }
    }

    public boolean equals(Object o) {
        if (o instanceof Pet) {
            Pet pet = (Pet) o;
            return this.name.equals(pet.name);
        }
        return false;
    }

    protected void heal() {
        this.health = 1.0;
        this.painLevel = 1;
    }


}
