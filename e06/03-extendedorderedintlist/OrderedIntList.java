import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Overview: Le istanze di questa classe rappresentano liste ordinate di interi.
 *           Gli oggetti di questo tipo sono mutabili
 *           Una lista ordinata tipica e' [x0, x1, ..., xk] dove xi-1 < xi per i = 1,.., k   
 *           
 */
public class OrderedIntList {

    private boolean empty;
    protected OrderedIntList left, right;
    private int value;

    /**
     *  Funzione di Astrazione: AF(this) = []                                               se this.empty
     *                                   = AF(this.left) + [this.value] + AF(this.right)    altrimenti
     * 
     * Invariante di Rappresentazione:  empty || (
     *                                      left != null && right != null &&
     *                                      RI(left) && RI(right) &&
     *                                      ( !left.empty => left.greatest < value ) &&
     *                                      ( !right.emmpy => value < right.greatest ) 
     * 
     * Invariante di Astrazione: ogni elemento della lista e' minore dei successivi
     */
    
    /**
     * Post-condizioni: inizializza this affinche rappresenti una lista ordinata vuota
     */
    public OrderedIntList() {
        empty = true;
    }   

    /**
     * Effetti-collaterali: potrebbe modificare this
     * Post-condizioni: Se el e' presente in this solleva un'eccezione di tipo DuplicateException
     *                  altrimenti aggiunge el a this
     */
    public void addEl(int el) {
        if(empty) {
            left = new OrderedIntList();
            right = new OrderedIntList();
            value = el;
            empty = false;
            return;
        }
        //if(el == value) throw new DuplicateException("L'elemento e' gia presente nella lista");
        if(el < value) 
            left.addEl(el);
        else
            right.addEl(el);
    } 
    
    /**
     * Effetti-collaterali: potrebbe modificare this
     * Post-condizioni: Se el non e' presente in this solleva un'eccezione di tipo NotFoundException
     *                  altrimenti rimuove el a this
     */
    public void remEl(int el) {
        if(isEmpty()) throw new NotFoundException();
        if(el == value) {
            try {
                value = right.least();
                right.remEl(value);
            } catch (EmptyException e) {
                empty = left.empty;
                value = left.value;
                right = left.right;
                left = left.left;
                return; 
            }
        } else if (el < value) {
            left.remEl(el);
        } else {
            right.remEl(el);
        }
    } 
    
    /**
     * Post-condizioni: restituisce true se el e' presente in this, false altrimenti
     */
    public boolean isIn(int el) {
        if(isEmpty())
            return false;
        if(el == value)
            return true;
        else if( el < value)
            return left.isIn(el);
        else
            return right.isIn(el);
    }   

    /** 
     * Post-condizioni: restituisce true se this e' vuota, false altrimenti
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * Post-condizioni: se this e' vuota solleva un eccezione di tipo EmptyException
     *                  Altrimenti restituisce l'elemento minore in this
     */
    public int least() {
        if(isEmpty()) throw new EmptyException();
        try {
            return left.least();
        } catch (EmptyException e) {
            return value;
        }
    }

    /**
     * Post-condizioni: implementa la funzione di astrazione
     */
    @Override
    public String toString() {
        if(empty) return "";
        return left.toString() + value + " - " + right.toString();
    }

    public boolean repOk() {
        return true;
    }

    /**
     * Post-condizioni: restituisce un generatore che produce gli elementi di this dal piu piccolo al piu grande
     */
    public Iterator<Integer> smallToBig() {
        return new Iterator<>() {

            private boolean used = false;
            private Integer current = null;
            private final Iterator<Integer> leftIterator = empty ? null : left.smallToBig();
            private final Iterator<Integer> rightIterator = empty ? null : right.smallToBig();

            @Override
            public boolean hasNext() {
                if (empty) return false;
                if (current != null) return true;
                if (leftIterator.hasNext()) {
                    current = leftIterator.next();
                    return true;
                }
                if (used == false) {
                    current = value;
                    used = true;
                    return true;
                }
                if (rightIterator.hasNext()) {
                    current = rightIterator.next();
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