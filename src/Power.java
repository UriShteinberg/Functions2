public class Power extends Function{
    private Function f;
    private int pow;


    public Power(Function f, int pow){
        this.f = f;
        this.pow = pow;
    }
    @Override
    public double valueAt(double x) {
        double y = f.valueAt(x);
        for (int i=0; i<pow; ++i){
            y*=y;
        }
        return y;
    }

    @Override
    public String toString() {
        String res = f.toString()+"^"+pow;
        return res;
    }

    @Override
    public Function derivative() {
        Function der = f.derivative();
        Function lesser = new Power(f, pow-1);
        Function n = new Constant(pow);
        return new MultiProduct(n, lesser, der);
    }
}
