public class Polynomial extends Function{

    private final double[] coefficientsArr;

    public Polynomial(double... coefficients){
        this.coefficientsArr = new double[coefficients.length];
        for(int i=0; i<coefficients.length; i++){
            coefficientsArr[i] = coefficients[i];
        }
    }

    @Override
    public int valueAt(int x) {
        int i = 0, sum = 0;
        for(double coefficient:coefficientsArr){
            sum += Math.pow(coefficient, i);
            i++;
        }
        return sum;
    }

    //
    @Override
    public String toString() { // add check if int so convert to int (without a point)
        String strPoly = "";
        double[] coArr = this.coefficientsArr; //it's a pointer, just for readability
        // handling first coefficient:
        if (coArr[0] == (int) coArr[0])
            strPoly += String.format("%d", (int) coArr[0]);
        else if (coArr[0] != 0)
            strPoly += String.format("%f", coArr[0]);
        // handling second coefficient:
        if (coArr[1] == 1)
            strPoly += String.format(" x");
        if (coArr[1] == -1)
            strPoly += String.format(" -x");
        if ((coArr[1] == (int) coArr[1]) && coArr[1] != 1 && coArr[1] != -1)
            strPoly += String.format(" %dx", (int) coArr[1]);
        else if (coArr[0] != 0)
            strPoly += String.format(" %fx", coArr[1]);
        // handling from pow>2:
        for (int i = 0; i < coArr.length; i++) {
            if (coArr[i] == 0)
                continue;
            if (coArr[i] == 1)
                strPoly += String.format(" x^%d", i);
            else if (coArr[i] == -1)
                strPoly += String.format(" -x^%d", i);
            else {
                if (coArr[i] == (int)(coArr[i]))
                    strPoly += String.format(" %dx^%d", (int)(coArr[i]), i);
                else
                    strPoly += String.format(" %fx^%d", coArr[i], i);
            }
        }
        return strPoly;
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
    @Override
    public Polynomial taylorPolynomial(int n){
        double[] newArr = new double[this.coefficientsArr.length-n];
        for(int i=0; i<=this.coefficientsArr.length-n; i++){
            newArr[i] = this.coefficientsArr[i];
        }
        return new Polynomial(newArr);
    }
}
