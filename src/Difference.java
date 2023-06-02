public class Difference extends Function{
    private Function f;
    private Function g;

    public Difference(Function f, Function g){
        this.f = f;
        this.g = g;

    }

    @Override
    public double valueAt(double x){
        return f.valueAt(x) - g.valueAt(x);
    }

    @Override
    public String toString(){
        String res ="(" +  f.toString() + " - " + g.toString() + ")";
        return res;
    }

    @Override
    public Function derivative() {
        return new Difference(f.derivative(), g.derivative());
    }
}
