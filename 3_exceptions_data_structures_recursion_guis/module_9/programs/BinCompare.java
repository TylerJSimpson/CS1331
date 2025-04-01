public class BinCompare<T extends Comparable<T>> {
    private T content1;
    private T content2;

    public BinCompare(T content1, T content2) {
        this.content1 = content1;
        this.content2 = content2;
    }

    public T greaterValue() {
        return (content1.compareTo(content2) >= 0 ? content1 : content2);
    }

    public static void main(String[] args) {
        BinCompare<String> test = new BinCompare<>("I'm a basic String", "short and stout");
        System.out.println(test.greaterValue());
    }
}