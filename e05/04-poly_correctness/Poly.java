// OVERVIEW: le istanze di questa classe rappresentano polinomi in una sola variabile a coefficienti lineari
//          gli oggetti di questo tipo non sono mutabili

public class Poly {

    // CAMPI
    /** Array contenente i coefficienti del polinomio */
    private final int[] terms;
    /** Grado del polinomio */
    private final int degree;


    /**
     * Funzione di Astrazione: AF(terms, degree) = c0 x^0 + c1 x^1 + ... + cdegree x^degree    dove ci = terms[i]
     *                          ad esempio per terms = [1, 3, 2] e degree = 2  AF(terms, degree) = 1x^0 + 3x^1 + 2x^2
     * 
     * Invariante di rappresentazione: degree e' maggiore o uguale a 0, terms != null, 
     *                                 la lunghezza di terms - 1 e' pari a degree
     * 
     * Invarianti di Astrazione: il grado di un polinomio e' maggiore o uguale a zero
     *                           il numero di termini (diversi da zero) di un polinomio e' minore o uguale del suo grado + 1
     */

    //COSTRUTTORI
    /**
     * Post-condizioni: inizializza this affinche' rappresenti il polinomio zero
     * 
     * Preservation of RI: degree viene inizializato a 0 e pertanto e' >= 0 e rispetta RI
     *                     terms viene inizializzato con dimensione 1 e pertanto la sua lunghezza - 1
     *                     e' 0, che e' uguale a degree.
     *                     RI e' quindi preservata
     * 
     * Correctness: il costruttore inizializza terms ad un array vuoto di dimensione 1 e degree a 0.
     *              AF mappa terms ai coefficienti del polinomio fino a grado degree
     *              Terms e degree vengono quindi mappati al polinomio con coefficiente 0 per il termine
     *              di grado 0, che e' il polinomio zero
     * 
     * Preservation of AI: il costruttore inizializza il polinomio zero, il cui grado e' 0.
     *                      Il polinomio inizializzato inoltre non ha termini diversi da zero
     *                      Le AI sono quindi preservate
     */
    public Poly() {
        terms = new int[1];
        degree = 0;
    }

    /**
     * Post-condizioni: inizializza this affinche' rappresenti il monomio c*x^n
     *                  solleva un'eccezione di tipo NegativeExponentException se n e' negativo
     * 
     * Preservation of RI: se n e' minore di zero viene sollevata un'eccezione, altrimenti degree viene inizializzato ad n
     *                      e quindi degree sara >= 0, rispettando RI
     *                      terms viene inizializzato con dimensione degree + 1 pertanto la sua lunghezza - 1 sara' pari a degree
     *                      RI e' pertanto preservata
     * 
     * Correctness:  AF mappa terms ai coefficienti del polinomio fino a grado degree
     *              Dopo la chiamata terms e c in posizione degree, e 0 in tutte le altre posizioni.
     *              Viene quindi mappato al polinomio con coefficiente c per il termine di grado degree.
     *              Siccome degree e' uguale a n questo polinomio e' c*x^n.
     * 
     * Preservation of AI:  il costruttore inizializza il polinomio cx^n, il cui grado e' n, solo 
     *                      se n e' maggiore o uguale a 0. Pertanto il grado del polinomio inizializzato e'
     *                      >= 0 e rispetta AI.
     *                      Il polinomio inizializzato ha solo un termine diverso da zero, e siccome il grado e' >= 0
     *                      si ha che il numero di termini diversi da zero e' sicuramente minore o uguale al grado + 1.
     *                      Le AI sono quindi preservate
     */
    public Poly(int c, int n) {
        if ( n < 0) throw new NegativeExponentException("Esponente negativo");
        if ( c == 0 ) degree = 0;
        else degree = n;
        terms = new int[degree + 1];
        terms[degree] = c;

        assert repOK();
    }

    /**
     * Post-condizioni: inizializza un polinomio di grado n con tutti i coefficienti uguali a 0
     *                  solleva un'eccezione di tipo NegativeExponentException se n e' negativo
     * 
     * Preservation of RI: se n e' minore di zero viene sollevata un'eccezione, altrimenti degree viene inizializzato ad n
     *                      e quindi degree sara >= 0, rispettando RI
     *                      terms viene inizializzato con dimensione degree + 1 pertanto la sua lunghezza - 1 sara' pari a degree
     *                      RI e' pertanto preservata
     * 
     * Correctness: AF mappa terms ai coefficienti del polinomio fino a grado degree
     *              Dopo la chiamata terms contiene zeri in tutte le posizioni.
     *              Viene quindi mappato al polinomio i cui coefficienti sono tutti uguali a zero
     * 
     * Preservation of AI:  il costruttore inizializza un polinomio di grado 0.
     *                      Il polinomio inizializzato non ha termini diversi da zero
     *                      Le AI sono quindi preservate
     */
    private Poly(int n) {
        if ( n < 0) throw new NegativeExponentException("Esponente negativo");
        degree = n;
        terms = new int[degree + 1];
    }

    /**
     * post-condizioni: restituisce il polinomio this + q
     *                  solleva un'eccezione di tipo NullPointerException se q e' null
     * 
     * 
     */
    public Poly add(Poly q) {
        if (q == null) throw new NullPointerException();
        Poly max, min;
        if(degree > q.degree) {
            max = this;
            min = q;
        } else {
            max = q;
            min = this;
        }

        int newDegree = max.degree;
        if (degree == q.degree) {
            for(int j = degree; j > 0; j--)
                if(terms[j] + q.terms[j] != 0) break;
                else newDegree--;
        }

        Poly sum = new Poly(newDegree);
        int i;
        for(i = 0; i <= min.degree && i <= newDegree; i++)
            sum.terms[i] = min.terms[i] + max.terms[i];
        for(int j = i; j <= newDegree; j++) 
            sum.terms[j] = max.terms[j];

        assert repOK();
        return sum;
    }

    /**
     * post-condizioni: restituisce il polinomio -this
     */
    public Poly minus() {
        Poly r = new Poly(degree);
        for (int i = 0; i <= degree; i++)
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

        if((q.degree == 0 && q.terms[0] == 0) || (degree == 0 && terms[0] == 0)) return new Poly();

        Poly prod = new Poly(degree + q.degree);
        for (int i = 0; i <= degree; i++) 
            for(int j = 0; j <= q.degree; j++)
                prod.terms[i + j] += terms[i] * q.terms[j];

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

    @Override
    public int hashCode() {
        int result = Integer.hashCode(degree);
        for(int i = 0; i < degree(); i++) {
            result += 31 * Integer.hashCode(terms[i]);
        }
        return result;
    }
}