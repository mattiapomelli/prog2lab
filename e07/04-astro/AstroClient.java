import java.util.Scanner;

public class AstroClient {
    
    public static void main(String[] args) {
        AstronomicalSystem system = new AstronomicalSystem();

        // system.addBody(new Planet("Marte", new Vector(0, 1, 0)));
        // system.addBody(new Planet("Giove", new Vector(1, 0, 0)));
        // system.addBody(new Planet("Saturno", new Vector(0, 0, 1)));
        // system.addBody(new Star("Sole", new Vector(0, 0, 0)));
        

        Scanner s = new Scanner(System.in);

        while(s.hasNext()) {
            char type = s.next().charAt(0);
            String name = s.next();
            int x = s.nextInt();
            int y = s.nextInt();
            int z = s.nextInt();
            if(type == 'P')
                system.addBody(new Planet(name, new Vector(x, y, z)));
            else
                system.addBody(new Star(name, new Vector(x, y, z)));
        }
        s.close();

        //System.out.println(system);   
        for(int i = 0; i < Integer.parseInt(args[0]); i++) {
            system.evolve();
        }
        System.out.println(system);   
    }
}
