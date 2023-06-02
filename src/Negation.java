public class Negation extends Function{
    private Function f;

    public Negation(Function f){
        this.f = f;
    }

    @Override
    public double valueAt(double x){
        return f.valueAt(x)*-1;
    }

    @Override
    public String toString(){
        return "(-" + f.toString() + ")";
    }

    @Override
    public Function derivative(){
        return new Negation(f.derivative());
    }
}