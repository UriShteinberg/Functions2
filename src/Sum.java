public class Sum extends MultiSum{
    private Function f;
    private Function g;

    public Sum(Function f, Function g){
        super(f, g);
    }

    @Override
    public double valueAt(double x){
        //return f.valueAt(x) + g.valueAt(x);
        return super.valueAt(x);
    }

    @Override
    public String toString(){
        //return ("(" + f.toString() + ") + (" + g.toString() + ")");
        return super.toString();
    }

    @Override
    public Function derivative(){
        //return new Sum(f.derivative(), g.derivative());
        return super.derivative();
    }
}