public class Bin<T> {
    
    private T content;

    public Bin(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public static void main(String[] args) {
        Bin<String> test = new Bin<>("I'm a basic String");
        System.out.println(test);
    }

}
