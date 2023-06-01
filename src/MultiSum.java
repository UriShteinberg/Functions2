public class MultiSum extends Function{
    private Function f1;
    private Function f2;
    private Function[] funcs;
    private int len;

    public MultiSum(Function f1, Function f2, Function... funcs){
        this.funcs = funcs.clone();
        this.len = funcs.length;

        this.f1 = f1;
        this.f2 = f2;
        for (int i=0; i < len; ++i){
            this.funcs[i] = funcs[i];
        }
    }
    @Override
    public double valueAt(double x){
        double value = 0;
        value += f1.valueAt(x);
        value += f2.valueAt(x);
        for(Function f: funcs){
            value += f.valueAt(x);
        }
        return value;
    }

    @Override
    public String toString() {
        String res = "(";
        res += f1.toString();
        res += " + ";
        res += f2.toString();
        if(len>0) res += " + ";
        for (int i = 0; i < len; i++) {
            res += funcs[i].toString();
            if (i< funcs.length-1) {
                res += " + ";
            }

        }
        return res + ")";
    }

    @Override
    public Function derivative(){
        Function der1 = f1.derivative();
        Function der2 = f2.derivative();
        Function derivs[] = new Function[funcs.length];
        for (int i = 0; i < len; i++){
            derivs[i] = funcs[i].derivative();
        }

        return new MultiSum(der1, der2, derivs);
    }
}
