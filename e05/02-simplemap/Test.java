public class Test {
    
    public static void main(String[] args) {
        SimpleMap m = new SimpleMap();

        m.put("k1", 3);
        m.put("k1", 2);
        m.put("k2", 4);
        m.put("k3", 5);
        System.out.println(m);
        Integer a = m.remove("k1");
        System.out.println(m);
        System.out.println(a);

        Integer b = m.get("k3");

        System.out.println(b);
    }
}