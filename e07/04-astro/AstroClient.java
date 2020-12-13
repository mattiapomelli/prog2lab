import java.util.Scanner;

public class AstroClient {
    
    public static void main(String[] args) {
        AstronomicalSystem system = new AstronomicalSystem();
        Scanner s = new Scanner(System.in);

        while(s.hasNext()) {
            char type = s.next().charAt(0);
            String name = s.next();
            int x = s.nextInt();
            int y = s.nextInt();
            int z = s.nextInt();
            if(type == 'P')
                system.addBody(new Planet(name, new Point(x, y, z)));
            else
                system.addBody(new Star(name, new Point(x, y, z)));
        }

        s.close();

        for(int i = 0; i < Integer.parseInt(args[0]); i++) {
            system.evolve();
        }
        
        System.out.println(system);   
    }
}
