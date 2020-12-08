public class Test {    
    public static void main(String[] args) {
        Set<String> set = new UnBoundedSet<>();
        Set<String> set2 = new UnBoundedSet<>();
        Set<String> set3 = new UnBoundedSet<>();
        Set<Integer> setInt = new UnBoundedSet<>();
        setInt.add(2);
        setInt.add(1);
        set.add("Ciao");
        set.add("Mano");
        set.add("Mano");
        set.add("Piede");
        set.remove("Mano");
        set2.add("Piede");
        set2.add("Ciao");
        set3.add("Piede");
        set3.add("Ciao");
        System.out.println(set);
        System.out.println(set2);
        System.out.println(set.equals(set2));
        System.out.println(set3.hashCode());
        System.out.println(set2.hashCode());


    }
}