import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Rational sum = new Rational(s.nextInt(), s.nextInt());
        Rational prod = sum;
        Rational div = sum;

        while(s.hasNextInt()) {
            int a = s.nextInt();
            int b = s.nextInt();
            sum = sum.sum(new Rational(a, b));
            prod = prod.mul(new Rational(a, b));
            div = div.div(new Rational(a, b));
        }

        System.out.println(sum);
        System.out.println(prod);
        System.out.println(div);
    }
}
