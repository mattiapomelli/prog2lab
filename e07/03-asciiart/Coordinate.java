/**
 * Overview: Le istanze di questa classe rappresentano delle coordinate intere
 *           Una coordinata e' una coppia (r, c) in cui r rappresenta la riga e c la colonna
 * 
 *          Gli oggetti di questo tipo sono immutabili
 * 
 * AF(r, c) = (r, c);
 */
public class Coordinate {
    // componenti della coordinata
    private final int r;
    private final int c;

    /**
     * Inizializza this affinche rappresenti la coordinata (r, c)
     * @param r riga 
     * @param c colonna
     */
    public Coordinate(int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    /**
     * @return la riga di this
     */
    public int getRow() {
        return r;
    }
    
    /**
     * @return la colonna di this
     */
    public int getColumn() {
        return c;
    }

    @Override
    public String toString() {
        return "(" + r + ", " + c + ")";
    }

    @Override
    public boolean equals(Object o) {
        if( !(o instanceof Coordinate)) return false;
        Coordinate other = (Coordinate) o;
        return r == other.r && c == other.c;  
    }

    @Override
    public int hashCode() {
        int res = Integer.hashCode(r);
        return 31 * res + Integer.hashCode(c);
    }
}
