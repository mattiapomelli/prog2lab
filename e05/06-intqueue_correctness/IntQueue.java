/**
 * OVERVIEW: Le istanze di questa classe rappresentano code di interi (limitate)
 *           Gli oggetti di questo tipo sono mutabili
 *           Una coda tipica e' [x1, x2, ..., xk], k minore o uguale dimensione massima
 *           Dato che e' una struttura dati FIFO
 *           a seguito di una enqueue dell'elemento y, si otterra' [x1, x2, .., xk, y]
 *           a seguito di una dequeue, si otterra' [x2, ..., xk].
 */
public class IntQueue {
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
     *     = [elements[head], elements[head+1], ..., elements[tail-1]]                                 se -1 < head <= tail
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
     * 
     * Preservation of RI: la coda viene inizializzata vuota, quindi con dimensione 0, che e' sicuramente
     *                      minore della dimensione massima, che e' pari ad n, che deve essere positivo
     *                      head viene inizializzato a -1, ed e' minore di elements.length, che e' positiva
     *                      tail viene inizializzato a 0, ed e' minore di elements.length, che e' positiva
     *                      RI e' quindi preservata.
     * 
     * Correctness: AF mappa gli elementi di elements da posizione head a tail agli elementi della coda
     *              siccome head e tail sono rispettivamente -1 e 0, questi valori vengono mappati a una coda vuota
     * 
     */
    public IntQueue(int n) {
        if (n < 0) throw new InvalidLengthException("La dimensione massima non puo essere negativa");
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
     * 
     * Preservation of RI: RI e' valida per this al momento della chiamata
     *                     Se la coda e' piena non viene aggiunto nessun elemento, quindi la coda continuera'
     *                     a non contenere piu elementi della sua capienza massima.
     * 
     *                      head viene modificato solo se la coda e' vuota e viene posto a 0, pertanto dopo enqueue e'
     *                      ancora >= -1 e minore di elements.length
     * 
     *                      il modulo per elements.length da un risultato compreso tra 0 e elements.length - 1
     *                      quindi tail dopo l'assegnamento avra' un valore compreso tra 0 e elements.length - 1
     *                      RI e' quindi preservata
     *              
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
     * 
     * Preservation of RI: RI e' valida per this al momento della chiamata
     *                     Non vengono aggiunti elementi, pertanto il numero di elementi continuera' ad
     *                      essere miinore della capienza massima anche dopo la chiamata.
     * 
     *                      tail viene modificato solo se la coda risulta vuota dopo la rimozione e viene posto a 0
     *                      pertanto dopo dequeue sara' ancora >= -1 e minore di elements.length
     * 
     *                      il modulo per elements.length da un risultato compreso tra 0 e elements.length - 1
     *                      quindi head dopo l'assegnamento avra' un valore compreso tra 0 e elements.length - 1, 
     *                      oppure -1 se la coda risulta vuota dopo la rimozione.
     *                      In ogni caso dopo la chiamata si avra' ancora -1 <= tail < elements.length
     *                      RI e' quindi preservata
     *              
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

    @Override
    /**
     * Post-condizioni: implementa la funzione di astrazione
     */
    public String toString() {
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

    @Override
    public int hashCode() {
        int result = Integer.hashCode(size());

        for (int i = 0; i < size(); i++) {
            result += 31 * Integer.hashCode(elements[(head + i) % elements.length]);
        }
        return result;
    }
}