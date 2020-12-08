/**
 * Overview: Le istanze di questa classe rappresentano delle bitmap
 *           Una tipica bitmap e' una matrice di b x h pixel
 * 
 *          Gli oggetti di questo tipo sono mutabili
 */
public class BitMap {
    
    private final int b; // colonne
    private final int h; // righe
    private Pixel pixels[][];

    public BitMap(int b, int h) {
        this.b = b;
        this.h = h;
        this.pixels = new Pixel[b][h];

        for(int i = 0; i < b; i++)
            for(int j = 0; j < h; j++)
                pixels[i][j] = new Pixel();
    }

    public void draw(Figure f) {
        for(Coordinate coord : f) {
            int r = coord.getRow();
            int c = coord.getColumn();
            if(r >= 0 && r < h && c >= 0 && c < b)
                pixels[r][c].turnOn();
        }
    }

    public void turnAllOff() {
        for(int i = 0; i < b; i++)
            for(int j = 0; j < h; j++)
                pixels[i][j].turnOff();
    }

    public void invert() {
        for(int i = 0; i < b; i++)
            for(int j = 0; j < h; j++)
                pixels[i][j].invert();
    }

    public void turnPixelOn(int r, int c) {
        if(r >= 0 && r < h && c >= 0 && c < b)
            pixels[r][c].turnOn();
    }

    public void turnPixelOff(int r, int c) {
        if(r >= 0 && r < h && c >= 0 && c < b)
            pixels[r][c].turnOff();
    }

    @Override
    public String toString() {
        String res = "BitMap: \n";
        for(int i = 0; i < b; i++) {
            for(int j = 0; j < h; j++)
                res += pixels[i][j].toString();
            res += "\n";
        }
        return res;
    }
}
