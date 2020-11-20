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

    /**
     * Funzione di astrazione: AF(elements) = { elements.get(i) | 0 <= i <= elements.size() }
     * 
     * Invariante di Rappresentazione: elements non e' null, L'insieme non contiene duplicati, quindi
     *                                 per ogni i, j  compresi tra 0 e size() vale  i != j -> elements.get(i) != elements.get(j)
     */
    
    // COSTRUTTORI
    /**
     * Post condizioni: Inizializza un nuovo insieme di interi vuoto
     */
    public IntSet() {
        elements = new ArrayList<>();
        assert repOk();
    }

    // METODI
    /**
     * Effetti collaterali: potrebbe modificare this: this_post = this + {x}
     * Post condizioni: Aggiunge x all'insieme this se non e' gia presente
     */
    public void insert(int x) {
        if (!this.contains(x)) elements.add(x);
        assert repOk();
    }

    /**
     * Effetti collaterali: potrebbe modificare this: this_post = this - {x}
     * Post-condizioni: rimuove x dall'insieme this, se presente
     */
    public void remove(int x) {
        int index = elements.indexOf(x);                    // indice dell'elemento da rimuovere, -1 se non e' presente

        if (index != -1) {
            int lastIndex = elements.size() - 1;            // indice dell'ultimo elemento    
            elements.set(index, elements.get(lastIndex));   // scambio elemento da modificare con l'ultimo elemento
            elements.remove(lastIndex);                     // rimuovi elemento in ultima posizione
        }

        assert repOk();
    }

    /**
     * Post-condizioni: restituisce un intero scelto arbitrariamente tra gli elementi di this
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
     * Post-condizioni: implementa la funzione di astrazione
     */
    @Override                       
    public String toString() {
        assert repOk();

        String r = "IntSet: {";
        if (size() > 0) {
            for (int i = 0; i < size() - 1; i++)
                r += elements.get(i) + ", ";
            r += elements.get(elements.size() - 1);
        }
        return r + "}";
    }

    /**
     * Post-condizioni: restituisce true se l'invariante di rappresentazione e' verificata
     */
    public boolean repOk() {
        if(elements == null) return false;
        for (int i = 0; i < size(); i++)
            for (int j = i + 1; i < size(); j++)
                if(elements.get(i) == elements.get(j)) return false;

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntSet)) return false;
        IntSet other = (IntSet) obj;

        if(size() != other.size()) return false;

        for(int i = 0; i < size(); i++)
            if(!other.contains(elements.get(i))) return false;

        return true;
    }
}