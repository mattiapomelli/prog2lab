import java.util.Iterator;

public class MaxIntSet extends IntSet {

    /**
     * Funzione di Astrazione: AF_MaxIntSet(this) = AF_IntSet(this)
     * 
     * Invariante di Rappresentazione: this.size > 0 ->  biggest apprtiene a this && per ogni x in this  biggest >= x
     */
    
    // CAMPI
    // che rappresenta l'elemento maggiore, se l'insieme non e' vuoto
    private int biggest;

    // COSTRUTTORI
    /**
     * Post-condizioni: inizializza this affinche rappresenti un insieme vuoto
     */
    public MaxIntSet() {
        super();
    }

    /**
     * Effetti-collaterali: potrebbe modificare biggest
     * Post-condizioni: inserisce x nell'insieme e aggiorna biggest se x e' maggiore di esso o l'insieme e' vuoto
     */
    @Override
    public void insert(int x) {
        if (size() == 0 || x > biggest) biggest = x;
        super.insert(x);

        assert repOk();
    }

    /**
     * Effetti-collaterali: potrebbe modificare biggest
     * Post-condizioni: rimuove x dall'insieme e aggiorna biggest l'elemento rimosso era il maggiore
     */
    public void remove (int x) {
        super.remove(x);
        if (size() == 0 || x < biggest) return;
        Iterator it = iterator();
        biggest = ((Integer) it.next( )).intValue( );

        while (it.hasNext()) {
            int z = ((Integer) it.next( )).intValue( );
            if (z > biggest) biggest = z;
        }

        assert repOk();
    }

    /**
     * Post-condizioni: se l'insieme e' vuoto solleva un eccezione di tipo EmptyException
     *                  altrimenti restituisce l'elemento maggiore in this
     */
    public int max() {
        if (size() == 0) throw new EmptyException();
        return biggest;
    }

    @Override
    public boolean repOk() {
        if (!super.repOk()) return false;
        if (size() == 0) return true;

        boolean found = false;
        Iterator it = iterator();

        while (it.hasNext()) {
            int z = ((Integer) it.next( )).intValue( );
            if (z > biggest) return false;
            if (z == biggest) found = true;
        }

        return found;
    }

    @Override
    public String toString() {
        if (size() == 0) return "Max" + super.toString();
        else return "Max" + super.toString() + ", max = " + biggest;
    }
}