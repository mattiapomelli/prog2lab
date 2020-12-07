import java.util.Iterator;

public class MaxMinIntSet extends MaxIntSet {

    /**
     * Funzione di Astrazione: AF_MaxMinIntSet(this) = AF_MaxIntSet(this)
     * 
     * Invariante di Rappresentazione: this.size > 0 ->  smallest apprtiene a this && per ogni x in this  smallest <= x
     */
    
    // CAMPI
    // rappresenta l'elemento maggiore, se l'insieme non e' vuoto
    private int smallest;

    // COSTRUTTORI
    /**
     * Post-condizioni: inizializza this affinche rappresenti un insieme vuoto
     */
    public MaxMinIntSet() {
        super();
    }


    /**
     * Effetti-collaterali: potrebbe modificare smallest
     * Post-condizioni: inserisce x nell'insieme e aggiorna smallest se x e' minore di esso o l'insieme e' vuoto
     */
    @Override
    public void insert(int x) {
        if (size() == 0 || x < smallest) smallest = x;
        super.insert(x);
    }

    /**
     * Effetti-collaterali: potrebbe modificare smallest
     * Post-condizioni: rimuove x dall'insieme e aggiorna smallest l'elemento rimosso era il minore
     */
    public void remove (int x) {
        super.remove(x);
        if (size() == 0 || x > smallest) return;
        Iterator it = iterator();
        smallest = ((Integer) it.next( )).intValue( );

        while (it.hasNext()) {
            int z = ((Integer) it.next( )).intValue( );
            if (z < smallest) smallest = z;
        }
    }

    /**
     * Post-condizioni: se this e' vuoto solleva un'eccezione di tipo EMptyException
     *                  altrimenti restituisce l'elemento piu piccolo di this
     */
    public int min() {
        if (size() == 0) throw new EmptyException();
        return smallest;
    };

    @Override
    public boolean repOk() {
        if (!super.repOk()) return false;
        if (size() == 0) return true;

        boolean found = false;
        Iterator<Integer> it = iterator();

        while (it.hasNext()) {
            int z = ((Integer) it.next( )).intValue( );
            if (z < smallest) return false;
            if (z == smallest) found = true;
        }

        return found;
    }

    @Override
    public String toString() {
        if (size() == 0) return "Min" + super.toString();
        else return "Min" + super.toString() + ", min = " + smallest;
    }
}