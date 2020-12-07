import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Overview: Le istanze di questa classe rappresentano insiemi limitati di oggetti
 *           Un tipico insieme e' { o1, o2, o3, .. on } in cui o1, ..on sono oggetti
 *           dello stesso tipo e diversi tra loro
 * 
 *          Gli oggetti di questo tipo sono mutabili          
 */
public class BoundedSet<T> implements Set<T> {

    // struttura dati che contiene gli elementi di this
    private final T[] elements;
    // indice che contiene la posizione in cui inserire il prossimo elemento
    private int next;

    /*
     *  Funzione di Astrazione: AF(elements, next) = { elements[i] | 0 <= i < next }  
     * 
     *  Invariante di Rappresentazione: size() <= elements.length
     *                                  elements non contiene elementi ripetuti
     * 
     *  Invariante di Astrazione: gli elementi all'interno di un insieme sono unici
     *
     */

    /**
     * Inizializza this affinche rappresenti un insieme di capacita' maxSize
     * 
     * @param maxSize il numero massimo di elementi che l'insieme puo contenere
     */
    @SuppressWarnings("unchecked")
    public BoundedSet(int maxSize) {
        elements = (T[]) new Object[maxSize];
        next = 0;
        assert repOk();
    }

    @Override
    public int size() {
        return next;
    }

    @Override
    public boolean isEmpty() {
        return next == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < next; i++)
            if (elements[i] == o)
                return true;
        return false;
    }

    @Override
    public T choose() {
        if (isEmpty())
            throw new EmptyException("Impossibile scegliere un elemento, l'insieme e' vuoto");
        return elements[0];
    }

    /**
     * Preservazione di RI: se l'insieme ha raggiunto la capacita' massima viene sollevata un eccezione, 
     *                      e l'elemento non e' inserito in this, pertanto size() non e' modificata
     *                      Altrimenti, l'elemento viene inserito solo se non e' gia presente in this,
     *                      pertanto non si creano elementi duplicati
     * 
     * Correttezza: si assume AF(elements, next) = { elements[i] | 0 <= i < next } prima della chiamata
     *              se e non e' presente in this l'insieme sara' { elements[i] | 0 <= i < next } U {e}
     *              dopo l'esecuzione del metodo, altrimenti this rimane invariato
     * 
     * Preservaione di AI: se e e' presente nell'insieme, non viene aggiunto. pertanto non si creano duplicati
     */
    @Override
    public boolean add(T e) {
        if (next == elements.length)
            throw new FullException();
        if (contains(e))
            return false;
        elements[next++] = e;

        assert repOk();
        return true;
    }

    /**
     * Preservazione di RI: se o non e' presente in this, this non viene modificato 
     *                      Altrimenti l'elemento viene rimosso, e pertanto size diminuisce di 1.
     *                      si assume size() < elements.length prima della chiamata, il che implica
     *                      size() - 1 < elements.length
     * 
     * Correttezza: si assume AF(elements, next) = { elements[i] | 0 <= i < next } prima della chiamata
     *              se o non e' presente in this l'insieme rimane invariato
     *              Altrimenti l'insieme sara' { elements[i] | 0 <= i < next } - {o} dopo l' invocazione del metodo
     * 
     * Preservaione di AI: non vengono aggiunti elementi all'insieme, pertanto non vengono introdotti duplicati
     */
    @Override
    public boolean remove(Object o) {
        if (!contains(o))
            return false;
        for (int i = 0; i < next; i++)
            if (elements[i] == o)
                elements[i] = elements[--next];
        
        assert repOk();
        return true;
    }

    /**
     * @return un generatore che produce gli elementi di this
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            // RI: i <= next
            private int i = 0;

			@Override
			public boolean hasNext() {
				return i < next;
			}

			@Override
			public T next() {
                if(!hasNext()) throw new NoSuchElementException();
                return elements[i++];
			}  
        };
    }

    @Override
    public String toString() {
        String r = "[";
        if(!isEmpty()) {
            int i;
            for (i = 0; i < next-1; i++)
                r += elements[i] + ", ";
            r += elements[i];
        }
        return r + "]";
    }

    public boolean repOk() {
        if(size() > elements.length) return false;
        for(int i = 0; i < size(); i++)
            for(int j = i; j < size(); j++)
                if(elements[i] == elements[j]) return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof BoundedSet<?>)) return false;
        BoundedSet<?> other = (BoundedSet<?>) o;
        
        if(size() != other.size()) return false;
        
        for(T el : this)
            if(!other.contains(el)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(size());

        for(int i = 0; i < size(); i++) {
            T el = elements[i];
            result = 31 * result + el.hashCode();
        }

        return result;
    }
}