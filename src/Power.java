public class Power extends Function{
    private Function f;
    private int pow;


    public Power(Function f, int pow){
        this.f = f;
        this.pow = pow;
    }
    @Override
    public double valueAt(double x) {
        if(f.valueAt(x)==0) return 0;
        else if (f.valueAt(x)==1) return 1;
        return Math.pow(f.valueAt(x), this.pow);
    }

    @Override
    public String toString() {
        String res = f.toString();
//        if(pow==0){
//            return (new Constant(1).toString());
//        }
        res=("(" +res+ "^" +pow+ ")");
        return res;
    }

    @Override
    public Function derivative() {
        if(this.pow==1)
            return this.f.derivative();
        else {
            Function der = f.derivative();
            Function lesser = new Power(f, pow - 1);
            Function n = new Constant(pow);
            return new MultiProduct(n, lesser, der);
        }
    }
}