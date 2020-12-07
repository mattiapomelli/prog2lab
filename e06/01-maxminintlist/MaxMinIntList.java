import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MaxMinIntList extends IntList {

    /**
     * Funzione di Astrazione: AF(elements, size) = [ elements.get(i) | 0 <= i <= elements.size() ]
     * 
     * Invariante di rappresentazione: !isEmpty() ->  smallest appartiene ad elements && per ogni x in elements smallest <= x
     *                                 !isEmpty() ->  biggest appartiene ad elements && per ogni x in elements biggest >= x
     */

    // CAMPI
    // struttura dati che contiene gli elementi di this
    private final List<Integer> elements;
    // rappresenta l'elemento maggiore della lista, se non e' vuota
    private int biggest;
    // rappresenta l'elemento minore della lista, se non e' vuota
    private int smallest;

    /**
     * Post-condizioni: inizializza this affinche rappresenti una lista vuota
     */
    public MaxMinIntList() {
        elements = new ArrayList<>();
        assert repOk();
    }

    /**
     * Post-condizioni: restituisce true se la lista e' vuota, false altrimenti
     */
    private boolean isEmpty() {
        return elements.size() == 0;
    }

    @Override
    public Integer first() {
        if(isEmpty()) throw new EmptyException();
        return elements.get(0);
    }

    @Override
    public Iterator<Integer> elements() {
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
    public void addEl(int x) {
        if(isEmpty() || x > biggest) biggest = x;
        if (isEmpty() || x < smallest) smallest = x;
        elements.add(x);

        assert repOk();
    }

    /**
     * Post-condizioni: se this e' vuoto solleva un'eccezione di tipo EmptyException
     *                  altrimenti restituisce l'elemento piu piccolo di this
     */
    public int min() {
        if (isEmpty()) throw new EmptyException();
        return smallest;
    };

    /**
     * Post-condizioni: se this e' vuoto solleva un'eccezione di tipo EmptyException
     *                  altrimenti restituisce l'elemento piu grande di this
     */
    public int max() {
        if (isEmpty()) throw new EmptyException();
        return biggest;
    };

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean repOk() {
        if (isEmpty()) return true;

        boolean foundSmall = false;
        boolean foundBig = false;
        Iterator<Integer> it = elements();

        while (it.hasNext()) {
            int z = ((Integer) it.next( )).intValue( );
            if (z < smallest || z > biggest) return false;
            if (z == smallest) foundSmall = true;
            if (z == biggest) foundBig = true;
        }

        return foundSmall && foundBig;
    }
    
}