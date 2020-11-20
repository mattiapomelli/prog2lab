import java.util.Scanner;

public class Soluzione {

    /**
     * post-condizioni: stampa il carattere c n volte
     *                  solleva un'eccezione di tipo IllegalArgumentException se n non e' positivo
     */
    public static void stampaRipetuta(char c, int n) {
        if (n < 0) throw new IllegalArgumentException("Il numero in input deve essere positivo");
        for(int i = 0; i < n; i++)
            System.out.print(c);
    }

    /**
     * post-condizioni: stampa una riga composta da i spazi, poi n asterischi
     *                  solleva un'eccezione di tipo IllegalArgumentException se n o i non sono positivo
     */
    public static void stampaRiga(int i, int n) {
        if (n < 0 || i < 0) throw new IllegalArgumentException("Il numero in input deve essere positivo");
        stampaRipetuta(' ', i);
        stampaRipetuta('*', n);
        System.out.println();
    }

    /**
     * post-condizioni: stampa un rombo di diagonale 2n + 1
     *                  solleva un'eccezione di tipo IllegalArgumentException se n non e' positivo
     */
    public static void stampaRombo(int n) {
        if (n < 0) throw new IllegalArgumentException("Il numero in input deve essere positivo");
        for (int i = 0; i < n + 1 ; i++) {              //prima meta'
            stampaRiga(n - i, 2 * i + 1);         
        }

        for (int i = n-1; i >= 0 ; i--) {               // seconda meta'
            stampaRiga(n - i, 2 * i + 1); 
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        stampaRombo(s.nextInt());
        s.close();
    }
}