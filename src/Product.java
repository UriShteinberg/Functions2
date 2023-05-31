public class Product extends MultiProduct{
    private Function f;
    private Function g;

    public Product(Function f, Function g){
        super(f, g);
    }

    @Override
    public double valueAt(double x){
        return super.valueAt(x);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public Function derivative(){
        return super.derivative();
    }
}
