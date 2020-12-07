import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
    int i = 0;
    Scanner s = new Scanner(System.in);

    UnboundedIntQueue queue = new UnboundedIntQueue();

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