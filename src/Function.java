import java.math.BigDecimal;
public abstract class Function {
    public abstract double valueAt(double x);

    @Override
    public abstract String toString();

    public abstract Function derivative();

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

    public double bisectionMethod(double a, double b) {
        return this.bisectionMethod(a, b, Math.pow(10, -5));
    }


    public double newtonRaphsonMethod(double a, double epsilon) {
        double xk = a;
        while (Math.abs(valueAt(xk)) >= epsilon) {
            xk = xk - (valueAt(xk) / derivative().valueAt(xk));
        }
        return xk;
    }

    public double newtonRaphsonMethod(double a) {
        return this.newtonRaphsonMethod(a, Math.pow(10, -5));
    }

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