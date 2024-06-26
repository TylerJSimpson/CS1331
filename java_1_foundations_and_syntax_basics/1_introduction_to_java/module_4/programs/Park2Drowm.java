package module_4.programs;

public class Park2Drowm {
    public static void main(String[] args) {
        double[][] array2d = {{80, 70, 75},
                              {69, 72, 74}};
        final double MIN_TEMP = 75;
        final double MAX_TEMP = 90;

        for (int row = 0; row < array2d.length; row++) {
            for (int col = 0; col < array2d[row].length; col++) {
                if ((array2d[row][col] <= MAX_TEMP) 
                    && (array2d[row][col] >= MIN_TEMP)) {
                        System.out.println("Go to park.");
                }
            }
        }

    }
}