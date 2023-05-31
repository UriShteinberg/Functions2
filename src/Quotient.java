public class Quotient extends Function{
    private Function f;
    private Function g;

    public Quotient(Function f, Function g){
            this.f = f;
            this.g = g;
    }

    @Override
    public double valueAt(double x){
        double gx = g.valueAt(x);
//        if (gx==0) {
//            System.out.println("exception: return to this!");
//        }
        return f.valueAt(x) / gx;
    }

    @Override
    public String toString(){
        return "(" + f.toString() + " / " + g.toString() + ")";
    }


    @Override
    public Function derivative(){
        Function ng = new MultiProduct(g, g);
        Function nf = new Difference(new MultiProduct(f.derivative(), g), new MultiProduct(f, g.derivative()));
        return new Quotient(nf, ng);
    }

}
