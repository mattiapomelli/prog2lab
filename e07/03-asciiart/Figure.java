import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Figure implements Iterable<Coordinate> {
    
    protected List<Coordinate> coordinates;

    public Figure() {
        coordinates = new ArrayList<>();
    }

    public String toString() {
        String res = "Figure: {";
        if(coordinates.size() > 0) {
            int i;
            for( i = 0; i < coordinates.size() - 1; i++)
                res += coordinates.get(i).toString() + ", ";
            res += coordinates.get(i).toString();
        }

        return res + "}";
    } 

    @Override
    public Iterator<Coordinate> iterator() {
        return new Iterator<Coordinate>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < coordinates.size();
            }

            @Override
            public Coordinate next() {
                if(!hasNext()) throw new NoSuchElementException();
                return coordinates.get(i++);
            }
        };
    }


}
