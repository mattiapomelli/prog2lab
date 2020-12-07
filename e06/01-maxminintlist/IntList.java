import java.util.Iterator;

/**
 * Overview: Le istanze di questa classe rappresentano liste di interi
 *           Una tipica lista di interi e' [x1, x2, ..., xn]
 * 
 */
public abstract class IntList {
   
    /**
     * Post-condizioni: restituisce il primo elemento di this, se this non e' vuota
     *                  altrimenti solleva un'eccezione ti tipo EmptyException
     */
    public abstract Integer first();

    /**
     * Post-condizioni: restituisce un generatore che produce gli elementi di this, nell'ordine in cui sono
     */
    public abstract Iterator<Integer> elements();

    /**
     * Post-condizioni: aggiunge l'elemento x all'inizio di this
     */
    public abstract void addEl(int x);

    /**
     * Post-condizioni: restituisce il numero di elementi presenti in this
     */
    public abstract int size();

    public abstract boolean repOk();

    public String toString() {
        return null;
    }

    public boolean equals(Object o) {
        return true;
    }
}