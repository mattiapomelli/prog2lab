public class Rectangle extends Figure {

    public Rectangle(int h, int l, int r, int c) {
        super();
        HorizontalSegment hs1 = new HorizontalSegment(l, r, c);
        HorizontalSegment hs2 = new HorizontalSegment(l, r + h - 1, c);
        VerticalSegment vs1 = new VerticalSegment(h, r, c);
        VerticalSegment vs2 = new VerticalSegment(h, r, c + l -1);
        coordinates.addAll(hs1.coordinates);
        coordinates.addAll(hs2.coordinates);
        coordinates.addAll(vs1.coordinates);
        coordinates.addAll(vs2.coordinates);
    }
}
