public class Client {
    
    public static void main(String[] args) {
        BitMap map = new BitMap(4, 5);
        
        System.out.println(map);
        
        HorizontalSegment seg = new HorizontalSegment(5, 2, 1);
        VerticalSegment vseg = new VerticalSegment(3, 2, 1);
        System.out.println(vseg);
        
        map.draw(seg);
        map.draw(vseg);
        System.out.println(map);
    }
}
