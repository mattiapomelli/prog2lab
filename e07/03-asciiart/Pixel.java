public class Pixel {
    
    public boolean on;

    public Pixel() {
        on = false;
    }

    public void turnOn() {
        on = true;
    }

    public void turnOff() {
        on = false;
    }

    public void invert() {
        on = !on;
    }

    public String toString() {
        return on ? "*" : "-";
    }
}
