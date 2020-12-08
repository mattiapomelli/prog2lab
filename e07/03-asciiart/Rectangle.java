/**
 * Overview: le istanze di questa classe rappresentano dei rettangoli
 *           Un rettangolo e' una figura caratterizzata da un'altezza h e una larghezza l
 *           e delle coordinate (r, c), dato dall'insieme {
 *              (r,c), (r+1,c), ..., (r+h-1,c),
 *              (r,c+l-1), (r+1,c+l-1), ..., (r+h-1,c+l-1),
 *              (r,c), (r,c+1), ..., (r,c+l-1),
 *              (r+h-1,c), (r+h-1,c+1), ..., (r+h-1,c+l-1),
 *          }
 */
public class Rectangle extends Figure {

    /**
     * Inizializza this affinche rappresenti il rettangolo di dimensioni l x h alle coordinate (r, c)
     * @param h altezza del rettangolo
     * @param l larghezza del rettangolo
     * @param r riga del rettangolo
     * @param c colonna del rettangolo
     */
    public Rectangle(int h, int l, int r, int c) {
        super();
        HorizontalSegment hs1 = new HorizontalSegment(l, r, c);
        HorizontalSegment hs2 = new HorizontalSegment(l, r + h - 1, c);
        VerticalSegment vs1 = new VerticalSegment(h, r, c);
        VerticalSegment vs2 = new VerticalSegment(h, r, c + l -1);
        coordinates.addAll(hs1.coordinates);
        coordinates.addAll(hs2.coordinates);
        coordinates.addAll(vs1.coordinates);
        coordinates.addAll(vs2.coordinates);
    }
}
