import java.util.Scanner;

public class FahrenheitToCelsiusMTLnextLine {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Fahrenheit value: ");
        int fahrenheit = input.nextInt();
        input.nextLine(); //cleans up newline at end of user input
        System.out.print("Enter a day of the week along with the month day and year: ");
        String day = input.nextLine();
        double celsius = (5.0/9) * (fahrenheit - 32);
        System.out.println(day + " Fahrenheit: " + fahrenheit);
        System.out.println(day + " Celsius: " + celsius);
    }
}