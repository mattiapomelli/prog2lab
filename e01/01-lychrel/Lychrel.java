import java.util.Scanner;

public class Lychrel {
    
    /**
    * Post-condizioni: restituisce una stringa che rappresenta n
    */
    public static String convertToString(long n) {
        return "" + n;
    }

    /**
    * Post-condizioni: restituisce una rappresentazione di s tramite un long
    *                   solleva un'eccezione di tipo NullPointerException se s e' null
    *                   solleva un'eccezione di tipo IllegalArgumentException se s non rappresenta un numero
    */
    public static long convertToLong(String s) {
        if (s == null) throw new NullPointerException();
        int len = s.length();
        long n = 0;
        for (int i = 0; i < len; i++) {
            int c = s.charAt(i);
            if (c < '0' || c > '9') throw new IllegalArgumentException("La stringa s deve rappresentare un numero");

            n *= 10;
            n += s.charAt(i) - '0';
        }
        return n;
    }


    /**
     * Post-condizioni: restituisce true se s e' una stringa palindroma, false altrimenti
     *                  solleva un eccezione di tipo NullPointerException se s e' null
     * 
     * S e' palindroma se:
     * S e' la stringa vuota
     * S e' composta da un solo caratter
     * S e' palindroma & a == a -> aSa palindroma
     */
    public static boolean isPalindrome(String s) {
        if (s == null) throw new NullPointerException();
        int len = s.length();
        if (len <= 1) return true;
        return s.charAt(0) == s.charAt(len - 1) && isPalindrome(s.substring(1, len - 1));  // il secondo e' escluso
    }
    
    /**
     * Post-condizioni: restituisce la stringa reverse di s
     *                  solleva un eccezione di tipo NullPointerException se s e' null
     */
    public static String reverse(String s) {
        if (s == null) throw new NullPointerException();
        int len = s.length();
        if (len <= 1) return s;
        return s.charAt(len - 1) + reverse(s.substring(1, len-1)) + s.charAt(0);
    }

    /**
     * Post-condizioni: restituisce il numero successivo della "sequenza di Lychrel"
     *                  solleva un eccezione di tipo IllegalArgumentException se n non e' positivo
     */
    public static long lychrelStep(long n) {
        if (n < 0) throw new IllegalArgumentException("Il numero in input deve essere positivo");
        return n += convertToLong(reverse(convertToString(n)));
    }

    /**
     * Pre-condizioni: n non e' un numero di Lychrel
     * Post-condizioni: stampa la "sequenza di Lychrel"
     */
    public static void lychrelSequence(long n) {
        while(!isPalindrome(convertToString(n))) {
            System.out.println(n);
            n = lychrelStep(n);
        }
        System.out.println(n);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        lychrelSequence(s.nextLong());
        s.close();
    }
}