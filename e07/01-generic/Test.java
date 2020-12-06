public class Test {    
    public static void main(String[] args) {
        BoundedSet<Integer> set = new BoundedSet<>(5);
        set.insert(4);
        System.out.println(set);

    }
}