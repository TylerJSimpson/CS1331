import java.util.ArrayList;

public class IntArrayListDemo {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(90);
        scores.add(95);
        scores.add(110);
        scores.add(99);
        scores.add(105);
        System.out.println(scores.toString());

        int sum = 0;
        for (Integer score : scores) {
            sum += score;
        }
        System.out.println("Total points: " + sum);
    }
    
}

