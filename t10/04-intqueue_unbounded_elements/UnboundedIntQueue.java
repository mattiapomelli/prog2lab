import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * OVERVIEW: Le istanze di questa classe rappresentano code di interi di dimensione non limitata
 *           Gli oggetti di questo tipo sono mutabili
 *           Una coda tipica e' [x1, x2, ..., xk], con k arbitrariamente grande
 *           e dove x1 rappresenta testa della coda, mentre xk rappresenta l'ultimo elemento
 *           Una coda e' una struttura dati FIFO, quindi
 *           a seguito di una enqueue dell'elemento y, si otterra' [x1, x2, .., xk, y]
 *           a seguito di una dequeue, si otterra' [x2, ..., xk].
 */
public class UnboundedIntQueue implements Iterable<Integer> {

    // CAMPI
    /** Struttura dati che contiene gli elementi della UnboundedIntQueue this */
    private final LinkedList<Integer> elements;

    /**
     * Funzione di astrazione: AF(elements) = [ elements.get(i) | 0 <= i < elements.size()]
     * 
     * Invariante di rappresentazione: elements != null, la coda ha una dimensione maggiore o uguale a 0
     */

    // COSTRUTTORI
    /**
     * Post-condizioni: inizializza this affinche' rappresenti una coda vuota
     */
    public UnboundedIntQueue() {
        elements = new LinkedList<>();
    }

    //METODI
    /**
     * Effetti-collaterali: modifica this
     * Post-condizioni: aggiunge l'elemento n alla coda, this = [x1, x2, ..., xk], this_post = [x1, x2, .., xk, n]
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

    /**
     * Post-condizioni: restituisce un generatore che produce gli elementi di this, dal primo all'ultimo in ordine di inserimento
     */
    public Iterator<Integer> iterator() {
        return new Iterator<Integer> () {

            // RI: 0 <= i <= size()

            private int i = 0;

            public boolean hasNext() {
                return i < size();
            }

            public Integer next() {
                if(!hasNext()) throw new NoSuchElementException();
                return elements.get(i++);
            }
        };
    }

    @Override
    /**
     * Post-condizioni: implementa la funzione di astrazione
     */
    public String toString() {
        assert repOK();

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
    
}