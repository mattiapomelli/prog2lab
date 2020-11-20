import java.util.Scanner; 

public class Soluzione {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();

        for (int i = 1; i <= n; i++) {
            if(i % 3 == 0) {
                if(i % 7 == 0)
                    System.out.println("Tico Taco");
                else
                    System.out.println("Tico");
            } else if (i % 7 == 0) {
                System.out.println("Taco");
            } else {
                System.out.println(i);
            }
            
        }
    }
}