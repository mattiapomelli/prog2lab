/**
 * Overview: Le istanze di questa classe rappresentano un singolo termine di un polinomio
 */
public class Term {
    int coeff;
    int exp;
    
    /**
     * Post-condizioni: inizializza this affinche' rappresenti il monomio c*x^e
     *                  solleva un'eccezione di tipo NegativeExponentException se e e' negativo
     */
    Term(int c, int e) {
        if ( e < 0) throw new NegativeExponentException("Esponente negativo");
        coeff = c;
        exp = e;
    }
}