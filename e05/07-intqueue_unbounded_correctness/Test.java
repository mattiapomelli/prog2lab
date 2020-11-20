import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
    int nEnc = 0, nDec = 0;
    Scanner s = new Scanner(System.in);

    UnboundedIntQueue queue = new UnboundedIntQueue();

    while (s.hasNextByte()) {
        byte op = s.nextByte();
        if (op == +1) {
            queue.enqueue(++nEnc);
        } else {
            if (nDec == nEnc) break;
            System.out.println(queue.dequeue());
            nDec++;
        }
      }

    System.out.println(queue);
    System.out.println(queue.size());
  }
}