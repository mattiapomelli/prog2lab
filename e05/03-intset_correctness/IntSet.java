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
     * Funzione di astrazione: AF(elements) = { elements.get(i) | 0 <= i < elements.size() }
     * 
     * Invariante di Rappresentazione: elements non e' null, L'insieme non contiene duplicati, quindi
     *                                 per ogni i, j  compresi tra 0 e size() vale  i != j -> elements.get(i) != elements.get(j)
     * 
     * Invarianti di Astrazione: la dimensione di un insieme e' maggiore o uguale a 0
     *                           un insieme non contiene elementi duplicati
     */
    
    // COSTRUTTORI
    /**
     * Post condizioni: Inizializza un nuovo insieme di interi vuoto
     * 
     * Preservation of RI: elements viene inizializzato ad una lista vuota, pertanto non conterra' duplicati
     *                     e non sara' nullo, RI e' quindi preservata
     * 
     * Correctness: il costruttore inizializza elements ad una lista vuota, che ha quindi size = 0
     *              per size = 0 AF mappa elements ad un insieme { elements.get(i) | 0 <= i < 0 }
     *              che corrisponde all'insieme vuoto, infatti non esistono valori i che siano maggiori o uguali a zero
     *              e minori di zero contemporaneamente
     * 
     * Preservation of AI: il costruttore inizializza un insieme vuoto, quindi di dimensinoe 0.
     *                     L'insieme non contiene elementi e pertanto tanto meno duplicati.
     *                      Le AI sono quindi preservate.
     */
    public IntSet() {
        elements = new ArrayList<>();
        assert repOk();
    }

    // METODI
    /**
     * Effetti collaterali: potrebbe modificare this: this_post = this + {x}
     * Post condizioni: Aggiunge x all'insieme this se non e' gia presente
     * 
     * Preservation of RI: insert aggiunge x ad elements solo se non e' gia presente
     *                     pertanto dopo la chiamata non ci saranno duplicati in elements, RI e' quidni preservata
     * 
     * Correctness: il metodo controlla che l'elemento da aggiungere non sia gia presente nella lista, se e' presente allora
     *              non esegue alcune operazione
     *              Altrimenti aggiunge l'elemento x alla lista, e siccome AF mappa gli elementi della lista a quelli
     *              dell'insieme anche l'insieme conterra' x
     * 
     * Preservation of AI: insert incrementa o lascia inalterata la dimensione dell'insieme.
     *                      Inoltre aggiunge un elemento solo se non e' gia presente e quindi non inserisce duplicati
     *                      Le AI sono quindi preservate
     */
    public void insert(int x) {
        if (!this.contains(x)) elements.add(x);
        assert repOk();
    }

    /**
     * Effetti collaterali: potrebbe modificare this: this_post = this - {x}
     * Post-condizioni: rimuove x dall'insieme this, se presente
     * 
     * Preservation of RI: indexOf non modifica elements
     *                      insert non aggiunge elementi ad elements,
     *                      siccome prima della chiamata elements non contiene duplicati, anche dopo la chiamata
     *                      non ne conterra' perche' nessun elemento viene aggiunto, pertanto RI e' preservata
     * 
     * Correctness: il metodo controlla se l'elemento da rimuovere e' presente nella lista, se non e' cosi allora non
     *              esegue alcuna operazione.
     *              Altrimenti rimuove l'elemento x dalla lista, e siccome AF mappa gli elementi della lista a quelli
     *              dell'insieme anche l'insieme non conterra' x
     * 
     * Preservation of AI: remove rimuove un elemento dall'insieme solo se era presente.
     *                     Pertanto la dimensione diminuisce di 1 solo se era maggiore di 0 prima della chiamata e
     *                     quindi non e' mai minore di 0 dopo la chiamata.
     *                     remove non aggiunge elementi all'insieme e pertanto non introduce duplicati.
     *                     Le AI sono quindi preservate
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
     * 
     * Correctness: se la lista e' vuota viene sollevata un'eccezione e non viene quindi scelto nessun elemento
     *              Altrimenti viene scelto l'elemento in ultima posizione della lista, siccome AF mappa gli 
     *              elementi della lista (dalla prima all'ultima posizione) agli elementi dell'insieme
     *              l'elemento scelto fa parte anche dell'insieme
     */
    public int choose() {
        if (size() == 0) throw new EmptyException("Impossibile estrarre elemento. Insieme vuoto");
        return elements.get(elements.size() - 1);
    }

    /**
     * Post-condizioni: restituisce la cardinalita' dell'insieme this
     * 
     * Correctness: Siccome AF mappa gli elementi della lista a quelli del insieme, e RI garantisce
     *              che non ci sono duplicati, la dimensione della lista che viene restituita e' uguale
     *              alla dimensione dell'insieme
     */
    public int size() {
        return elements.size();
    }
    
    /**
     * Post-condizioni: restituisce true se x e' in this, false altrimenti
     * 
     * Correctness: il metodo restituisce true solo se l'elemento x appartiene alla lista. AF mappa gli
     *              elementi della lista agli elementi dell'insieme.
     *              Pertanto se x appartiene alla lista appartiene anche all'insieme
     *              Verra' quindi restituito true solo se x appartiene all'insieme, false altrimenti.
     */
    public boolean contains(int x) {
        return elements.contains(x);
    }

    /**
     * Post-condizioni: implementa la funzione di astrazione
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

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}