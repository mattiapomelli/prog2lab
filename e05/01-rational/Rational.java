import java.util.Objects;

/**
 * OVERVIEW: Le istanze di questa classe rappresentano i numeri razionali
 *           Gli oggetti di questa classe non sono mutabili
 *           Un numero razionale tipico e' n / d in cui n e d sono due numeri interi, e d e' diverso da zero
 * 
 * Funzione di Astrazione: AF(n, d) = n / d
 * 
 * Invariante di Rappresentazione: den > 0
 * 
 * Invariante di Astrazione: denominatore != 0
 */

public class Rational {
    
    //CAMPI
    // intero che rappresenta il numeratore
    private int num;
    // intero che rappresenta il denominatore
    private int den;


    // COSTRUTTTORI 
    /**
     * Post-condizioni: Inizializza this affinche rappresenti il numero razionale n / d
     *                  Solleva un'eccezione di tipo ArithmeticException se d = 0
     * 
     * Preservation of RI: Se d == 0 viene sollevata un'eccezione, quindi this non viene inizializzato
     *                     altrimenti den viene inizializzato con il valore assoluto di d
     *                     e d == 0 -> |d| > 0 -> den > 0
     * 
     * Correctness: AF(num, den) = num / den
     *                           = |num| / |den|     se num e den hanno lo stesso segno
     *                           = -|num| / |den|    se num e den hanno segni diversi      
     * 
     */
    public Rational(int n, int d) {
        if (d == 0) throw new ArithmeticException("Il denominatore deve essere diverso da 0");

        num = n * d > 0 ? Math.abs(num) : - Math.abs(num);
        den = Math.abs(d);

        reduce();

        assert repOK();
    }

    /**
     * Pre-condizioni: this.den != 0
     * Effetti Collaterali: potrebbe modificare this se non e' ai minimi termini
     * Post-condizioni: modifica this affinche' rappresenti il numero razionale ai minimi termini
     * 
     * Preservation of RI: si assume che RI sia preservata al momento della chiamata, quindi den > 0
     *                     L'mcd tra due numeri non negativi e' positivo, pertanto
     *                     den > 0 -> mcd(Math.abs(num), den) > 0 -> div > 0
     *                     den > 0 && div > 0  ->  den / div > 0
     *                     pertanto dopo la chiamata si ha den > 0
     * 
     * Correctness: AF(num/den) = num/den
     *                          = (num/d) / (den/d)    con d = mcd(|num|, |den|)
     *                     
     */
    private void reduce() {

        int div = mcd(Math.abs(num), den);
        num = num / div;
        den = den / div;

        assert repOK();
    }

    // METODI
    /**
     * Post-condizioni: restituisce il numero razionale this + other
     *                  solleva un'eccezione di tipo NullPointerException se b e' null
     * 
     * Preservation of RI: den > 0 && b.den > 0  ->  den * b.den > 0
     * 
     * Correctness: n/d + n2/d2 = ( n*d2 + n2*d) / (d* d2)
     */
    public Rational sum(Rational b) {
        Objects.requireNonNull(b);
        return new Rational(num * b.den + b.num * den, den * b.den);
    }

    /**
     * Post-condizioni: restituisce il numero razionale this - other
     *                  solleva un'eccezione di tipo NullPointerException se b e' null
     * 
     * Preservation of RI: Si assume che minus e sum preservino RI
     *                     quindi b.den  > 0 -> b.minus().den > 0
     *                     e b.den > 0 && b.minus().den > 0   ->  sum(b.minus()).den > 0
     * 
     * Correctness: n/d - n2/d2 = n/d + (-n2/d2) 
     */
    public Rational sub(Rational b) {
        Objects.requireNonNull(b);
        return sum(b.minus());
    }

    /**
     * Post-condizioni: restituisce il numero razionale -this
     * 
     * Preservation of RI: den > 0 per ipotesi induttiva. quindi anche il nuovo razionale inizializzato avra' den > 0
     *                     siccome viene inizializzato con lo stesso denominatore di this
     * 
     * Correctness: -(n/d) = -n/d 
     */
    public Rational minus() {
        return new Rational(-num, den);
    }

    /**
     * Post-condizioni: restituisce il numero razionale this * other
     *                  solleva un'eccezione di tipo NullPointerException se b e' null
     * 
     * Preservation of RI: den > 0 && b.den > 0  ->  den * b.den > 0
     * 
     * Correctness: n/d * n2/d2 = (n*n2) / (d*d2)
     */
    public Rational mul(Rational b) {
        Objects.requireNonNull(b);
        return new Rational(num * b.num, den * b.den);
    }

    /**
     * Post-condizioni: restituisce il numero razionale this / other
     *                  solleva un'eccezione di tipo NullPointerException se b e' null
     *                  solleva un'eccezione di tipo ArithmeticException se b e' 0
     * 
     * Preservation of RI: Si assume che minus e sum preservino RI
     *                     quindi b.den  > 0 -> b.reciprocal().den > 0
     *                     e b.den > 0 && b.reciprocal > 0    ->  mul(b.reciprocal).den > 0
     * 
     * Correctness: (n/d) / (n2/d2) = n/d * d2/n2
     */
    public Rational div(Rational b) {
        Objects.requireNonNull(b);
        return mul(b.reciprocal());
    }


    /**
     * Post-condizioni: se num = 0 solleva un eccezione di tipo IllegalArgumentException
     *                  altrimenti restituisce il numero razionale 1 / this
     * 
     * Preservation of RI: se num = 0 viene sollevata un'eccezione e non viene inizializzato un nuovo rational
     *                    
     * 
     * Correctness: 1 / (n / d) = d / n
     */
    public Rational reciprocal() {
        //if(num == 0) return new Rational(num, den);
        return new Rational(den, num);
    }

    /**
     * Post-condizioni: restituisce il Massimo Comune Divisore tra a e b
     *                  solleva un'eccezione se a o b < 0
     */
    private static int mcd(int a, int b) {
        if( a < 0 || b < 0) throw new IllegalArgumentException("A and B must be greater than 0");
        if (b == 0) return a;
        else return mcd(b, a % b);
    }

    /**
     * Post-condizioni: implementa la Funzione di Astrazione
     */
    @Override
    public String toString() {
        //if (den == 1) return "" + num;
        return num + "/" + den;
    }

    /**
     * Post-condizioni: restituisce true se l'Invariante di rappresentazione e' valida, false altrimenti
     */
    private boolean repOK() {
        return den > 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rational)) return false;
        Rational b = (Rational) obj;

        return num == b.num && den == b.den;
    }

    @Override
    public int hashCode() {
        return 31 * Integer.hashCode(num) + Integer.hashCode(den);
    }
}