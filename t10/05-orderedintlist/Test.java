import java.util.Iterator;
import java.util.Scanner;

public class Test {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        OrderedIntList list = new OrderedIntList();

        while (s.hasNextInt()) {
            list.addEl(s.nextInt());
        }
        s.close();

        Iterator<Integer> it = list.smallToBig();
        if(it.hasNext()) System.out.print(it.next());
        while(it.hasNext()) {
            System.out.print(", " + it.next());
        }
        System.out.println();
        
        it = list.bigToSmall();
        if(it.hasNext()) System.out.print(it.next());
        while(it.hasNext()) {
            System.out.print(", " + it.next());
        }
        System.out.println();
    }
}