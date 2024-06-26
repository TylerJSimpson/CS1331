package module_4.programs;

public class ArraySearchMethod {
    public static void main(String[] args) {
        
        String[] concepts = {"abstraction", "polymorphism", "inheritance", "enapsulation"};

        System.out.println(searchStringArray("polymorphism", concepts));
        System.out.println(searchStringArray("breakdancing", concepts));
        
    }

    public static boolean searchStringArray(String target, String[] array) {
        boolean result = false;
        for (String element : array) {
            if (element.equals(target)) {
                result = true;
                break;
            }
        }
    return result;
    }
}
