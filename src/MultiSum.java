public class MultiSum extends Function{
    private Function[] funcs;
    private int len;

    public MultiSum(Function... funcs){
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
        double value = 0;
        for(Function f: funcs){
            value += f.valueAt(x);
        }
        return value;
    }

    @Override
    public String toString() {
        String res = "(";
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
        Function derivs[] = new Function[funcs.length];
        for (int i = 0; i < len; i++){
            derivs[i] = funcs[i].derivative();
        }

        return new MultiSum(derivs);
    }
}
