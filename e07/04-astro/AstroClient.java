import java.util.Iterator;
import java.util.Scanner;

public class AstroClient {
    
    public static void main(String[] args) {
        AstronomicalSystem system = new AstronomicalSystem();

        system.addBody(new Planet("Marte", new Vector(0, 1, 0)));
        system.addBody(new Planet("Giove", new Vector(1, 0, 0)));
        system.addBody(new Planet("Saturno", new Vector(0, 0, 1)));
        system.addBody(new Star("Sole", new Vector(0, 0, 0)));
        // system.addBody(new Planet("Saturno2", new Vector(0, 0, 1)));
        // system.addBody(new Star("Sole2", new Vector(0, 0, 0)));
        // system.addBody(new Star("Sole3", new Vector(0, 0, 0)));
        // system.addBody(new Star("Sole4", new Vector(0, 0, 0)));
        // system.addBody(new Planet("Saturno3", new Vector(0, 0, 1)));
        // system.addBody(new Star("Sole5", new Vector(0, 0, 0)));
        // system.addBody(new Star("Sole6", new Vector(0, 0, 0)));
        // system.addBody(new Star("Sole7", new Vector(0, 0, 0)));
        // system.addBody(new Planet("Saturno4", new Vector(0, 0, 1)));
        

        Scanner s = new Scanner(System.in);

        // while(s.hasNext()) {
        //     char type = s.next().charAt(0);
        //     String name = s.next();
        //     int x = s.nextInt();
        //     int y = s.nextInt();
        //     int z = s.nextInt();
        //     if(type == 'P')
        //         system.addBody(new Planet(name, new Vector(x, y, z)));
        //     else
        //         system.addBody(new Star(name, new Vector(x, y, z)));
        // }
        // s.close();

            // Integer.parseInt(args[0])

        System.out.println(system);   
        for(int i = 0; i < 21; i++) {
            system.evolve();
            // System.out.println(i);
        }
        System.out.println(system);   


        // Iterator<Planet> it = system.planets();
        // while(it.hasNext()) {
        //     System.out.println(it.next());
        // }
    }
}
