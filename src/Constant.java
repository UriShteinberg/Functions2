public class Constant extends Function {
    private double con;
    public Constant(double con) {
        this.con = con;
    }

    @Override
    public double valueAt(double x) {
        return this.con;
    }

    @Override
    public String toString() {
        if (con==(int)(con))
            return ("(" + (int)(con) + ")");
        else
            return ("(" + this.con + ")");
    }

    @Override
    public Function derivative() {
        return new Constant(0);
    }
}
