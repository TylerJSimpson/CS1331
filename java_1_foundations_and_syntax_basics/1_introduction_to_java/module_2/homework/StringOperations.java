package module_2.homework;

public class StringOperations {
    public static void main(String[] args) {
        String my_str = "Tyler Simpson";
        System.out.println(my_str);
        String my_str_a = my_str.replace("T", "a");
        String my_str_z = my_str_a.replace("S", "z");
        System.out.println(my_str_z);
        String web = "www.creditparsepro.io";
        System.out.println(web);
        String web_substring = web.substring(4,18);
        String web_substring_concat = web_substring + "1331";
        System.out.println(web_substring_concat);
    }
}