public class VerticalSegment extends Figure {

    public VerticalSegment(int l, int r, int c) {
        super();
        for(int i = 0; i < l; i++) {
            coordinates.add(new Coordinate(r + i, c));
        }
    }
}
