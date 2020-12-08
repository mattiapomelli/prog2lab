/**
 * Overview: Le istanze di questa classe rappresentano dei pixel
 *           Un pixel e' un elemento di un immagine che puo trovarsi in uno di due possibili stati: acceso o spento
 *           Un pixel acceso e' rappresentato con '*'
 *           Mentre un pixel spento e' rappresentano con '.'
 * 
 *          Gli oggetti di questo tipo sono mutabili
 * 
 * AF(on) = * se on == true
 *          . se on == false
 */
public class Pixel {
    
    // campo che rappresenta lo stato del pixel
    public boolean on;

    /**
     * Inizializza this affinche rappresenti un pixel spento
     */
    public Pixel() {
        on = false;
    }

    /**
     * Modifica this, rendendo lo stato 'acceso'
     */
    public void turnOn() {
        on = true;
    }

    /**
     * Modifica this, rendendo lo stato 'spento'
     */
    public void turnOff() {
        on = false;
    }

    /**
     * Modifica this, invertendone lo stato
     *      this_post.on = true se this.on = false;
     *      this_post.in = false se this.false = true
     */
    public void invert() {
        on = !on;
    }

    @Override
    public String toString() {
        return on ? "*" : "-";
    }

    @Override
    public boolean equals(Object o) {
        if( !(o instanceof Pixel)) return false;
        Pixel p = (Pixel) o;
        return on == p.on;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(on);
    }
}
