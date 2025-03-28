public class Cat extends Pet {

    // Variables

    private int miceCaught;

    // Constructors

    public Cat(String name, double health, int painLevel) {
        super(name, health, painLevel);
        this.miceCaught = 0;

    }

    public Cat(String name, double health, int painLevel, int miceCaught) {
        super(name, health, painLevel);

        if (miceCaught < 0) {
            this.miceCaught = 0;
        } else {
            this.miceCaught = miceCaught;
        }

    }

    // Getters
    public int getMiceCaught() {
        return miceCaught;
    }

    // Methods

    @Override
    public int treat() {

        double calculatedMinutes; 

        if (miceCaught < 4) {
            calculatedMinutes = ((getPainLevel() * 2.0 ) / getHealth());
        } else if (miceCaught <= 7) {
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
        
        StringBuilder meow = new StringBuilder();
        String meowSound = getPainLevel() > 5 ? "MEOW" : "meow";
        
        for (int i = 0; i < miceCaught; i++) {
            meow.append(meowSound);
            if (i < miceCaught - 1) {
                meow.append(" ");
            }
        }
        
        System.out.println(meow.toString());
    }

    public boolean equals(Object o) {
        if (o instanceof Cat) {
            Cat cat = (Cat) o;
            return super.equals(o) && this.miceCaught == cat.miceCaught;
        }
        return false;
    }

}
