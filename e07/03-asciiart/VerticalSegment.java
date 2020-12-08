/**
 * Overview: Le istanze di questa classe rappresentano dei segmenti verticali
 *           Un segmento orizzontale e' una figura caratterizzata da una dimensione h
 *           e delle coordinate (r, c), dato dall'insieme {(r,c), (r+1,c), ..., (r+h-1,c)}
 */
public class VerticalSegment extends Figure {

    /**
     * Inizializza this affinche rappresenti il segmento verticale di dimensione e coordinate (r, c)
     * @param l dimensione del segmento
     * @param r riga del segmento
     * @param c colonna del segmento
     */
    public VerticalSegment(int l, int r, int c) {
        super();
        for(int i = 0; i < l; i++) {
            coordinates.add(new Coordinate(r + i, c));
        }
    }
}
