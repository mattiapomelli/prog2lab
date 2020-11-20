import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        IntSet set = new IntSet();

        while(s.hasNextInt()) {
            set.insert(s.nextInt());
        }

        System.out.println(set.size());
        s.close();
    }
}