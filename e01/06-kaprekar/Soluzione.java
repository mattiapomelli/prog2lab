import java.util.Scanner;

public class Soluzione {
    
    /**
    * Post-condizioni: restituisce una stringa che rappresenta n
    */
    public static String convertToString(long n) {
        return "" + n;
    }

    /**
    * Pre-requisiti: s non e' un riferimento null
    *                s e' una stringa che rappresenta un numero 
    * Post-condizioni: restituisce una rappresentazione di s tramite un long
    */
    public static long convertToLong(String s) {
        int len = s.length();
        long n = 0;
        for (int i = 0; i < len; i++) {
            n *= 10;
            n += s.charAt(i) - '0';
        }
        return n;
    }

    /**
     * Pre-requisiti: s non e' un riferimento null
     * Post-condizioni: restituisce la stringa reverse di s
     */
    public static String reverse(String s) {
        int len = s.length();
        if (len <= 1) return s;
        return s.charAt(len - 1) + reverse(s.substring(1, len-1)) + s.charAt(0);
    }

    /**
     * pre-requisiti: s non e' un riferimento null
     *                a e b sono numeri compresi tra 0 e la lunghezza di s - 1
     * post-condizioni: restituisce una copia della stringa s, con i caratteri in posizione a e b scambiati tra di loro
     */
    public static String swapCharacters(String s, int a, int b) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if( i == a)
                c = s.charAt(b);
            if ( i == b)
                c = s.charAt(a);
            result += c;
        }
        return result;
    }

    /**
     * pre-requisiti: s non e' un riferimento null
     *                s e' una stringa che rappresenta un numero
     *                a e b sono numeri compresi tra 0 e la lunghezza di s - 1
     * post-condizioni: restituisce una copia della stringa s in cui le cifre in posizione a e b sono scambiate,
     *                  se quella in posizione a e' maggiore di quella in posizione b
     */
    public static String ordinaCoppia(String s, int a, int b) {
        if(s.charAt(a) > s.charAt(b))
            s = swapCharacters(s, a, b);
        return s;
    }

    /**
     * pre-requisiti: n positivo
     * post-condizioni: restituisce un numero che rappresenta le cifre di n ordinate dalla piu piccola alla piu grande
     *                  se crescente e' true, altrimenti dalla piu grande alla piu piccola
     */
    public static long ordinaCifre(long n, boolean crescente) {
        String s = convertToString(n);
        s = ordinaCoppia(s, 0, 1);
        s = ordinaCoppia(s, 2, 3);
        s = ordinaCoppia(s, 0, 2);
        s = ordinaCoppia(s, 1, 3);
        s = ordinaCoppia(s, 1, 2);
        if(!crescente) {
            return convertToLong(reverse(s));
        }
        return convertToLong(s);
    }

    /**
     * Pre-condizioni: n positivo
     * Post-condizioni: restituisce il numero successivo della "sequenza di Kaprekar"
     */
    public static long kaprekarStep(long n) {
        return ordinaCifre(n, false) - ordinaCifre(n, true);
    }

    /**
     * Pre-condizioni: n e' di quattro cifre in base 10
     * Post-condizioni: stampa la "sequenza di Kaprekar"
     */
    public static void kaprekarSequence(long n) {
        while (n != 6174) {
            System.out.println(n);
            n = kaprekarStep(n);
        }
        System.out.println(n);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        kaprekarSequence(s.nextLong());
        s.close();
    }
}