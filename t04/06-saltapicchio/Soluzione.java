import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(args[0]);
        int[] v = new int[n];
        int[] diff = new int[n-1];

        for (int i = 0; i < n; i++) {
            v[i] = s.nextInt();
        }

        for (int i = 0; i < n-1; i++) {
            int sub = v[i+1] - v[i];
            sub = sub < 0 ? sub*-1 : sub;
            diff[i] = sub;
        }

        boolean saltapicchio = true;
        for (int i = 1; i < n; i++) {
            boolean trovato = false;
            for (int j = 0; j < n-1; j++) {
                if(diff[j] == i)
                    trovato = true;
            }
            if(!trovato) {
                saltapicchio = false;
                break;
            }
        }

        if (saltapicchio) 
            System.out.println("saltapicchio");

        s.close();
    }
}