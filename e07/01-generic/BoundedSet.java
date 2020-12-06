public class BoundedSet<E> implements Set<E> {

    // struttura dati che contiene gli elementi di this
    private final E[] elements;
    // indice che contiene la posizione in cui inserire il prossimo elemento
    private int next;

    /**
     * Inizializza this affinche rappresenti un insieme di capacita' maxSize
     * @param maxSize il numero massimo di elementi che l'insieme puo contenere
     */
    public BoundedSet(int maxSize) {
        elements = (E[]) new Object[maxSize];
        next = 0;
    }
 
    @Override
    int size() {
        return next;
    }

    @Override
    boolean isEmpty() {
        return next == 0;
    }

    @Override
    boolean contains(Object o) {
        for(int i = 0; i < next; i++) 
            if(elements[i] == o) return true;
        return false;
    }

    @Override
    E choose() {
        if(isEmpty()) throw new EmptyException("Impossibile scegliere un elemento, l'insieme e' vuoto");
        return elements[0];
    }

    @Override
    boolean add(E e) {
        if(next == elements.length) throw new FullException();
        if(contains(e)) return false;
        elements[next++] = e;
        return true;
    }   

    @Override
    boolean remove(Object o) {
        if(!contains(o)) return false;
        for(int i = 0; i < next; i++)
            if(elements[i] == o) elements[i] = elements[next--];
        return true;
    }
}