import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * OVERVIEW: Le istanze di questa classe rappresentano code di interi (limitate)
 *           Gli oggetti di questo tipo sono mutabili
 *           Una coda tipica e' [x1, x2, ..., xk], k minore o uguale dimensione massima
 *           Dato che e' una struttura dati FIFO
 *           a seguito di una enqueue dell'elemento y, si otterra' [x1, x2, .., xk, y]
 *           a seguito di una dequeue, si otterra' [x2, ..., xk].
 */
public class IntQueue implements Iterable<Integer> {
    // CAMPI
    /** Struttura dati che contiene gli elementi della IntQueue this */
    final private int[] elements;
    
    /**
     * Gli indici della testa e della coda della IntQueue
     * head indica il primo elemento di this (-1 se la coda e' vuota)
     * Tail indica l'indice della prima posizione disponibile (tail == head se la coda e' piena)
     */
    private int head, tail;

    /**
     * Funzione di Astrazione :AF(elements, head, tail) =
     *     = [elements[head], elements[head+1], ..., elements[tail-1]]                                 se -1 < head <= tail o [elements[head],
     *     = [elements[head+1], ..., elements[elements.size-1], elements[0], ..., elements[tail-1]]    se head > tail
     *                         
     * Invariante di rappresentazione: la coda non contiene piu elementi della sua capienza massima,
     *      -1 <= head < size, 0 <= tail < size, head == -1 -> tail = 0
     * 
     */
    // COSTRUTTORI
    /**
     * Pre-condizioni: n >= 0
     * Post-condizioni: Inizializza this affinche' rappresenti una coda di dimensione massima n;
     */
    public IntQueue(int n) {
        elements = new int[n];
        head = -1;
        tail = 0;

        assert repOK();
    }

    // METODI
    /**
     * Effetti collaterali: potrebbe modficare this
     * Post-condizioni: se la coda e' piena, solleva un'eccezione di tipo FullException, mentre
     *                  aggiunge n alla coda altrimenti
     *                  this = [x1, x2, ..., xk], this_post = [x1, x2, .., xk, n]
     */
    public void enqueue(int n) {
        if (isFull()) throw new FullException("Impossibile aggiungere elemento. Coda piena.");
        if (isEmpty()) head = 0;
        elements[tail] = n;
        tail = (tail + 1) % elements.length;
        assert repOK();
    }

    /**
     * Post-condizioni: restituisce true se la cosa e' piena, false altrimenti
     */
    public boolean isFull() {
        return head == tail;
    }

    /**
     * Post-condizioni: restituisce true se la cosa e' vuota, false altrimenti
     */
    public boolean isEmpty() {
        return head == -1;
    }
    
    /**
     * Effetti collaterali: potrebbe modficare this
     * Post-condizioni: se la coda non e' vuota restituisce l'elemento in testa e lo elimina da this
     *                  solleva un'eccezione empty exception se la coda e' vuota
     *                  this = [x1, x2, ..., xk], this_post = [x2, .., xk]
     */
    public int dequeue() {
        if(isEmpty()) throw new EmptyException("Impossibile estrarre elemento. Coda vuota");
        int r = elements[head];
        head = (head + 1) % elements.length;
        if (head == tail) {
            head = -1;
            tail = 0;
        }
        assert repOK();
        return r;
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
                return elements[(head + i++) % elements.length];
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

        if(!isEmpty()) {
            int i;
            for (i = 0; i < size() - 1; i++)
                r += elements[(head + i) % elements.length] + ", ";
            r += elements[(head + i) % elements.length];
        }

        return r + "]";
    }

    /**
     * Post-condizioni: restituisce il numero di elementi presenti nella coda
     */
    public int size() {
        if(isEmpty()) return 0;
        if(isFull()) return elements.length;
        return (tail - head + elements.length) % elements.length;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntQueue)) return false;
        IntQueue other = (IntQueue) obj;

        if (size() != other.size() || elements.length != other.elements.length) return false;
        for (int i = 0; i < size(); i++) {
            if (elements[(head + i) % elements.length]
                != other.elements[(other.head + i) % elements.length]) return false;
        }
        
        return true;
    }

    /**
     * restituisce true se l'invariante di rappresentazione e' verificata
     */
    private boolean repOK() {
        return size() < elements.length
            && elements != null
            && -1 <= head
            && head < elements.length
            && tail >= 0
            && tail < elements.length
            && (head != -1 || tail == 0);
    }
}