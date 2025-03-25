import java.util.InputMismatchException;
import java.util.Scanner;

public class FahrenheitToCelsiusExceptionsMult {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a fahrenheit value: ");
        try {
            int fahrenheit = input.nextInt();
            double celsius = (5.0/9) * (fahrenheit - 32);
            System.out.printf("Fahrenheit: %d\n", fahrenheit);
            System.out.printf("Celsius: %,.1f\n", celsius);
            double x = 1331/fahrenheit;
        }
        catch(InputMismatchException ime) {
            System.out.println("Sorry, that wasn't an int.");
            System.out.println("Please re-run the program again");
        }
        catch(ArithmeticException ae) {
            System.out.println("You entered an invalid number:");
            System.out.println("ae.getMessage()");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}