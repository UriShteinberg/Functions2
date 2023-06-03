public class Quotient extends Function{
    private Function f;
    private Function g;

    public Quotient(Function f, Function g){
        this.f = f;
        this.g = g;
    }

    @Override
    public double valueAt(double x){
        return (f.valueAt(x) / g.valueAt(x));
    }

    @Override
    public String toString(){
        return "(" + f.toString() + " / " + g.toString() + ")";
    }


    @Override
    public Quotient derivative(){
        Function ng = new Power(g, 2);
        Function nf = new Difference(new MultiProduct(f.derivative(), g), new MultiProduct( g.derivative(), f));
        return new Quotient(nf, ng);
    }

}