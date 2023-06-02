public class Difference extends MultiSum{
    private Function f;
    private Function g;

    public Difference(Function f, Function g){
        super(f, g);

    }

    @Override
    public double valueAt(double x){
        return f.valueAt(x) - g.valueAt(x);
    }

    @Override
    public String toString(){
        String res = f.toString() + " - " + g.toString();
    }

    @Override
    public Function derivative() {
        return new Difference(f.derivative(), g.derivative());
    }
}