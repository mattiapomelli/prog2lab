/**
 * Overview: le istanze di questa classe rappresentano dei timbri
 *           Un timbro di una matrice M e' una figura caratterizzata da delle coordinate (r, c)
 *           dato dall'insieme {(r+i,c+j) | M[i][j] != 0}
 */
public class Stamp extends Figure{
    
    /**
     * Inizializza this affinche rappresenti il trimbro della matrice m alle coordinate (r, c)
     * @param m matrice del timbro
     * @param r riga del timbro
     * @param c colonna del timbro
     */
    public Stamp(int[][] m, int r, int c) {
        for (int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[0].length; j++) {
                if(m[i][j] != 0)
                    coordinates.add(new Coordinate(r + i, c + j));
            }
        }
    }
}
