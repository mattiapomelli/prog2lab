/**
 * Overview: le istanze di questa clase rappresentano delle stelle
 * 		     Una stella e' caratterizzata da un nome, e da una posizione (px, py, pz)
 * 
 * 			Gli oggetti di questo tipo non sono mutabili
 */

public class Star extends CelestialBody {

	/**
	 * Inizializza this affinche rappresenti la stella di nome name, in posizione position
	 */
    public Star(String name, Vector position) {
        super(name, position);
    }

	@Override
	public long energy() {
		return potentialEnergy();
	}

	@Override
	public String toString() {
		return "Stella, " + super.toString();
	}
    
}
