public class Stamp extends Figure{
    
    public Stamp(int[][] m, int r, int c) {
        for (int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[0].length; j++) {
                if(m[i][j] != 0)
                    coordinates.add(new Coordinate(r + i, c + j));
            }
        }
    }
}
