package module_4.programs;

public class Averager1 {
    public static void main(String[] args) {
        double[] weekHighs = {80, 70, 75, 69, 72, 74, 90};
        double highSum = 0;

        for (int index = 0; index < weekHighs.length; index++) {
            highSum = highSum + weekHighs[index];
        }
        double averageHighs = highSum / weekHighs.length;
        System.out.println("Average is: " + averageHighs);

    }
}