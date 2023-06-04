public class MultiProduct extends Function{
    private Function f1;
    private Function f2;
    private Function[] funcs;
    private int len;

    public MultiProduct(Function f1, Function f2, Function... funcs){
        this.f1 = f1;
        this.f2 = f2;
        this.funcs = funcs;
        this.len = funcs.length;
        for (int i=0; i < len; ++i){
            this.funcs[i] = funcs[i];
        }
    }
    @Override
    public double valueAt(double x){
        double value = 1;
        value *= f1.valueAt(x);
        value *= f2.valueAt(x);
        for(Function f: funcs){
            value *= f.valueAt(x);
        }
        return value;
    }

    @Override
    public String toString() {
        String res = "(";
        res += f1.toString();
        res += " * ";
        res += f2.toString();
        if(len>0) res += " * ";
        for (int i = 0; i < len; i++) {
            res += funcs[i].toString();
            if (i< funcs.length-1) {
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
public Function[] funcsToFunc(){
    Function[] all_together = new Function[len+2];
    all_together[0] = f1;
    all_together[1] = f2;
    for(int i=2; i<len+2; ++i){
        all_together[i] = funcs[i-2];
    }
    return all_together;
}
//    @Override
//public Function derivative() {
//    Function der1 = f1;
//    Function der2 = f2;
//    Function all_together[] = this.funcsToFunc();
//    Function[] derivs = new Function[len+2];
//    for (int i = 0; i < len; i++) {
//        Function[] multis = all_together; // find alternative to the clone!!
//        multis[0] = all_together[i].derivative();
//        for (int j = 1; j < len+2; ++j) {
//            if (j == i) continue;
//            else if (j < i) {
//                multis[j] = all_together[j - 1];
//            } else {
//                multis[j] = all_together[j];
//            }
//        }
//        Function cut_multis[]= new Function[len];
//        for (int j=0; j<len; ++j){
//            cut_multis[j] = multis[j+2];
//        }
//        if(i==0){
//            der1 = new MultiProduct(multis[0], multis[1], cut_multis);
//        } else if (i==1) {
//            der2 = new MultiProduct(multis[0], multis[1], cut_multis);
//        }
//        else {
//            derivs[i] = new MultiProduct(multis[0], multis[1], cut_multis);
//        }
//    }
//    if(len==0){
//        return new MultiSum(der1, der2);
//    }
//    else {
//        Function newArr[] = new Function[derivs.length+2];
//        return new MultiSum(der1, der2, derivs);
//    }
//}


    @Override
    public Function derivative(){
        Function der1 = new MultiProduct(f1.derivative(), f2, funcs);
        Function der2 = new MultiProduct(f2.derivative(), f1, funcs);
        Function[] derivs = new Function[funcs.length];
        for (int i = 0; i < len; i++){
            Function[] multis = funcs.clone(); // find alternative to the clone!!
            multis[0] = funcs[i].derivative();
            for (int j=1; j < len; ++j){
                if(j==i) continue;
                else if (j<i) {
                    multis[j] = funcs[j-1];
                }
                else {
                    multis[j] = funcs[j];
                }
            }
            derivs[i] = new MultiProduct(f1, f2, multis);
        }
        return new MultiSum(der1, der2, derivs);
    }

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

}