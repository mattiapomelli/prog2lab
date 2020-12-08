import java.util.List;

/**
 * Overview: le istanze di questa classe rappresentano dei pianeti Un pianeta e'
 *           caratterizzato da un nome, un vettore posizione (px, py, px) e un vettore velocita (vx, vy, vz)
 *           Gli oggetti di questo tipo sono mutabili
 */
public class Planet extends CelestialBody {

    private Vector velocity;

    /**
     * Inizializza this affinche rappresenti un pianeta di nome name, in posizione position e con velocita (0, 0, 0)
     */
    public Planet(String name, Vector position) {
        super(name , position);
        this.velocity = new Vector();
    }

    /**
     * @return l'energia cinetica di this
     */
    public long cineticEnergy() {
        return (long) velocity.norm();
    }
    
    /**
     * Modifica la velocita di this in base all'attrazione con i corpi celesti in bodies
     * @param bodies lista di corpi celesti da cui this subisce l'attrazione
     */
    public void updateVelocity(List<CelestialBody> bodies) {      
        int newX = 0, newY = 0, newZ = 0;

        for(CelestialBody b : bodies) {
            newX += position.compareX(b.position);
            newY += position.compareY(b.position);
            newZ += position.compareZ(b.position);
        }
        velocity = velocity.add(new Vector(newX, newY, newZ));
    }

    /**
     * Modifica la posizione di this
     */
    public void updatePosition() {
        position = position.add(velocity);
    }

    @Override
    public long energy() {
        return potentialEnergy() * cineticEnergy();
    }

    @Override 
    public String toString() {
        return "Pianeta, " + super.toString() + ", vel: " + velocity.toString();
    }
}
