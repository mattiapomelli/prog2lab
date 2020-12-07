public class HorizontalSegment extends Figure {

    public HorizontalSegment(int l, int r, int c) {
        super();
        for (int i = 0; i < l; i++) {
            coordinates.add(new Coordinate(r, c + i));
        }
    }
}
