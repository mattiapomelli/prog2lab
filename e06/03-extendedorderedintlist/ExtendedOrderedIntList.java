import java.util.Iterator;

public class ExtendedOrderedIntList extends OrderedIntList {
    
    // COSTRUTTORI
    /**
     * Post-condizioni: inizializza this affinche rappresenti una lista vuota
     */
    public ExtendedOrderedIntList() {
        super();
    }

     /**
     * Post-condizioni: restituisce un generatore che produce gli elementi di this dal piu grande al piu piccolo
     */
    public Iterator<Integer> bigToSmall() {
        return new Iterator<>() {

            private boolean used = false;
            private Integer current = null;
            private final Iterator<Integer> leftIterator = isEmpty() ? null : left.bigToSmall();
            private final Iterator<Integer> rightIterator = isEmpty() ? null : right.bigToSmall();

            @Override
            public boolean hasNext() {
                if (isEmpty()) return false;
                if (current != null) return true;
                if (rightIterator.hasNext()) {
                    current = rightIterator.next();
                    return true;
                }
                if (used == false) {
                    current = value;
                    used = true;
                    return true;
                }
                if (leftIterator.hasNext()) {
                    current = leftIterator.next();
                    return true;
                }
                return false;
            }

            @Override
            public Integer next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    Integer ret = current;
                    current = null;
                    return ret;
            }
        };
    }
}