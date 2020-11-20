import java.util.Scanner;

public class Soluzione2 {

    /**
     * pre-requisiti: n e' un numero positivo
     * post-condizioni: stampa una scacchiera 8 x 8 le cui caselle hanno dimensione N x N
     */
    public static void stampaScacchiera(int n) {
        final int CASELLE = 8;
        final char NERO = '-';
        final char BIANCO = '#';
        
        for (int riga = 0; riga < CASELLE * n; riga++) {
            for (int colonna = 0; colonna < CASELLE * n; colonna++) 
                System.out.print((riga / n + colonna / n)%2 == 0 ? NERO : BIANCO);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        stampaScacchiera(s.nextInt());
        s.close();
    }
}