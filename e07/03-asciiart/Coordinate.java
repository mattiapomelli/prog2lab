public class Coordinate {
    private int r;
    private int c;

    public Coordinate(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public String toString() {
        return "(" + r + ", " + c + ")";
    }

    public int getRow() {
        return r;
    }

    public int getColumn() {
        return c;
    }
}
