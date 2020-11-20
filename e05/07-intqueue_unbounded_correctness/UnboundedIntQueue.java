import java.util.LinkedList;
import java.util.List;

/**
 * OVERVIEW: Le istanze di questa classe rappresentano code di interi di dimensione non limitata
 *           Gli oggetti di questo tipo sono mutabili
 *           Una coda tipica e' [x1, x2, ..., xk], con k arbitrariamente grande
 *           e dove x1 rappresenta testa della coda, mentre xk rappresenta l'ultimo elemento
 *           Una coda e' una struttura dati FIFO, quindi
 *           a seguito di una enqueue dell'elemento y, si otterra' [x1, x2, .., xk, y]
 *           a seguito di una dequeue, si otterra' [x2, ..., xk].
 */
public class UnboundedIntQueue {

    // CAMPI
    /** Struttura dati che contiene gli elementi della UnboundedIntQueue this */
    private final LinkedList<Integer> elements;

    /**
     * Funzione di astrazione: AF(elements) = [ elements.get(i) | 0 <= i < elements.size()]
     * 
     * Invariante di rappresentazione: elements != null, elements.size() >= 0
     * 
     * Invarianti di Astrazione: la coda ha una dimensione maggiore o uguale a 0
     */

    // COSTRUTTORI
    /**
     * Post-condizioni: inizializza this affinche' rappresenti una coda vuota
     * 
     * Preservation of RI: la coda viene inizializzata vuota, con dimensione 0, RI e' pertanto preservata
     * 
     * Correctness: AF mappa gli elementi di elements da posizione 0 a elements.size() agli elementi della coda
     *              elements viene inizializzato con size == 0, e viene quindi vengono mappati a una coda vuota
     *              AF(elements) = [ elements.get(i) | 0 <= i < elements.size()]
     *              elements.size() = 0 -> AF(elements) = {}
     */
    public UnboundedIntQueue() {
        elements = new LinkedList<>();
    }

    //METODI
    /**
     * Effetti-collaterali: modifica this
     * Post-condizioni: aggiunge l'elemento n alla coda, this = [x1, x2, ..., xk], this_post = [x1, x2, .., xk, n]
     * 
     * Preservation of RI: al momento della chiamata si assume elements.size >= 0
     *                     dopo la chiamata elements.size() aumenta di 1 e quindi e' > 0
     * 
     * Correctness:
     */
    public void enqueue(int n) {
        elements.add(n);

        assert repOK();
    }

    //METODI
    /**
     * Effetti-collaterali: potrebbe modificare this
     * Post-condizioni:  solleva un eccezinoe di tipo EmptyException se la coda e' vuota
     *                   Altrimenti rimuove il primo elemento dalla coda,
     *                   this = [x1, x2, ..., xk], this_post = [x2, .., xk]
     * 
     * Preservation of RI: se la coda e' vuota, e quindi elements.size() == 0, viene sollevata un'eccezione
     *                     altrimenti elements.size() e' > 0, e siccome dopo la chiamata diminuisce di 1, 
     *                     si avra' elements.size >= 0
     */
    public int dequeue() {
        if(elements.isEmpty()) throw new EmptyException("Coda vuota, impossibile estrarre elemento");
        int removed = elements.remove();

        assert repOK();
        return removed;
    }

    /**
     * Post-condizioni: restituisce il numero di elementi nella coda
     */
    public int size() {
        return elements.size();
    }

    @Override
    /**
     * Post-condizioni: implementa la funzione di astrazione
     */
    public String toString() {

        String r = "IntQueue : [";

        if(!elements.isEmpty()) {
            int i;
            for (i = 0; i < size() - 1; i++)
                r += elements.get(i) + ", ";
            r += elements.get(i);
        }

        return r + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UnboundedIntQueue)) return false;
        UnboundedIntQueue other = (UnboundedIntQueue) obj;

        if (elements.size() != other.size()) return false;
        for (int i = 0; i < size(); i++) 
            if(elements.get(i) != other.elements.get(i)) return false; 
        
        return true;
    }

    /**
     * restituisce true se l'invariante di rappresentazione e' verificata
     */
    private boolean repOK() {
        return elements != null && size() >= 0;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(elements.size());
        for (int i = 0; i < size(); i++) {
            result += 31 * elements.get(i).hashCode();
        }
        return result;
    }
    
}