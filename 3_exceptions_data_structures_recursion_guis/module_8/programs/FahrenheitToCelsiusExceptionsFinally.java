import java.util.InputMismatchException;
import java.util.Scanner;

public class FahrenheitToCelsiusExceptionsFinally {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean success = false;
        int fahrenheit = 0;

        while (!success) {
            try {
                System.out.print("Enter a fahrenheit value: ");
                fahrenheit = input.nextInt();
                success = true;
            }
            catch(InputMismatchException e) {
                System.out.println("Sorry, that wasn't an int.");
                System.out.println("Please try again");
            }
            finally {
                input.nextLine();
            }
        }
        System.out.print("Enter a day of the week: ");
        String day = input.nextLine();
        double celsius = (5.0/9) * (fahrenheit - 32);
        System.out.printf("%s Fahrenheit: %d\n", day, fahrenheit);
        System.out.printf("%s Celsius: %,.1f\n", day, celsius);
    }
}