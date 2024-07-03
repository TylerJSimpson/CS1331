package module_3.programs;

import java.util.Scanner;

public class ifElseTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter temp in F: ");
        int temp_f = input.nextInt();
        double temp_c = (5.0/9) * (temp_f - 32);
        if ((temp_c > 20) && (temp_c < 35)) {
            System.out.printf("Go outside it's %.2f C out!\n", temp_c);
        }
        else {
            System.out.printf("Get inside it's %.2f out!\n", temp_c);
        }
    }
}