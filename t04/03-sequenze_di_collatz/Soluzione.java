import java.util.Scanner; 

public class Soluzione {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int i = 1;
        s.close();

        System.out.print(n + " ");
        while ( n != 1) {
            n = (n % 2 == 0) ? n = n / 2 : n *3 + 1;           
            i++;
            System.out.print(n + " ");
        }
        System.out.print(i + "\n");
    }
}