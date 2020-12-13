import java.util.List;
import java.util.Objects;

/**
 * Overview: le istanze di questa classe rappresentano dei pianeti. Un pianeta e' un corpo celeste
 *           caratterizzato da un nome, una posizione (px, py, px) e una velocita (vx, vy, vz)
 *           Gli oggetti di questo tipo sono mutabili
 * 
 * RI: RI(super) && velocity != null
 */
public class Planet extends CelestialBody {

    private Point velocity;

    /**
     * Inizializza this affinche rappresenti un pianeta di nome name, in posizione position e con velocita (0, 0, 0)
     */
    public Planet(String name, Point position) {
        super(name , position);
        this.velocity = new Point();
        assert repOk();
    }

    /**
     * @return l'energia cinetica di this, data dalla norma della sua velocita
     */
    public long cineticEnergy() {
        return (long) velocity.norm();
    }
    
    /**
     * Modifica la velocita di this in base all'attrazione verso i corpi celesti in bodies
     * @param bodies lista di corpi celesti da cui this subisce l'attrazione
     * @throws NullPointerException se bodies e' null o contiene elementi null
     * 
     * Preservation of RI: se bodies e' null o contiene elementi null viene sollevata un'eccezione
     * e pertanto velocity non viene modificata.
     * Si assume inoltre che al momento della chiamata position e velocity non siano null.
     * Pertanto le operazioni subtract e add coinvolgono solo punti non null, e di conseguenza anche il risultato 
     * prodotto, che viene assegnato a velocity, sara' tale
     */
    public void updateVelocity(List<CelestialBody> bodies) {   
        Objects.requireNonNull(bodies);
        int newX = 0, newY = 0, newZ = 0;

        for(CelestialBody b : bodies) {
            Objects.requireNonNull(b);
            Point diff = b.position.subtract(position);
            newX += Integer.signum(diff.x);
            newY += Integer.signum(diff.y);
            newZ += Integer.signum(diff.z);
        }

        velocity = velocity.add(new Point(newX, newY, newZ));
        assert repOk();
    }

    /**
     * Modifica la posizione di this, sommandone la velocita
     * 
     * Preservation of RI: si assume che prima della chiamata position e velocity siano non null
     * Pertanto l'operazione add coinvolge due punti non null, e di conseguenza anche il risultato, che viene
     * assegnato a position, sara' tale
     */
    public void updatePosition() {
        position = position.add(velocity);
        assert repOk();
    }

    @Override
    /**
     * @return l'energia di this, data dalla somma tra energia potenziale ed energia cinetica
     */
    public long energy() {
        return potentialEnergy() * cineticEnergy();
    }

    @Override 
    public String toString() {
        return "Pianeta, " + super.toString() + ", vel: " + velocity.toString();
    }

    @Override
    protected boolean repOk() {
        return super.repOk() && velocity != null;
    }
}
