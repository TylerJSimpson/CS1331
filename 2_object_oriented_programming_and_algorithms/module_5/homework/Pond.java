public class Pond {

    //Main Method
    public static void main(String[] args) {

        Frog Peepo = new Frog("Peepo");
        Frog Pepe = new Frog("Pepe", 10, 15);
        Frog Peepaw = new Frog("Peepaw", 4.6, 5);
        Frog Frogger = new Frog("Frogger", 11, 16);

        Fly fly1 = new Fly(1, 3);
        Fly fly2 = new Fly(6);
        Fly fly3 = new Fly();

        //Set the species of any Frog to “1331 Frogs”
        Frog.setSpecies("1331 Frogs");

        //Print out on a new line the description of the Frog named Peepo given by the toString method.
        System.out.println(Peepo);

        //Have the Frog named Peepo attempt to eat the Fly with a mass of 6.
        Peepo.eat(fly2);

        //Print out on a new line the description of the Fly with a mass of 6 given by the toString method.
        System.out.println(fly2);

        //Have the Frog named Peepo grow by 8 months.
        Peepo.grow(8);

        //Have the Frog named Peepo attempt to eat the Fly with a mass of 6.
        Peepo.eat(fly2);

        //Print out on a new line the description of the Fly with a mass of 6 given by the toString method.
        System.out.println(fly2);

        //Print out on a new line the description of the Frog named Peepo given by the toString method.
        System.out.println(Peepo);

        //Print out on a new line the description of your own Frog given by the toString method.
        System.out.println(Frogger);

        //Have the Frog named Peepaw grow by 4 months.
        Peepaw.grow(4);

        //Print out on a new line the description of the Frog named Peepaw given by the toString method.
        System.out.println(Peepaw);

        //Print out on a new line the description of the Frog named Pepe given by the toString method.
        System.out.println(Pepe);
    
    }
}