/**
 * Overview: Le istanze di questa classe rappresentano delle bitmap
 *           Una bitmap e' una matrice di b x h pixel, con b ed h > 0
 *           Gli oggetti di questo tipo sono mutabili
 * 
 * AF(pixels, b, h) = [[pixels[0][0], pixels[0][1], ..., pixels[0][b]]]
 *                    [[pixels[1][0], pixels[1][1], ..., pixels[1][b]]]
 *                    [[                    ....                     ]]
*                     [[pixels[h][0], pixels[h][1], ..., pixels[h][b]]] 
 *          
 * RI: b > 0 && h > 0
 *    
 */
public class BitMap {
    
    // numero di colonne di this
    private final int b;
    // numero di righe di this
    private final int h;
    // struttura dati che contiene i pixel di this
    private Pixel pixels[][];

    /**
     * Inizializza this affinche rappresenti una bitmap di dimensione b x h, con tutti i pixel spenti
     * @param b numero di colonne
     * @param h numeo di righe
     * @throws IllegalArgumentException se b o h non sono positivi
     * 
     * Preservation of RI: se b <= 0 o h <= 0 viene sollevata un'eccezione, e this non viene inizializzato
     * Altrimenti this viene inizializzato con dimensioni b x h, le quali essendo final non verranno piu modificate
     */
    public BitMap(int b, int h) {
        if(b <= 0 || h <= 0) throw new IllegalArgumentException();
        this.b = b;
        this.h = h;
        this.pixels = new Pixel[b][h];

        for(int i = 0; i < b; i++)
            for(int j = 0; j < h; j++)
                pixels[i][j] = new Pixel();
        
        assert repOk();
    }

    /**
     * Disegna la figura f su this, ignorando le coordinate di f che non rientrano nelle dimensioni di this
     * this viene modificato
     * @param f figura da disegnare sulla bitmap this
     */
    public void draw(Figure f) {
        for(Coordinate coord : f) {
            int r = coord.getRow();
            int c = coord.getColumn();
            if(isValidCoordinate(r, c))
                pixels[r][c].turnOn();
        }
        assert repOk();
    }

    /**
     * spegne tutti i pixel in this, this viene modificato
     */
    public void turnAllOff() {
        for(int i = 0; i < b; i++)
            for(int j = 0; j < h; j++)
                pixels[i][j].turnOff();

        assert repOk();
    }

    /**
     * Inverte tutti i pixel di this, this viene modificato
     */
    public void invert() {
        for(int i = 0; i < b; i++)
            for(int j = 0; j < h; j++)
                pixels[i][j].invert();

        assert repOk();
    }

    /**
     * Accende il pixel in posizione (r, c), se (r, c) e una posizione valida in this
     * @param r riga del pixel da accendere
     * @param c colonna del pixel da accendere
     */
    public void turnPixelOn(int r, int c) {
        if(isValidCoordinate(r, c))
            pixels[r][c].turnOn();
        
        assert repOk();
    }

    /**
     * Spegne il pixel in posizione (r, c), se (r, c) e una posizione valida in this
     * @param r riga del pixel da spegnere
     * @param c colonna del pixel da spegnere
     */
    public void turnPixelOff(int r, int c) {
        if(isValidCoordinate(r, c))
            pixels[r][c].turnOff();

        assert repOk();
    }

    /**
     * @param r riga 
     * @param c colonna
     * @return true se (r, c) e' una coordinata valida di this, false altrimenti
     */
    private boolean isValidCoordinate(int r, int c) {
        return (r >= 0 && r < h && c >= 0 && c < b);
    }

    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < b; i++) {
            for(int j = 0; j < h; j++)
                res += pixels[i][j].toString();
            res += "\n";
        }
        return res;
    }

    private boolean repOk() {
        return b > 0 && h > 0;
    }
}
