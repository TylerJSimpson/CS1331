import java.util.InputMismatchException;
import java.util.Scanner;

public class FahrenheitToCelsiusExceptionsCombined {

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
        catch(InputMismatchException | ArithmeticException e) {
            System.out.println("Sorry, that wasn't an valid value.");
            System.out.println("Please re-run the program again");
        }
    }
}