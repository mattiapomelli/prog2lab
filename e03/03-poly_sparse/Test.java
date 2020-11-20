public class Test {
    public static void main(String[] args) {
        Poly p = new Poly(2, 3);
        Poly q = new Poly(3, 3);
        Poly r = new Poly(5, 2);
        System.out.println(p + "  coeff: " +  p.coeff(3) + "  degree:  " + p.degree());
        
        Poly sum = p.add(q);
        System.out.println(sum + "  coeff: " + "  degree:  " + sum.degree());
        sum = sum.add(r);
        System.out.println(sum + "  coeff: " + "  degree:  " + sum.degree());
        Poly minus = sum.minus();
        System.out.println(minus + "  coeff: " + "  degree:  " + minus.degree());
        
        Poly sub = sum.sub(p);
        System.out.println(sub + "  coeff: " +"  degree:  " + sub.degree());
        
        Poly prod = p.mul(r);
        System.out.println(prod + "  coeff: " + "  degree:  " + prod.degree());


    }
}