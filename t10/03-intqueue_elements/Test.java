import java.util.Scanner;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt(), i = 0;

    IntQueue queue = new IntQueue(n);

    while (s.hasNextInt()) {
        queue.enqueue(s.nextInt());
        if((++i) % 3 == 0)
            queue.dequeue(); 
    }
    s.close();

    for(Integer x : queue)
        System.out.println(x);
  }
}