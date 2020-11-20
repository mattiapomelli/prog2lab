import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();

        for (int i = 0; i < 8; i++) {
            for(int k = 0; k < n; k++) {
                for (int j = 0; j < 8; j++) {
                    if((i + j)%2 == 0)
                        for(int l = 0; l < n; l++)
                            System.out.print("-");
                    else
                        for(int l = 0; l < n; l++)
                            System.out.print("#");
                }
                System.out.println();
            }
        }
    }
}