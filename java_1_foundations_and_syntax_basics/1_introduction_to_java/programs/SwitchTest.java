import java.util.Scanner;

public class SwitchTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your selection: ");
        int selection = input.nextInt();

        String menuOption;

        switch (selection) {
            case 0:
                menuOption = "Operator";
                break;
            case 1:
                menuOption = "Customer Service";
                break;
            default:
                menuOption = "Retry Selection";
                break;
        }
        System.out.println(menuOption);
    }
}