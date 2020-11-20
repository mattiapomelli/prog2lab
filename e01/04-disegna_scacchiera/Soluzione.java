import java.util.Scanner;

public class Soluzione {

    /**
     * pre-requisiti: n e' un numero positivo
     * post-condizioni: stampa il carattere c n volte
     */
    public static void stampaRipetuta(char c, int n) {
        for(int i = 0; i < n; i++)
            System.out.print(c);
    }

    /**
     * pre-requisiti: n e' un numero positivo
     * post-condizioni: stampa una scacchiera 8 x 8 le cui caselle hanno dimensione N x N
     */
    public static void stampaScacchiera(int n) {
        final int CASELLE = 8;
        
        for (int riga = 0; riga < CASELLE; riga++) {
            for(int k = 0; k < n; k++) {
                for (int colonna = 0; colonna < CASELLE; colonna++) {
                    if((riga + colonna)%2 == 0)
                        stampaRipetuta('-', n);
                    else
                        stampaRipetuta('#', n);
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        stampaScacchiera(s.nextInt());
        s.close();
    }
}