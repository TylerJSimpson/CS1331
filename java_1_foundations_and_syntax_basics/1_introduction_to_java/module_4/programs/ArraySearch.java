public class ArraySearch {
    public static void main(String[] args) {
        String[] concepts = {"abstraction","polymorphism","inheritance","encapsulation"};
        String result = "not found";

        for (String concept : concepts) {
            if (concept.equals("polymorphism")) {
                result = "Found.";
                break;
            }
        }

        System.out.println(result);
    }
}