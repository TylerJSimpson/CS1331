package module_3.homework;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        // Initialize variables used in switch statement
        int int_1;
        int int_2;
        int compare_string;
        int return_int;
        double dbl_1;
        double dbl_2;
        double return_dbl;
        String str_1;
        String str_2;
        String error_message = "Invalid input entered. Terminating...";

        // Instantiate Scanner
        Scanner input = new Scanner(System.in);

        System.out.println("List of operations: add subtract multiply divide alphabetize");
        System.out.println("Enter an operation:");

        // Clean Scanner input
        String input_lowercase = input.next().toLowerCase();

        // Core processing logic
        switch (input_lowercase) {
            
            case "add":
                System.out.println("Enter two integers:");

                if (input.hasNextInt()) {
                    int_1 = input.nextInt();
                    if (input.hasNextInt()) {
                        int_2 = input.nextInt();
                        return_int = int_1 + int_2;
                        System.out.println("Answer: " + return_int);
                    }
                    else {
                        System.out.println(error_message);
                    }
                }
                else {
                    System.out.println(error_message);
                }   
                break;
            
            case "subtract":
                System.out.println("Enter two integers:");

                if (input.hasNextInt()) {
                    int_1 = input.nextInt();
                    if (input.hasNextInt()) {
                        int_2 = input.nextInt();
                        return_int = int_1 - int_2;
                        System.out.println("Answer: " + return_int);
                    }
                    else {
                        System.out.println(error_message);
                    }
                }
                else {
                    System.out.println(error_message);
                }   
                break;
            
            case "multiply":
                System.out.println("Enter two doubles:");

                if (input.hasNextDouble()) {
                    dbl_1 = input.nextDouble();
                    if (input.hasNextDouble()) {
                        dbl_2 = input.nextDouble();
                        return_dbl = dbl_1 * dbl_2;
                        System.out.printf("Answer: %.2f\n", return_dbl);
                    }
                    else {
                        System.out.println(error_message);
                    }
                }
                else {
                    System.out.println(error_message);
                }   
                break;
        
            case "divide":
                System.out.println("Enter two doubles:");

                if (input.hasNextDouble()) {
                    dbl_1 = input.nextDouble();
                    if (input.hasNextDouble()) {
                        dbl_2 = input.nextDouble();
                        if (dbl_2 == 0) {
                            System.out.println(error_message);
                        }
                        else {
                            return_dbl = dbl_1 / dbl_2;
                            System.out.printf("Answer: %.2f\n", return_dbl);                            
                        }
                    }
                    else {
                        System.out.println(error_message);
                    }
                }
                else {
                    System.out.println(error_message);
                }   
                break;

            case "alphabetize":
                System.out.println("Enter two words:");

                if (input.hasNext()) {
                    str_1 = input.next();
                    if (input.hasNext()) {
                        str_2 = input.next();

                        compare_string = str_1.toLowerCase().compareTo(str_2.toLowerCase());
                        if (compare_string == 0) {
                            System.out.println("Answer: Chicken or Egg.");
                        }
                        else if (compare_string > 0) {
                            System.out.println("Answer: " + str_2 + " comes before " + str_1 + " alphabetically.");
                        }
                        else if (compare_string < 0) {
                            System.out.println("Answer: " + str_1 + " comes before " + str_2 + " alphabetically.");
                        }

                    }
                    else {
                        System.out.println(error_message);
                    }
                }
                else {
                    System.out.println(error_message);
                }   
                break;
            
            default:
                System.out.println(error_message);
        }
    }
}