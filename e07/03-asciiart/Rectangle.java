public class Rectangle extends Figure {

    public Rectangle(int l, int h, int r, int c) {
        super();
        for(int i = 0; i < l; i++) {
            coordinates.add(new Coordinate(r + i, c));
        }
    }
}
