public class Bin2Diff<X,Y> {

    private X content1;
    private Y content2;

    public Bin2Diff(X content1, Y content2) {
        this.content1 = content1;
        this.content2 = content2;
    }

    public X getContent1() {
        return content1;
    }

    public void setContent1(X content1) {
        this.content1 = content1;
    }

    public Y getContent2() {
        return content2;
    }

    public void setContent2(Y content2) {
        this.content2 = content2;
    }

    public static void main(String[] args) {
        Bin2Diff<String,Car> test = new Bin2Diff<>("My dream car", new Car("Yuhina", "Spark", "2037"));
    }
    
}
