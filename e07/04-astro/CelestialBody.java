/**
 * Le istanze di questa classe rappresentano dei corpi celesti
 */

public abstract class CelestialBody {
    
    public final String name;
    public Vector position;

    /**
     * Inizializza this affinche rappresenti un corpo celeste di nome name e in posizione position
     * @param name nome di this
     * @param position posizione iniziale di this
     */
    public CelestialBody(String name, Vector position) {
        this.name = name;
        this.position = position;
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
}
