/**
 * Overview: Le istanze di questa classe rappresentano delle bitmap
 *           Una tipica bitmap e' una matrice di b x h pixel
 * 
 *          Gli oggetti di questo tipo sono mutabili
 */
public class BitMap {
    
    private final int w; // colonne
    private final int h; // righe
    private int pixels[][];

    public BitMap(int w, int h) {
        this.w = w;
        this.h = h;
        this.pixels = new int[h][w];
    }

    public void draw(Figure f) {
        for(Coordinate coord : f) {
            int r = coord.getRow();
            int c = coord.getColumn();
            if(r >= 0 && r < h && c >= 0 && c < w)
                pixels[r][c] = 1;
        }
    }

    @Override
    public String toString() {
        String res = "BitMap: \n";
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++)
                res += pixels[i][j];
            res += "\n";
        }
        return res;
    }
}
