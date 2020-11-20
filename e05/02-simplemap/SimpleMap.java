import java.util.Map;
import java.util.Objects;
import java.util.HashMap;
import java.util.List;

/**
 * OVERVIEW: Le istanze di questa classe rappresentano mappe da stringhe a interi
 *           Una mappa tipica e' una struttura dati che associa a delle chiavi dei valori
 * 
 *           m = {"k1": v1, "k2": v2, ..., "kn": "vn"} in cui ki e' una Stringa unica a cui è associato un intero vi
 *           Gli oggetti di questo tipo sono mutabili
 * 
 * Funzione di Astrazione: AF(keys, values) = { "ki": vi | 0 <= i < keys.size() con "ki"=keys[i] e vi=values[i]}
 * 
 * Invariante di Rappresentazione: keys non contiene duplicati
 *                                 keys.size() == values.size()
 *                                 keys e value non sono null     
 * 
 * Invariante di Astrazione: una mappa non contiene chiavi duplicate
 *                           ad ogni chiave e' associato al piu un valore
 *                           una mappa ha una dimensione maggiore o uguale a 0             
 *
 */

public class SimpleMap {
    // CAMPI
    // struttura dati che contiene le chiavi della SimpleMap this
    private final List<String> keys;
    // struttura dati che contiene i valori della SimpleMap this
    private final List<Integer> values;

    // COSTRUTTORI
    /**
     * Post-condizioni: Inizializza this affinche rappresenti una mappa vuota
     * 
     * Preservation of RI: keys e values vengono inizializzati come  liste vuote
     *                     pertanto entrambi hanno dimensione uguale a 0, e percio keys.size() = values.size()
     *                     Siccome keys non contiene alcun elemento non puo avere duplicati
     * 
     * Correttezza: con keys e values liste vuote si ha AF(keys values) = {}
     * 
     * Preservation of AI: la mappa viene inizializzata senza alcun elemento, pertanto non contiene chiavi duplicate
     *                      e la dimensione e' 0
     */
    public SimpleMap() {
        keys = new LinkedList<>();
        values = new LinkedList<>();
        repOK();
    }

    // METODI
    /**
     * Effetti-collaterali: potrebbe modificare this
     * Post-condizioni: aggiunge la chiave k con associato n alla mappa
     *                  se k e' gia presente viene sostituito il valore associato a k con n
     *                  
     *                  solleva un'eccezione di tipo NullPointerException se k e' null
     * 
     *                  this = {"k1": v1, "k2": v2, ..., "kn": "vn"}
     *                  this_post = {"k1": v1, "k2": v2, ..., "kn": "vn", "k": n} 
     * 
     * Preservation of RI: se k e' presente in keys, allora non viene aggiunta a keys e pertanto
     *                     dopo la chiamata non conterra' duplicati e la sua dimensione non cambia.
     *                     Altrimenti sia la dimensione di keys che quella di values aumentano di 1.
     *                     per ipotesi induttiva prima della chiamata si ha: keys.size() == values.size() 
     *                     e keys.size() == values.size() -> keys.size() + 1 == values.size() + 1
     *                     
     * Correctness: sia la mappa prima della chiamata {"k1": v1, "k2": v2, ..., "kn": "vn"} 
     *              se k e' presente in keys la mappa sara' {"k1": v1, ..., "k": v ..., "kn": "vn"} con k gia presente in keys
     *              prima della chiamata
     *              altrimenti l'associazione "k": n viene aggiunta a keys
     * 
     * Preservation of AI: la chiave k viene aggiunta solo se non e' gia presente nella mappa, pertanto non si creano duplicati
     *                     La dimensione della mappa resta uguale o aumenta al piu di 1, pertanto dopo la chiamata e' >= 0
     *              
     */
    public void put(String k, int n) {
        Objects.requireNonNull(k); 
        
        int i = keys.indexOf(k);
        if (i == -1) {
            keys.add(k);
            values.add(v);
        } else {
            values.set(i, v);
        }

        repOK();
    }

