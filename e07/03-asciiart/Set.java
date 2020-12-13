public interface Set<E> extends Iterable<E> {

    /**
     * @return la dimensione dell'insieme
     */
    int size();

    /**
     * @return true se l'insieme e' vuoto, false altrimenti
     */
    boolean isEmpty();

    /**
     * @return true se o e' presente nell'insieme, false altrimenti
     */
    boolean contains(Object o);

    /**
     * Restituisce uno degli elementi dell'insieme
     * @return un elemento dell'insieme
     * @throws EmptyExcpetion se l'insieme e' vuoto
     */
    E choose();

    /**
     * Inserisce l'elemento e nell'insieme, se non gia presente
     * @return true se l'elemento e' stato inserito, false altrimenti
     * @throws FullException se l'insieme e' pieno
     */
    boolean add(E e);

    /**
     * Rimuove l'elemento o dall'insieme se presente
     * @return true se l'elemento e' stato rimosso, false altrimenti
     */
    boolean remove(Object o);
}