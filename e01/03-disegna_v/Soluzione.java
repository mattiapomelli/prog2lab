import java.util.Scanner;

public class Soluzione {

    /**
     * post-condizioni: stampa il carattere c
     */
    public static void stampaCarattere (char c) {
        System.out.print(c);
    }

    /**
     * pre-requisiti: n e' un numero positivo
     * post-condizioni: stampa una V che occupa n righe, disegnata con il carattere c
     */
    public static void stampaV(int n, char c) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= (2*n - 1); j++) {
                if (j == i || j == (2*n - i))
                    stampaCarattere(c);
                else 
                    stampaCarattere(' ');
            }
            stampaCarattere('\n');
        } 
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        stampaV(s.nextInt(), '*');
        s.close();
    }
}