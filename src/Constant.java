public class Constant extends Function{
    private double con;

    public Constant(double con){
        this.con = con;
    }

    @Override
    public double valueAt(double x){
        return con;
    }

    @Override
    public String toString(){
        return ("("+con+")");
    }

    @Override
    public Function derivative(){
        return new Constant(0);
    }
}

