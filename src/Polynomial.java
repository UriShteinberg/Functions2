public class Polynomial extends Function{

    private final double[] coefficientsArr;

    public Polynomial(double... coefficients){
        this.coefficientsArr = new double[coefficients.length];
        for(int i=0; i<coefficients.length; i++){
            coefficientsArr[i] = coefficients[i];
        }
    }

    @Override
    public double valueAt(double x) {
        int i = 0;
        double sum = 0;
        for(double coefficient:coefficientsArr){
            sum += coefficient*Math.pow(x, i);
            i++;
        }
        return sum;
    }


    public static String toStringConvertor(double coefficient, int pow, boolean firstEntered){
        String str = "";
        if(coefficient>0){
            if(firstEntered)
                str += " + ";
            if((coefficient==(int)coefficient) && (coefficient!=1))
                str += ((int)coefficient);
            else if(coefficient!=1)
                str += (coefficient);
        }
        if(coefficient<0){
            if(firstEntered)
                str += " - ";
            else
                str += "-";
            if((coefficient==(int)coefficient) && (coefficient!=-1))
                str += (Math.abs((int)coefficient));
            else if(coefficient!=-1)
                str += (Math.abs(coefficient));
        }
        if(coefficient!=0)
            str += "x";
        if((pow>1) && (coefficient!=0))
            str += ("^" + pow);
        return str;
    }
    @Override
    public String toString() { // add check if int so convert to int (without a point)
        String strPoly = "";
        boolean firstEntered = false;
        double[] coArr = this.coefficientsArr; //it's a pointer, just for readability
        // handling first coefficient:
        if ((coArr[0]==(int) coArr[0]) && (coArr[0]!=0)){
            strPoly += (String.format("%d", (int) coArr[0]));
            firstEntered = true;
        }
        else if (coArr[0] != 0) {
            strPoly += (String.format("%f", coArr[0]));
            firstEntered = true;
        }
        for (int i=1; i<coArr.length; i++) {
            if (firstEntered)
                strPoly += (toStringConvertor(coArr[i], i, true));
            else{
                if(toStringConvertor(coArr[i], i, false) != "")
                    firstEntered = true;
                strPoly += (toStringConvertor(coArr[i], i, false));
            }
        }
        if(strPoly=="")
            return "(0)";
        return ("(" +strPoly+ ")");
    }



    @Override
    public Polynomial derivative() {
        double[] coArr = this.coefficientsArr; //it's a pointer, just for readability
        if(coArr.length < 2) return new Polynomial(0);
        // from here the polynomial is for sure with pow bigger or equal to 2:
        double[] derivativeArr = new double[coArr.length-1];
        for(int i=1; i<coArr.length; i++){
             derivativeArr[i-1] = i*coArr[i];
        }
        return new Polynomial(derivativeArr);
    }
//    @Override
//    public Polynomial taylorPolynomial(int n){
//        double[] newArr = new double[this.coefficientsArr.length-n];
//        for(int i=0; i<=this.coefficientsArr.length-n; i++){
//            newArr[i] = this.coefficientsArr[i];
//        }
//        return new Polynomial(newArr);
//    }
}
