public class PrimitiveOperations {
    public static void main(String[] args) {
        int my_integer = 7;
        double my_float = 3.4;
        System.out.println(my_integer);
        System.out.println(my_float);
        double mult_integer_float = my_integer * my_float;
        System.out.println(mult_integer_float); 
        double my_integer_float = my_integer;
        System.out.println(my_integer_float); 
        int my_float_int = (int) my_float;
        System.out.println(my_float_int);
        char my_char = 'X';
        System.out.println(my_char);
        char my_char_lowercase = Character.toLowerCase(my_char);
        System.out.println(my_char_lowercase);
    }
}