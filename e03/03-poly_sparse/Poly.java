import java.util.ArrayList;
import java.util.List;

public class Poly {

    // CAMPI
    /** Array contenente i coefficienti del polinomio */
    private List<Term> terms;

    //COSTRUTTORI
    /**
     * Post-condizioni: inizializza this affinche' rappresenti il polinomio zero
     */
    public Poly() {
        terms = new ArrayList<Term>();
    }

    public Poly(int c, int e) {
        terms = new ArrayList<Term>();
        terms.add(new Term(c, e));
    }

    /**
     * post-condizioni: restituisce il polinomio this + q
     *                  solleva un'eccezione di tipo NullPointerException se q e' null
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
        return sum;
    }

    /**
     * post-condizioni: restituisce il polinomio -this
     */
    public Poly minus() {
        Poly r = new Poly();
        for (int i = 0; i < terms.size(); i++) {
            Term term = terms.get(i);
            r.terms.add( new Term(-term.coeff, term.exp));
        }

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
}