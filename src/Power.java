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
        double z = y;
        for (int i=1; i<pow; ++i){
            y*=z;
        }
        return y;
    }

    @Override
    public String toString() {
        String res = f.toString();
        if(pow==0){
            return (new Constant(1).toString());
        } else if (pow==1) {
            return res;
        }
        res+="^"+pow;
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
