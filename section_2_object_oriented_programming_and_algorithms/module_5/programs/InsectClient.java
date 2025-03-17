public class InsectClient {
    public static void main(String args[]) {
        System.out.println(Insect.produceRandomFact());
        Insect bug1 = new Insect(13, 31, 0);
        System.out.println("BUG1");
        System.out.println(bug1.toString());
        System.out.println(Insect.getPopulation());

        Insect bug2 = new Insect(13);
        System.out.println("BUG2");
        System.out.println(bug2.toString());
        System.out.println(Insect.getPopulation());

        Insect bug3 = new Insect();
        System.out.println("BUG3");
        System.out.println(bug3);
        System.out.println(Insect.getPopulation());
    }
}