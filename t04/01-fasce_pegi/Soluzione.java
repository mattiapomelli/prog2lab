import java.util.Scanner;  

public class Soluzione {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int eta = s.nextInt();
        s.close();

        int fascia;

        if(eta < 7)
            fascia = 3;
        else if (eta < 12)
            fascia = 7;
        else if (eta < 16)
            fascia = 12;
        else if (eta < 18)
            fascia = 16;
        else
            fascia = 18;

        System.out.println("fascia " + fascia);
    }
}
