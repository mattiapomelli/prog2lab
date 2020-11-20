import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();

        for (int i = 0; i < n + 1 ; i++) {              //prima meta'
            for (int j = 0; j < 2*n +1 ; j++) {
                if (j >= n - i && j <= n + i)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }

        for (int i = n-1; i >= 0 ; i--) {               // seconda meta'
            for (int j = 0; j < 2*n +1 ; j++) {
                if (j >= n - i && j <= n + i)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}