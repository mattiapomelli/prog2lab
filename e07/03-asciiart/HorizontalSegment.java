/**
 * Overview: Le istanze di questa classe rappresentano dei segmenti orizzontali
 *           Un segmento orizzontale e' una figura caratterizzata da una dimensione l
 *           e delle coordinate (r, c), dato dall'insieme {(r,c), (r,c+1), ..., (r,c+l-1)}
 */
public class HorizontalSegment extends Figure {

    /**
     * Inizializza this affinche rappresenti il segmento orizzontale di lunghezza l alle coordinate (r, c)
     * @param l dimensione del segmento
     * @param r riga del segmento
     * @param c colonna del segmento
     */
    public HorizontalSegment(int l, int r, int c) {
        super();
        for (int i = 0; i < l; i++) {
            coordinates.add(new Coordinate(r, c + i));
        }
    }
}
