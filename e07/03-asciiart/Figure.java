import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Overview: Le istanze di questa classe rappresentano delle figure
 *           Una figura e' un insieme finito di coordinate {(r1, c1),(r2, c2), ..., (rn, cn)} tali che 
 *           i != j -> (ri, yi) != (rj, cj)  per i=0,..,n j=0,..,n
 * 
 * AF(coordinates) = { (ri, ci) | 0 <= i < coordinates.size() }
 * RI: coordinates non contiene duplicati, garantito perche' coordinates e' un UnBoundedSet, il quale non contiene duplicati
 *          
 */
public abstract class Figure implements Iterable<Coordinate> {
    
    // Struttura dati che contiene le coordinate di this
    protected UnBoundedSet<Coordinate> coordinates;

    /**
     * Inizializza this affinche rappresenti la figura con nessuna coordinata
     */
    public Figure() {
        coordinates = new UnBoundedSet<>();
    }

    @Override
    public String toString() {
        String res = "Figure: {";
        if(coordinates.size() > 0) {
            int i;
            for( i = 0; i < coordinates.size() - 1; i++)
                res += coordinates.get(i).toString() + ", ";
            res += coordinates.get(i).toString();
        }

        return res + "}";
    } 

    /**
     * @return un generatore che produce le coordinate di this
     */
    @Override
    public Iterator<Coordinate> iterator() {
        return new Iterator<Coordinate>() {

            // RI: i <= coordinates.size()

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < coordinates.size();
            }

            @Override
            public Coordinate next() {
                if(!hasNext()) throw new NoSuchElementException();
                return coordinates.get(i++);
            }
        };
    }

    @Override public boolean equals(Object o) {
        if( !(o instanceof Figure)) return false;
        Figure f = (Figure) o;
        return coordinates.equals(f.coordinates);
    }
}
