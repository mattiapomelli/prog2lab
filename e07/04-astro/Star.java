/**
 * Overview: le istanze di questa clase rappresentano delle stelle fisse.
 * 		     Una stella fissa e' caratterizzata da un nome, e da una posizione (px, py, pz), che non
 * 			 viene mai modificata
 * 
 * 			Gli oggetti di questo tipo non sono mutabili
 * 
 * Preservation of RI: name e position sono inizializzati non null, pertanto resteranno tali
 * 						in quanto name e' final e position non viene mai modificata
 */

public class Star extends CelestialBody {

	/**
	 * Inizializza this affinche rappresenti la stella di nome name, in posizione position
	 */
    public Star(String name, Point position) {
        super(name, position);
    }

	@Override
	/**
     * @return l'energia di this, data dalla sua energia potenziale
     */
	public long energy() {
		return potentialEnergy();
	}

	@Override
	public String toString() {
		return "Stella fissa, " + super.toString();
	}
}
