import java.util.Objects;

/**
 * Overview: Le istanze di questa classe rappresentano dei corpi celesti.
 *           Un corpo celeste e' caratterizzato da un nome, non mutabile, e da una posizione,
 *           che potrebbe essere modificata
 * 
 * AF(name, position) = corpo celeste di nome name e posizione (position.x, position.y, position.z)
 * RI: name != null && position != null
 * 
 * Preservation of RI: name e position sono inizializzati non null, pertanto name restera' tale in quanto final
 *                     la posizione potrebbe mutare e deve essere mantenuta non null dalle sottoclassi
 *      
 */

public abstract class CelestialBody {
    
    public final String name;
    protected Point position;

    /**
     * Inizializza this affinche rappresenti un corpo celeste di nome name e in posizione position
     * @param name nome di this
     * @param position posizione iniziale di this
     * @throws NullPointerException se name o position sono null
     */
    public CelestialBody(String name, Point position) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(position);

        this.name = name;
        this.position = position;
        assert repOk();
    }

    /**
     * @return un long che rappresenta l'energia di this
     */
    public abstract long energy();

    /**
     * @return un long che rappresenta l'energia potenziale di this
     */
    public long potentialEnergy() {
        return (long) position.norm();
    };

    @Override
    public String toString() {
        String res = "nome: " + name;
        return res + ", pos: " + position.toString();
    }

    protected boolean repOk() {
        return position != null && name != null;
    }
}
