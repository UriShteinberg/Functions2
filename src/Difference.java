public class Difference extends MultiSum{
    private Function f;
    private Function g;

    public Difference(Function f, Function g){
        super(f, new Negation(g));

    }

    @Override
    public double valueAt(double x){
        return super.valueAt(x);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public Function derivative(){
        return super.derivative();
    }
}
