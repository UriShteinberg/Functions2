import java.math.BigDecimal;
public abstract class Function {

    public abstract double valueAt(double x);

    @Override
    public abstract String toString();

    public abstract Function derivative();

    /**
     *
     * @param a: lower boundary
     * @param b: higher boundary
     * @param epsilon: the epsilon distance from the answer
     * @return the root (in proximity < epsilon)
     */

    public double bisectionMethod(double a, double b, double epsilon) {
        double left = a, right = b;
        while (right - left > epsilon) {
            double mid = (right + left) / 2;
            if (valueAt(left) * valueAt(mid) > 0) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return (left + right) / 2;
    }

    /**
     *
     * @param a: lower boundary
     * @param b: higher boundary
     * @return the root (in proximity < default epsilon = 10^-5)
     */
    public double bisectionMethod(double a, double b) {
        return this.bisectionMethod(a, b, Math.pow(10, -5));
    }

    /**
     *
     * @param a: the dot from where to start the search
     * @param epsilon: the epsilon distance from the answer
     * @return the root (in proximity < epsilon)
     */
    public double newtonRaphsonMethod(double a, double epsilon) {
        double xk = a;
        while (Math.abs(valueAt(xk)) >= epsilon) {
            xk = xk - (valueAt(xk) / derivative().valueAt(xk));
        }
        return xk;
    }

    /**
     *
     * @param a: the dot from where to start the search
     * @return: the root (in proximity < default epsilon = 10^-5)
     */
    public double newtonRaphsonMethod(double a) {
        return this.newtonRaphsonMethod(a, Math.pow(10, -5));
    }

    /**
     *
     * @param n: the rank of the Taylor polynomial
     * @return: a new polynomial function - the Taylor (Maclaurin) polynomial of the function
     */
    public Function taylorPolynomial(int n) {
        double []coefficientArr = new double[n+1];
        Function function = this;
        double factorial = 1;
        for(int i=0; i<=n; i++){
            coefficientArr[i] = (function.valueAt(0))/factorial;
            function = function.derivative();
            factorial *= (i+1);
        }
        return new Polynomial(coefficientArr);
    }
}