    /**
     * Effetti-collaterali: potrebbe modificare this
     * Post-condizioni: rimuove la chiave k e l'elemento ad essa associato dalla mappa
     *                  e restituisce il valore dell'elemento rimosso, se k e' presente
     *                  altrimenti restituisce null
     * 
     *                  solleva un'eccezione di tipo NullPointerException se k e' null
     * 
     * Preservation of RI: non vengono aggiunte chiavi a keya, pertanto dopo la chiamata non conterra' duplicati,
     *                     La dimensione di keys e values resta invariata, oppure diminuiscono entrambe di 1
     *                     Per ipotesi induttiva prima della chiamata si ha: keys.size() == values.size() 
     *                     e keys.size() == values.size() -> keys.size() - 1 == values.size() - 1
     * 
     * Correctness: se k e' contenuta in keys in posizione i vengono rimossi gli elementi in posizione i
     *              sia di keys che di values. 
     *              Siccome AF(keys, values) = { "ki": vi | 0 <= i < keys.size() con "ki"=keys[i] e vi=values[i]}
     *              la rimozione di keys[i] e values[i] corrisponde all'eliminazione dell'associazione corrispondente
     *                
     */
    public void remove(String k) {
        Objects.requireNonNull(k); 
        
        int i = keys.indexOf(k);
        if (i != -1) {
            keys.remove(i);
            values.remove(i);
        }

        repOK();
    }

    /**
     * 
     * post-condizioni: restituisce il valore associato a k, se k e' presente, null altrimenti
     *                   solleva un'eccezione di tipo NullPointerException se k e' null
     * 
     * Correctness: Siccome AF(keys, values) = { "ki": vi | 0 <= i < keys.size() con "ki"=keys[i] e vi=values[i]}
     *              allora se la chiave k e' presente in keys alla posizione i, l'elemento associato ad essa
     *              e' l'elemento in posizione i di values
     *              Se k non e' presente in keys allora non vi e' nessun elemento in values associato a k, e pertanto
     *              e' corretto restituire null
     */
    public Integer get(String k) {
        Objects.requireNonNull(k); 
        int i = keys.indexOf(k);
        if (i != -1)
            return values.get(i);
        else
            return null;
    }

    /**
     * Post-condizioni: restituisce il numero di associazioni chiave-valore presenti
     * 
     * Correttezza: siccome AF(keys, values) = { "ki": vi | 0 <= i < keys.size() con "ki"=keys[i] e vi=values[i]}
     *              il numero di associazioni corrisponde alla dimensione di keys 
     */
    public int size() {
        return keys.size();
    }

    /**
     * Post-condizioni: restituisce true se l'invariante di rappresentazione e' valida, false altrimenti
     */
    private boolean repOK() {
        if(keys == null || values == null) return false;

        if(keys.size() != values.size()) return false;

        for(int i = 0; i < keys.size(); i++)
            for(int j = i + 1; j < keys.size(); j++)
                if(keys.get(i) == keys.get(j)) return false;

        return true;
    }

    /**
     * Post-condizioni: implementa la funzione di astrazione
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("SimpleMap : {");

        for (String k : keys)
            s.append('\"').append(k).append("\" : ").append(get(k)).append(", ");

        if (size() > 0)
            s.delete(s.length() - 2, s.length());

        return s.append('}').toString();
    }

    /**
     * Correctness: se obj non e' una SimpleMap non e' uguale a this
     *              se due mappe hanno dimensione diversa non possono essere uguali
     *              due mappe per essere uguali devono avere le stesse chiavi, e gli stessi elementi associati per ognuna di esse
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SimpleMap)) return false;
        SimpleMap other = (SimpleMap) obj;

        if (size() != other.size()) return false;

        for (String k : keys)
            if (!other.keys.contains(k) || get(k) != other.get(k)) return false;

        return true;
    }

    /**
     * Correctness: In una mappa l'ordine non conta, pertanto per garantire che due mappe uguali secondo equals
     *              abbiano lo stesso hashCode e' necessario ordinare le chiavi prima di calcolare hasCode degli elementi
     *              
     */
    public int hashCode() {
        List<String> list = new LinkedList<>(keys);
        Collections.sort(list);

        int result = Integer.hashCode(list.size());

        for (int i = 0; i < list.size(); i++) {
            String k = list.get(i);
            result = 31 * result + k.hashCode();
            result = 31 * result + Integer.hashCode(get(k));
        }

        return result;
    }

}