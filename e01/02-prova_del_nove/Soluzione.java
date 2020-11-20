import java.util.Scanner;

public class Soluzione {

    /**
     * pre-condizioni: n e' un numero positivo
     * post-condizioni: restituisce la somma delle cifre di n
     */
    public static int sommaCifre(int n) {
        return n % 9;
    }

    /**
     * post-condizioni: restituisce true se la prova del nove e' soddisfatta per
     *                  la moltiplicazione f1 * f2 = p , false altrimenti
     */
    public static boolean provaDelNove(int f1, int f2, int p) {
        int cifra = sommaCifre(sommaCifre(f1) * sommaCifre(f2));
        return cifra == sommaCifre(p); 
    }

    /**
     * pre-requisiti: n e' un numero naturale
     * post-condizioni: stampa le terne di interi positivi A, B e C , minori di N, per cui A x B e' diverso da C,
     *                  ma la prova del nove funziona.
     */
    public static void stampaTerne(int n) {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                for (int k = 1; k < n; k++) {
                    if(i * j != k && provaDelNove(i, j, k)) 
                        System.out.println(i + " " + j + " " + k);       
                }
            }
        }
    }

    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        stampaTerne(s.nextInt());
        s.close();
    }
}