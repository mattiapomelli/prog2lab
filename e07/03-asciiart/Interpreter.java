import java.util.Scanner;

public class Interpreter {

    // BitMap map;
    

    // public Interpreter() {
    //     map = new BitMap(1, 1);
    //     matrices = new int[1000][][];
    // }

    // public void readMatrix(int index, int m[][]) {
    //     matrices[]
    // }
    
    public static void main(String[] args) {
        BitMap map = null;
        int[][][] matrices = new int[1000][][];
        
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            char command = s.next().charAt(0);
            switch(command) {
                case 'n':
                    map = new BitMap(s.nextInt(), s.nextInt());
                    break;
                case 'c':
                    map.turnAllOff();
                    break;
                case 'i':
                    map.invert();
                    break;
                case 'x':
                    map.turnPixelOn(s.nextInt(), s.nextInt());
                    break;
                case 'o':
                    map.turnPixelOff(s.nextInt(), s.nextInt());
                    break;
                case 'v':
                    map.draw(new VerticalSegment(s.nextInt(), s.nextInt(), s.nextInt()));
                    break;
                case 'h':
                    map.draw(new HorizontalSegment(s.nextInt(), s.nextInt(), s.nextInt()));
                    break;
                case 'r':
                    map.draw(new Rectangle(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt()));
                    break;
                case 's':
                    int index = s.nextInt();
                    int w = s.nextInt();
                    int h = s.nextInt();
                    int[][] m = new int[w][h];    
                    for(int i = 0; i < w; i++)
                        for(int j = 0; j < h; j++)
                            m[i][j] = s.nextInt();
                    
                    matrices[index] = m;

                    break;
                case 'd':
                    map.draw(new Stamp(matrices[s.nextInt()], s.nextInt(), s.nextInt()));
                    break;
                case 'p':
                    System.out.println(map);
                    break;
                default:
                    System.out.println("default");
            }
        }

        s.close();
    }
}
