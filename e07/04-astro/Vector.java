import java.util.Objects;

/**
 * Overview: Le istanze di questa classe rappresentano vettori tridimensionali
 * nello spazio, a coordinate intere. Un vettore tipico e' (x, y, z) dove x, y e
 * z sono interi
 * 
 * Gli oggetti di questo tipo sono immutabili
 */
public class Vector {

    private final int x;
    private final int y;
    private final int z;

    /**
     * AF(x, y, z) = (x, y, z)
     */

    /**
     * Inizializza this affinche rappresenti il vettore (x, y, z)
     * 
     * @param x coordinata x
     * @param y coordinata y
     * @param z coordinata z
     */
    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Inizializza this affinche rappresenti il vettore (0, 0, 0)
     */
    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * @return la norma del vettore this, ovvero la radice quadrata del prodotto
     *         scalare tra this e se stesso
     */
    public double norm() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * @param v un vettore da sommare a this
     * @return un nuovo vettore che rappresenta la somma tra this + v
     * @throws NullPointerException se v e' null
     */
    public Vector add(Vector v) {
        Objects.requireNonNull(v);
        return new Vector(x + v.x, y + v.y, z + v.z);
    }

    /**
     * @param o vettore la cui coordinata x viene confrontata con quella di this
     * @return 0 se this e o hanno la stessa coordinata x, 1 se this ha coordinata x maggiore di o, -1 altrimenti
     * @throws NullPointerException se o e' null
     */
    public int compareX(Vector o) {
        Objects.requireNonNull(o);
        int res;
        if( (res = Integer.compare(o.x, x)) != 0)
            return res > 0 ? 1 : -1;
        return res;
    }

    /**
     * @param o vettore la cui coordinata y viene confrontata con quella di this
     * @return 0 se this e o hanno la stessa coordinata y, 1 se this ha coordinata y maggiore di o, -1 altrimenti
     * @throws NullPointerException se o e' null
     */
    public int compareY(Vector o) {
        Objects.requireNonNull(o);
        int res;
        if( (res = Integer.compare(o.y, y)) != 0)
            return res > 0 ? 1 : -1;
        return res;
    }

    /**
     * @param o vettore la cui coordinata z viene confrontata con quella di this
     * @return 0 se this e o hanno la stessa coordinata z, 1 se this ha coordinata z maggiore di o, -1 altrimenti
     * @throws NullPointerException se o e' null
     */
    public int compareZ(Vector o) {
        Objects.requireNonNull(o);
        int res;
        if( (res = Integer.compare(o.z, z)) != 0)
            return res > 0 ? 1 : -1;
        return res;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
