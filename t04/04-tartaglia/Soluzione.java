import java.util.Scanner;

public class Soluzione {
    
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();
        for (int i = 1; i <= n; i++)
            System.out.print(triangolo(n, i) + " ");
    }

    public static int triangolo (int riga, int colonna) {
        if (colonna == 1 || colonna == riga)
            return 1;
        return triangolo(riga - 1, colonna - 1) + triangolo(riga -1, colonna);
    }
}