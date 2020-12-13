import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/*
 *  Overview: Le istanze di questa classe rappresentano dei sistemi astronomici
 *            Un sistema astronomico e' una collezione di corpi celesti, i quali possono essere pianeti o stelle
 * 
 *            Gli oggetti di questo tipo sono mutabili
 * 
 *  AF(bodies) = { bodies[i] | 0 <= i <= bodies.size() }
 *  RI: bodies non null e non contiene elementi null
 * 
 *  Preservation of RI: bodies viene inizializzata null, e mai riassegnata in quanto final.
 */
public class AstronomicalSystem {

    // struttura dati che contiene i corpi celesti del sistema astronomico
    private final List<CelestialBody> bodies;

    /**
     * Inizializza this affinche rappresenti un sistema astronomico senza alcun corpo celeste
     */
    public AstronomicalSystem() {
        bodies = new ArrayList<>();
        assert repOk();
    }

    /**
     * Aggiunge il corpo celeste p a this
     * @param p un corpo celeste da aggiungere a this
     * @throws NullPointerException se p e' null
     * 
     * Preservation of RI: Si assume che RI sia valida prima della chiamata e che quindi non contegna elementi null.
     *                     p viene aggiunto a bodies solo se non null, pertanto anche dopo la chiamata bodies non
     *                     conterra' elementi null
     */
    public void addBody(CelestialBody p) {
        Objects.requireNonNull(p);
        bodies.add(p);
        assert repOk();
    }

    /**
     * @return un generatore che produce i pianeti presenti in this
     */
    public Iterator<Planet> planets() {
        return new Iterator<Planet>() {

            private final Iterator<CelestialBody> it = bodies.iterator();
            private Planet cache = null;

            @Override
            public boolean hasNext() {
                if( cache != null) return true;
                boolean found = false;
                while(it.hasNext() && !found) {
                    CelestialBody b = it.next();
                    if(b instanceof Planet) {
                        cache = (Planet) b;
                        found = true;
                    }
                }
                return found;
            }

            @Override
            public Planet next() {
                if(!hasNext()) throw new NoSuchElementException();
                Planet p = cache;
                cache = null;
                return p;
            }
        };
    }

    /**
     * Evolve il sistema astronomico al prossimo stato
     */
    public void evolve() {
        attractPlanets();
        movePlanets();
    }

    /**
     * evolve la velocita dei pianeti di this
     */
    private void attractPlanets() {
        Iterator<Planet> it = planets();
        while(it.hasNext()) {
            Planet p = it.next();
            p.updateVelocity(bodies);
        }
    }

    /**
     * evolve la posizione dei pianeti di this
     */
    private void movePlanets() {
        Iterator<Planet> it = planets();
        while(it.hasNext()) {
            Planet p = it.next();
            p.updatePosition();
        }
    }

    /**
     * @return l'energia totale di this
     */
    public long totalEnergy() {
        long energy = 0;
        for(CelestialBody b : bodies) 
            energy += b.energy();
        return energy;
    }

    @Override
    public String toString() {
        String res ="";
        Collections.sort(bodies,
            new Comparator<>() {
                @Override
                public int compare(CelestialBody o1, CelestialBody o2) {
                    return o1.name.compareTo(o2.name);
                }
            }
        );

        for(CelestialBody b : bodies) {
            res += b.toString() + "\n" ;
        }
        res += "Energia totale: " + totalEnergy();
        return res;
    }

    private boolean repOk() {
        if(bodies == null) return false;
        
        for(CelestialBody b : bodies)
            if(b == null) return false;
        
        return true;
    }
}