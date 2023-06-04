public class MultiProduct extends Function {
    private Function[] functions;

    public MultiProduct(Function... functions) {
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
    public double valueAt(double x) {
        double value = 1;
        for (Function f : functions)
            value *= f.valueAt(x);
        return value;
    }

    @Override
    public String toString() {
        String res = "(";
        for (int i = 0; i < this.functions.length; i++) {
            res += functions[i].toString();
            if (i < functions.length - 1) {
                res += " * ";
            }
        }
        return res + ")";
    }

    /**
     *the function computes the multi-product derivative, general idea:
     *  if the function is a multiplication of two functions just skip to else and use
     * the regular formula d/dx [ f(x) * g(x) ] = f'(x) * g(x) + g'(x) * f(x)
     * otherwise it
     * @return
     */
    @Override
    public Function derivative(){
        int len = this.functions.length;
        Function [] functionsToSum = new Function[len];
        Function [] functionsToMultiply = new Function[len];
        if(len>2) {
            for(int i = 0; i < len; i++) {
                for(int j = 0; j < len; j++) {
                    if (j == i){
                        continue;
                    }
                    functionsToMultiply[j] = this.functions[j];
                }
                Function []tempArr = new Function[i];
                for(int k=0; k<i; k++){
                    tempArr[k] = functionsToMultiply[k];
                }
                for(int t=1; t<=i; t++){
                    functionsToMultiply[t] = tempArr[t-1];
                }
                functionsToMultiply[0] = this.functions[i].derivative();
                functionsToSum[i] = new MultiProduct(functionsToMultiply);
            }
            return new MultiSum(functionsToSum);
        }
        else {
            Function der1 = new MultiProduct(this.functions[0].derivative(), this.functions[1]);
            Function der2 = new MultiProduct(this.functions[1].derivative(), this.functions[0]);
            return new MultiSum(der1, der2);
            }
    }
}