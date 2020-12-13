import java.util.Objects;

/**
 * Overview: Le istanze di questa classe rappresentano punti tridimensionali, a coordinate intere
 *           Un punto tipico e' (x, y, z) con x, y e z interi.
 * 
 *           Gli oggetti di questo tipo sono immutabili, pertanto le proprieta rimarrano preservate
 * 
 */
public class Point {

    public final int x;
    public final int y;
    public final int z;

    /**
     * AF(x, y, z) = (x, y, z)
     */

    /**
     * Inizializza this affinche rappresenti il punto (x, y, z)
     * 
     * @param x coordinata x
     * @param y coordinata y
     * @param z coordinata z
     */
    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Inizializza this affinche rappresenti il punto (0, 0, 0)
     */
    public Point() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * @return la norma di this, ovvero la radice quadrata del prodotto
     *         scalare tra this e se stesso
     */
    public double norm() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * @param p punto da sommare a this
     * @return un nuovo punto che rappresenta la somma tra this e p
     * @throws NullPointerException se p e' null
     */
    public Point add(Point p) {
        Objects.requireNonNull(p);
        return new Point(x + p.x, y + p.y, z + p.z);
    }

    /**
     * @param p punto da sottrarre a this
     * @return un nuovo punto che rappresenta la differenza tra this e p
     * @throws NullPointerException se p e' null
     */
    public Point subtract(Point p) {
        Objects.requireNonNull(p);
        return new Point(x - p.x, y - p.y, z - p.z);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
