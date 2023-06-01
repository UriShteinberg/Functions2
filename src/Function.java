public abstract class Function {
    public abstract double valueAt(double x);
    @Override
    public abstract String toString();
    public abstract Function derivative();

    public double bisectionMethod(double a, double b, double epsilon){
        double left = a, right = b;
        while (right-left>epsilon){
            double mid = (right+left) / 2;
            if(valueAt(left)*valueAt(mid) > 0){
                left = mid;
            }
            else {
                right = mid;
            }
        }
        return (left+right) / 2;
    }

    public double newtonRaphsonMethod(double a, double epsilon){
        double xk = a;
        double abs_xk = xk>0? xk : -xk;
        while (abs_xk > epsilon){
            xk = xk - (valueAt(xk) / derivative().valueAt(xk));
            abs_xk = xk>0? xk : -xk;
        }
        return xk;
    }

    public abstract Function taylorPolynomial(int n);
}