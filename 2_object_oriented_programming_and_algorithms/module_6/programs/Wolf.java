import java.util.Arrays;
public class Wolf extends Canine implements Groomable, Comparable<Wolf> {
    
    protected int rank;

    public Wolf(double size, int rank) {
        super(size);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void bark() { //3 times the default canine bark
        for (int i = 1; i <= 3; i++)
            super.bark();
    }

    public void bark(int barkMultiple) {
        for (int i = 1; i <= barkMultiple; i++) {
            super.bark();
        }
    }

    public void groom() {
        System.out.println("lick");
    }

    public int compareTo(Wolf anotherWolf) {
        return -(rank - ((Wolf)anotherWolf).rank);
    }

    public String toString() {
        return ("Rank " + rank + ", Size "+ size);
    }

    public static void main(String[] args) {
        Wolf[] pack = {
            new Wolf(17.1, 2),
            new Wolf(3, 10),
            new Wolf(9.2, 7),
            new Wolf(9.1, 8),
            new Wolf(5, 9),
            new Wolf(10, 6),
            new Wolf(16, 5)
        };

        System.out.println("Unsorted Pack: " + Arrays.toString(pack));
        Arrays.sort(pack);
        System.out.println("===============================");
        System.out.println("Sorted Pack: " + Arrays.toString(pack));
    }

}