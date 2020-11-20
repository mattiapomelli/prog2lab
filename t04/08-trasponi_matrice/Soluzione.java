import java.util.Scanner;

public class Soluzione {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // lettura input righe e colonne
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        int[][] A = new int[N][M];

        // lettura input matrice
        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                A[r][c] = s.nextInt();

        int[][] T = new int[M][N];

        // trasponi matrice
        for (int r = 0; r < M; r++)
            for (int c = 0; c < N; c++)
                T[r][c] = A[c][r];

        // stampa
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) 
                System.out.print(T[r][c] + " ");
            System.out.println();
        }


        s.close();
    }
}
