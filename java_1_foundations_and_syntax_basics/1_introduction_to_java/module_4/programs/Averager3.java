public class Averager3 {
    public static void main(String[] args) {
        double sum = 0;
        for (String num : args) {
            sum += Double.parseDouble(num); //Convert str to dbl
        }
        double average = (args.length > 0) ? (sum/args.length) : 0;
        System.out.println("Average is: " + average);
    }
}