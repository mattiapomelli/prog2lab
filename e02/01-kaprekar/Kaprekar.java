import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Kaprekar {

    /**
     * effetti collaterali: potrebbe modificare l'ordine degli elementi di a
     * post-condizioni: inverte l'ordine degli elementi di a
     *                  solleva un eccezione di tipo NullPointerException se a e' null
     */
    public static void reverse(byte[] a) {
        if (a == null) throw new NullPointerException();
        for (int i = 0; i < a.length/2; i ++) {
            byte tmp = a[i];
            a[i] = a[a.length -1 - i];
            a[a.length - 1 - i] = tmp;
        }
    }
    
    /**
     * post-condizioni: restituisce un array contenente le cifre di n
     *                  solleva un eccezione di tipo IllegalArgumentException se n ha piu di digits cifre
     */
    public static byte[] numToDigits(int n, int digits) {
        byte[] a = new byte[digits];
        for (int i = digits - 1; i >= 0; i--, n /= 10)
            a[i] = (byte)(n % 10);

        if (n > 0) throw new IllegalArgumentException("Il numero passato come argomento ha piu di " + digits + " cifre");

        return a;
    }

    /**
     * post-condizioni: converte il numero memorizzato (in cifre) in  in un intero
     *                  solleva un eccezione di tipo NullPointerException se a e' null
     *                  solleva un eccezione di tipo IllegalArgumentException se qualche elemento di a non e' una cifra decimale
     */
    public static int digitsToNum(byte[] a) {
        if (a == null) throw new NullPointerException();
        int n = 0;
        for (byte b : a) {
            if (b < 0 || b > 9) throw new IllegalArgumentException("Valori attesi per argomento compresi tra 0 e 9. Trovato: " + b);
            n = n * 10 + b;
        }

        return n;
    }
    
    /**
     * effetti collaterali: modifica System.out
     * Post-condizioni: stampa la "sequenza di kaprekar"
     *                  solleva un eccezione di tipo IllegalArgumentException se n non e' nell'intervallo [1,9999]
     *                  solleva un eccezione di tipo IllegalArgumentException se n non ha almeno due cifre diverse
     */
    public static void printKaprekar(int n) {
        if (n < 1 || n > 9999) throw new IllegalArgumentException("Il numero in input deve avere al piu 4 cifre e deve essere positivo");
        
        System.out.println(n);
        while (n != 6174) {
            n = stepKaprekar(n);
            if (n == 0) throw new IllegalArgumentException("Almeno due cifre del numero in input devono essere diverse");
            System.out.println(n);
        }
    }
    
    /**
     * Post-condizioni: esegue un passo della sequenza di Kaprekar
     *                   solleva un eccezione di tipo IllegalArgumentException se n non e' nell'intervallo [1,9999]
     */
    public static int stepKaprekar(int n) {
        if (n < 1 || n > 9999) throw new IllegalArgumentException("Il numero in input deve avere al piu 4 cifre e deve essere positivo");
        
        byte[] digits = numToDigits(n, 4);
        Arrays.sort(digits);
        n = -digitsToNum(digits);
        reverse(digits);
        return n + digitsToNum(digits);
    }
    
    public static void main(String[] args) {
        // InputStream is;
        // try {
        //     is = new URL("https://www.random.org/integers/?num=1&min=1&max=9999&col=1&base=10&format=plain&rnd=new").openStream();
        // }
        // catch (MalformedURLException e) {
        //     e.printStackTrace();
        //     System.out.println("URL non valido");
        //     is = System.in;
        // }
        // catch (IOException e) {
        //     e.printStackTrace();
        //     System.out.println("Impossibile contattare il server");
        //     is = System.in;
        // }
        Scanner s = new Scanner(System.in);

        printKaprekar(s.nextInt());
        s.close();
    }
}