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

//    @Override
//    public Function derivative(){
//        Function der1 = new MultiProduct(f1.derivative(), f2, funcs);
//        if(len==0){
//            Function der2 = new MultiProduct(f2.derivative(), f1);
//            return new MultiSum(der1, der2);
//        }
//        Function f3 = funcs[0];
//        Function[] funcs2 = new Function[len-1];
//        for(int i=1; i<len; ++i){
//            funcs2[i-1] = funcs[i];
//        }
//        Function temp = new MultiProduct(f2, f3, funcs2);
//        temp = temp.derivative();
//        Function der2 = new MultiProduct(temp, f1);
//        return new MultiSum(der1, der2);
//    }
//

//    public Function[] funcsToFunc(){
//        Function[] all_together = new Function[len+2];
//        all_together[0] = f1;
//        all_together[1] = f2;
//        for(int i=2; i<all_together.length; ++i){
//            all_together[i] = funcs[i-2];
//        }
//        return all_together;
//    }

//    @Override
//    public Function derivative() {
//        Function der1 = f1;
//        Function der2 = f2;
//        Function all_together[] = this.funcsToFunc();
//        Function[] derivs = new Function[all_together.length];
//        for (int i = 0; i < len; i++) {
//            all_together[0] = all_together[i].derivative();
//            for (int j = 1; j < all_together.length; ++j) {
//                if (j == i) continue;
//                else if (j < i) {
//                    all_together[j] = functions[j - 1];
//                } else {
//                    all_together[j] = functions[j];
//                }
//            }
//            Function[] cut_multis = new Function[functions.length];
//            for (int j=0; j<functions.length; ++j){
//                cut_multis[j] = all_together[j+2];
//            }
//            if(i==0){
//                der1 = new MultiProduct(all_together[0], all_together[1]);
//            } else if (i==1) {
//                der2 = new MultiProduct(all_together[0], all_together[1]);
//            }
//            else {
//                derivs[i] = new MultiProduct(all_together[0], all_together[1]);
//            }
//        }
//        return new MultiSum(der1, der2, derivs);
//    }

//    @Override
//    public Function derivative(){
//        Function derivs[] = new Function[funcs.length];
//        for (int i = 0; i < len; i++){
//            Function multis[] = funcs.clone();
//            multis[i] = funcs[i].derivative();
//            derivs[i] = new MultiProduct(multis);
//        }
//        return new MultiSum(derivs);
//    }
//
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