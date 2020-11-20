import java.util.ArrayList;
import java.util.List;

/**
 * OVERVIEW: Le istanze di questa classe rappresentano insiemi (non limitati) di interi.
 *           gLi oggetti di questo tipo sono mutabili
 * 
 */
public class IntSet {

    // CAMPI
    /** Struttura dati contenente gli elementi dell'insieme */
    private List<Integer> elements;
    
    // COSTRUTTORI
    /**
     * Post condizioni: Inizializza un nuovo insieme di interi vuoto
     */
    public IntSet() {
        elements = new ArrayList<>();
    }

    // METODI
    /**
     * Effetti collaterali: modifica this: this_post = this + {x}
     * Post condizioni: Aggiunge x all'insieme this
     */
    public void insert(int x) {
        if (!this.contains(x)) elements.add(x);
    }

    /**
     * Effetti collaterali: potrebbe modificare this: this_post = this - {x}
     * Post-condizioni: rimuove x dall'insieme this
     */
    public void remove(int x) {
        //elements.remove(Integer.valueOf(x));
        int index = elements.indexOf(x);                    // indice dell'elemento da rimuovere, -1 se non e' presente

        if (index != -1) {
            int lastIndex = elements.size() - 1;            // indice dell'ultimo elemento    
            elements.set(index, elements.get(lastIndex));   // scambio elemento da modificare con l'ultimo elemento
            elements.remove(lastIndex);                     // rimuovi elemento in ultima posizione
        }
    }

    /**
     * Post-condizioni: restituisce un intero scelto arbitrariamente tra gli elementi di thihs
     *                  e solleva un'eccezione di tipo EmptyException se l'insieme e' vuoto;
     */
    public int choose() {
        if (size() == 0) throw new EmptyException("Impossibile estrarre elemento. Insieme vuoto");
        return elements.get(elements.size() - 1);
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
    public boolean contains(int x) {
        return elements.contains(x);
    }

    /**
     * Post-condizioni: restituisce una rappresentazione testuale di this
     *                  ad esempio {1, 2, 3}
     */
    @Override                       
    public String toString() {
        String r = "IntSet: {";
        if (size() > 0) {
            for (int i = 0; i < size() - 1; i++)
                r += elements.get(i) + ", ";
            r += elements.get(elements.size() - 1);
        }
        return r + "}";
    }
}