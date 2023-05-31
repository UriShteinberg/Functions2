public class MultiProduct extends Function{
    private Function[] funcs;
    private int len;

    public MultiProduct(Function... funcs){
        this.funcs = funcs.clone();
        this.len = funcs.length;
        if(len<2){
            System.out.println("exception: return to this!");
        }
        for (int i=0; i < len; ++i){
            this.funcs[i] = funcs[i];
        }
    }
    @Override
    public double valueAt(double x){
        double value = 1;
        for(Function f: funcs){
            value *= f.valueAt(x);
        }
        return value;
    }

    @Override
    public String toString() {
        String res = "(";
        for (int i = 0; i < len; i++) {
            res += funcs[i].toString();
            if (i< funcs.length-1) {
                res += " * ";
            }

        }
        return res + ")";
    }

    @Override
    public Function derivative(){
        Function derivs[] = new Function[funcs.length];
        for (int i = 0; i < len; i++){
            Function multis[] = funcs.clone();
            multis[i] = funcs[i].derivative();
            derivs[i] = new MultiProduct(multis);
        }
        return new MultiSum(derivs);
    }
}
