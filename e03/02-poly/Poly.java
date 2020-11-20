// OVERVIEW: le istanze di questa classe rappresentano polinomi in una sola variabile a coefficienti lineari
//          gli oggetti di questo tipo non sono mutabili

public class Poly {

    // CAMPI
    /** Array contenente i coefficienti del polinomio */
    private final int[] terms;
    /** Grado del polinomio */
    private int degree;


    /**
     * Funzione di Astrazione: AF(terms, degree) = c0 x^0 + c1 x^1 + ... + cdegree x^degree    dove ci = terms[i]
     *                          ad esempio per terms = [1, 3, 2] e degree = 2  AF(terms, degree) = 1x^0 + 3x^1 + 2x^2
     * 
     * Invariante di rappresentazione: degree e' maggiore o uguale a 0, terms != null, 
     *                                 terms contiene un numero di elementi pari a degree
     */

    //COSTRUTTORI
    /**
     * Post-condizioni: inizializza this affinche' rappresenti il polinomio zero
     */
    public Poly() {
        terms = new int[1];
        degree = 0;
    }

    /**
     * Post-condizioni: inizializza this affinche' rappresenti il monomio c*x^n
     *                  solleva un'eccezione di tipo NegativeExponentException se n e' negativo
     */
    public Poly(int c, int n) {
        if ( n < 0) throw new NegativeExponentException("Esponente negativo");
        if ( c == 0 ) degree = 0;
        else degree = n;
        terms = new int[n + 1];
        terms[degree] = c;

        assert repOK();
    }

    /**
     * post-condizioni: restituisce il polinomio this + q
     *                  solleva un'eccezione di tipo NullPointerException se q e' null
     */
    public Poly add(Poly q) {
        if (q == null) throw new NullPointerException();

        int maxDegree = degree > q.degree ? degree : q.degree;
        Poly sum = new Poly(0, maxDegree);
        
        for (int i = 0; i <= degree; i++) 
            sum.terms[i] += terms[i];
        for (int i = 0; i <= q.degree; i++) 
            sum.terms[i] += q.terms[i];

        for (int i = maxDegree; i >= 0; i--) 
            if(sum.terms[i] != 0) { sum.degree = i; break; }

        assert repOK();
        return sum;
    }

    /**
     * post-condizioni: restituisce il polinomio -this
     */
    public Poly minus() {
        Poly r = new Poly( - terms[degree], degree);
        for (int i = 0; i < degree; i++)
            r.terms[i] = - terms[i];
        
        assert repOK();
        return r;
    }

    /**
     * post-condizioni: restituisce il polinomio this - q
     *                  solleva un'eccezione di tipo NullPointerException se q e' null
     */
    public Poly sub(Poly q) {
        if (q == null) throw new NullPointerException();
        return add(q.minus());
    }

    /**
     * post-condizioni: restituisce il polinomio this * q
     *                  solleva un'eccezione di tipo NullPointerException se q e' null
     */
    public Poly mul(Poly q) {
        if (q == null) throw new NullPointerException();

        int newDegree  = degree + q.degree;
        Poly prod = new Poly(0, newDegree);

        for (int i = 0; i <= degree; i++)
            for (int j = 0; j <= q.degree; j++)
                prod.terms[i+j] += terms[i] * q.terms[j];

        for (int i = newDegree; i >= 0; i--) 
            if(prod.terms[i] != 0) { prod.degree = i; break; }

        assert repOK();
        return prod;
    }

    /**
     * post-condizioni: restituisce il grado del polinomio this
     */
    public int degree() {
        return degree;
    }

    /**
     * post-condizioni: restituisce il termine corrispondente alla variabile con grado d
     */
    public int coeff(int d) {
        if ( d < 0 || d > degree) return 0;
        return terms[d];
    }
        
    @Override 
    public String toString() {
        assert repOK();
        
        String r = "";
        for (int i = 0; i < degree; i++) {
            r += coeff(i) + "x^" + i + " + ";  
        }
        r += coeff(degree) + "x^" + degree;
        return r;
    }

    /** Post-condizioni: restituisce true se l'invariante di rappresentazione e' valida */
    private boolean repOK() {
        return degree >= 0 && terms != null && terms.length-1 == degree;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Poly)) return false;
        Poly other = (Poly) obj;
        
        if(degree() != other.degree()) return false;
        for(int i = 0; i < degree(); i++)   
            if(terms[i] != other.terms[i]) return false;

        return true;
    }

}