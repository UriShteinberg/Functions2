public class MultiSum extends Function{
    private Function[] functions;

    public MultiSum(Function... functions){
        if (functions.length < 2)
            this.functions = new Function[]{new Constant(0)};
        else {
            this.functions = new Function[functions.length];
            for (int i = 0; i < functions.length; i++)
                if (functions[i] == null)
                    this.functions[i] = new Constant(0);
                else
                    this.functions[i] = functions[i];
        }
    }
    @Override
    public double valueAt(double x){
        double value = 0;
        for(Function f: functions){
            value += f.valueAt(x);
        }
        return value;
    }

    @Override
    public String toString() {
        String res = "(";
        for (int i = 0; i < this.functions.length; i++) {
            res += this.functions[i].toString();
            if (i< (this.functions.length-1)) {
                res += " + ";
            }
        }
        return res + ")";
    }

    @Override
    public Function derivative(){
        Function[] derivs = new Function[functions.length];
        for (int i = 0; i < functions.length; i++){
            derivs[i] = functions[i].derivative();
        }
        return new MultiSum(derivs);
    }
}