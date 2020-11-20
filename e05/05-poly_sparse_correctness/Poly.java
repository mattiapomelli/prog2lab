import java.util.ArrayList;
import java.util.List;

public class Poly {

    // CAMPI
    /** Array contenente i coefficienti del polinomio */
    private List<Term> terms;

    /**
     * Funzione di Astrazione: AF(terms) = somma di ci*x^ei   dove ci = terms.get(i).coeff  ei = terms.get(i).exp 
     *                                                        per 0 <= i < terms.size()
     * 
     * Invariante di Rappresentazione: terms.get(i).exp >= 0    per 0 <= i < terms.size() 
     * 
     * Invariante di Astrazione: il grado di un polinomio e' maggiore o uguale a zero
     * 
     */

    //COSTRUTTORI
    /**
     * Post-condizioni: inizializza this affinche' rappresenti il polinomio zero
     * 
     * Preservation of RI: inizializza terms con una lista vuota. Siccome la lista non ha elementi
     *                     non ci sono elementi con exp < 0 pertanto RI e' preservata
     */
    public Poly() {
        terms = new ArrayList<Term>();
        assert repOK();
    }

    /**
     * Post-condizioni: inizializza this affinche rappresenti il polinomio c*x^e
     *                  solleva un'eccezione se e < 0
     * 
     * Preservation of RI: se e < 0 this non viene inizializzato
     *                     altrimenti se e >= 0 viene aggiunto un nuovo a termine this con exp = e, quindi exp >= 0
     *                      
     */
    public Poly(int c, int e) {
        if ( e < 0) throw new NegativeExponentException("Esponente negativo");
        terms = new ArrayList<Term>();
        terms.add(new Term(c, e));
        assert repOK();
    }

    /**
     * post-condizioni: restituisce il polinomio this + q
     *                  solleva un'eccezione di tipo NullPointerException se q e' null
     * 
     * Preservation of RI: prima della chiamata ogni elemento di terms e di q.terms ha exp >= 0
     *                      ogni elemento aggiunto a sum.terms viene inizializzato con exp uguale ad
     *                      exp di un elemento di terms o q.terms, pertanto >= 0
     */
    public Poly add(Poly q) {
        if (q == null) throw new NullPointerException();

        Poly sum = new Poly();
        for (int i = 0; i < terms.size(); i++) {
            sum.terms.add( new Term(terms.get(i).coeff, terms.get(i).exp ) );
        }
        for (int i = 0; i < q.terms.size(); i++) {
            Term term = q.terms.get(i);
            if (sum.coeff(term.exp) == 0)
                sum.terms.add( new Term(term.coeff, term.exp ) );
            else {
                Term currentTerm = sum.terms.get(i);
                sum.terms.set(i, new Term(term.coeff + currentTerm.coeff, currentTerm.exp));
            }
        }
        assert repOK();
        return sum;
    }

    /**
     * post-condizioni: restituisce il polinomio -this
     * 
     * Preservation of RI: prima della chiamata ogni elemento di terms exp >= 0
     *                      ogni elemento aggiunto ad r viene inizializzato con exp uguale ad
     *                      exp di un elemento di terms, pertanto >=  0
     */
    public Poly minus() {
        Poly r = new Poly();
        for (int i = 0; i < terms.size(); i++) {
            Term term = terms.get(i);
            r.terms.add( new Term(-term.coeff, term.exp));
        }
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
     * 
     * Preservation of RI: prima della chiamata ogni elemento di terms e di q.terms ha exp >= 0
     *                      ogni elemento aggiunto a prod.terms viene inizializzato con exp uguale 
     *                      alla somma degli exp di elementi di terms o prod.terms, pertanto >= 0
     * 
     *                      terms.get(i).exp >= 0 0  per i=0,..terms.size()
     *                   && q.terms.get(j).exp >= 0 0  per j=0,..q.terms.size()
     *    -> terms.get(i).exp + q.terms.get(j) >= 0   per per i=0,..terms.size() e j=0,..q.terms.size()
     */
    public Poly mul(Poly q) {
        if (q == null) throw new NullPointerException();
        Poly prod = new Poly();

        for (int i = 0; i < terms.size(); i++) {
            for(int j = 0; j < q.terms.size(); j++) {
                Term term1 = terms.get(i);
                Term term2 = q.terms.get(j);
                int exp = term1.exp + term2.exp;
                if (prod.coeff(exp) == 0)
                    prod.terms.add( new Term(term1.coeff * term2.coeff, exp ) );
                else {
                    Term currentTerm = prod.terms.get(i + j);
                    prod.terms.set(i + j, new Term(term1.coeff * term2.coeff + currentTerm.coeff, exp));
                }
            }
        }

        assert repOK();
        return prod;
    }

    /**
     * post-condizioni: restituisce il termine corrispondente alla variabile con grado d
     */
    public int coeff(int d) {
        for (int i = 0; i < terms.size(); i++) {
            Term term = terms.get(i);
            if (term.exp == d) return term.coeff;
        }
        return 0;
    }

    public int degree() {
        int max = 0;
        for (int i = 0; i < terms.size(); i++) {
            Term term = terms.get(i);
            if (term.exp > max && term.coeff != 0) max = term.exp;
        }
        return max;
    }

    /** Post-condizioni: restituisce true se l'invariante di rappresentazione e' valida */
    private boolean repOK() {
        for (int i = 0; i < terms.size(); i++) {
            if(terms.get(i).exp < 0) return false;  
        }
        return true;
    } 

    @Override 
    public String toString() {
        String r = "";
        int i;
        for (i = 0; i < terms.size() - 1; i++) {
            r += terms.get(i).coeff + "x^" + terms.get(i).exp + " + ";  
        }
        r += terms.get(i).coeff + "x^" + terms.get(i).exp;
        return r;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Poly)) return false;
        Poly other = (Poly) obj;
        
        if(degree() != other.degree()) return false;
        for(int i = 0; i < terms.size(); i++) {
            int deg = terms.get(i).exp;
            if(coeff(deg) != other.coeff(deg)) return false;
        }

        return true;
    }
}