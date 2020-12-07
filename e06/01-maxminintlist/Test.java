import java.util.Scanner;
import java.util.Iterator;

public class Test {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        MaxMinIntList list = new MaxMinIntList();

        while(s.hasNextInt()) {
            list.addEl(s.nextInt());
        }

        Iterator<Integer> it = list.elements();

        if(it.hasNext()) System.out.print(it.next());
        while(it.hasNext()) {
            System.out.print(", " + it.next());
        }
        System.out.println();

        System.out.println(list.min());
        System.out.println(list.max());
        s.close();
    }
}