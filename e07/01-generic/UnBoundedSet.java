import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Overview: Le istanze di questa classe rappresentano insiemi non limitati di oggetti
 *           Un tipico insieme e' { o1, o2, o3, .. on } in cui o1, ..on sono oggetti
 *           dello stesso tipo e diversi tra loro
 * 
 *          Gli oggetti di questo tipo sono mutabili          
 */
public class UnBoundedSet<T> implements Set<T> {

    // CAMPI
    /** Struttura dati contenente gli elementi dell'insieme */
    private List<T> elements;

    /**
     * Funzione di astrazione: AF(elements) = { elements.get(i) | 0 <= i <= elements.size() }
     * 
     * Invariante di Rappresentazione: elements non e' null, L'insieme non contiene duplicati, quindi
     *                                 per ogni i, j  compresi tra 0 e size() vale  i != j -> elements.get(i) != elements.get(j)
     */
    
    // COSTRUTTORI
    /**
     * Post condizioni: Inizializza un nuovo insieme di interi vuoto
     */
    public UnBoundedSet() {
        elements = new ArrayList<>();
        assert repOk();
    }

    /**
     * Preservazione di RI: Altrimenti, l'elemento viene inserito solo se non e' gia presente in this,
     *                      pertanto non si creano elementi duplicati
     * 
     * Correttezza: si assume AF(elements, next) = { elements[i] | 0 <= i < next } prima della chiamata
     *              se e non e' presente in this l'insieme sara' { elements[i] | 0 <= i < next } U {e}
     *              dopo l'esecuzione del metodo, altrimenti this rimane invariato
     * 
     * Preservaione di AI: se e e' presente nell'insieme, non viene aggiunto. pertanto non si creano duplicati
     */
    public boolean add(T x) {
        if (this.contains(x))
            return false;
        
        elements.add(x);
        assert repOk();
        return true;
    }

    /**
     * Post-condizioni: restituisce l'elemento di this in posizione i
     * Solleva un eccezione di tipo InvalidArgumentException se i < 0 o i >= size(0)
     */
    public T get(int i) {
        if( i < 0 || i >= size()) throw new IllegalArgumentException();
        return elements.get(i);
    }

    /**
     * Post-condizioni: aggiunge a this tutti gli elementi dell'insieme o
     */
    public void addAll(UnBoundedSet<T> o) {
        for( T el : o.elements)
            add(el);

        assert repOk();
    }

    /**
     * Preservazione di RI: se o non e' presente in this, this non viene modificato 
     *                      Altrimenti l'elemento viene rimosso, e pertanto size diminuisce di 1.
     *                      si assume size() < elements.length prima della chiamata, il che implica
     *                      size() - 1 < elements.length
     * 
     * Correttezza: si assume AF(elements, next) = { elements.get(i) | 0 <= i < next } prima della chiamata
     *              se o non e' presente in this l'insieme rimane invariato
     *              Altrimenti l'insieme sara' { elements.get(i) | 0 <= i < next } - {o} dopo l' invocazione del metodo
     * 
     * Preservaione di AI: non vengono aggiunti elementi all'insieme, pertanto non vengono introdotti duplicati
     */
    public boolean remove(Object x) {
        int index = elements.indexOf(x);                    // indice dell'elemento da rimuovere, -1 se non e' presente

        if (index == -1)
            return false;
        
        int lastIndex = elements.size() - 1;            // indice dell'ultimo elemento    
        elements.set(index, elements.get(lastIndex));   // scambio elemento da modificare con l'ultimo elemento
        elements.remove(lastIndex);                     // rimuovi elemento in ultima posizione

        assert repOk();
        return true;
    }

    /**
     * Post-condizioni: restituisce la cardinalita' dell'insieme this
     */
    public int size() {
        return elements.size();
    }
    
    /**
     * Post-condizioni: restituisce true se x e' in this, false altrimenti
     */
    public boolean contains(Object x) {
        return elements.contains(x);
    }

    @Override
    public T choose() {
        if (isEmpty())
            throw new EmptyException("Impossibile scegliere un elemento, l'insieme e' vuoto");
        return elements.get(0);
    }

    /**
     * Post-condizioni: restituisce un generatore che produce gli elementi di this
     */
    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    /**
     * Post-condizioni: implementa la funzione di astrazione
     */
    @Override                       
    public String toString() {
        assert repOk();

        String r = "IntSet: {";
        if (size() > 0) {
            for (int i = 0; i < size() - 1; i++)
                r += elements.get(i) + ", ";
            r += elements.get(elements.size() - 1);
        }
        return r + "}";
    }

    /**
     * Post-condizioni: restituisce true se l'invariante di rappresentazione e' verificata
     */
    public boolean repOk() {
        if(elements == null) return false;
        for (int i = 0; i < size(); i++)
            for (int j = i + 1; i < size(); j++)
                if(elements.get(i) == elements.get(j)) return false;

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UnBoundedSet<?>)) return false;
        UnBoundedSet<?> other = (UnBoundedSet<?>) obj;

        if(size() != other.size()) return false;

        for(int i = 0; i < size(); i++)
            if(!other.contains(elements.get(i))) return false;

        return true;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}