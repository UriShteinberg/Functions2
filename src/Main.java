public class Main {
    public static void main(String[] args) {

        Function a = new Constant(5);
        Function b = new Constant(-29);
        Function c = new Constant(73.53);

        Function abc = new MultiSum(a, b, c);
        System.out.println("abc");
        System.out.println(abc.valueAt(3.5));
        System.out.println(abc);
        System.out.println(abc.derivative().toString());
        System.out.println();

        abc = new MultiProduct(a, b, c);
        System.out.println("a*b*c");
        System.out.println(abc.valueAt(3.5));
        System.out.println(abc);
        System.out.println(abc.derivative().toString());
        System.out.println();

        Function ab = new Sum(a, b);
        System.out.println("ab");
        System.out.println(ab.valueAt(3.5));
        System.out.println(ab);
        System.out.println(ab.derivative().toString());
        System.out.println();

        Function a_minus_b = new Difference(a, b);
        System.out.println("a-b");
        System.out.println(a_minus_b.valueAt(3.5));
        System.out.println(a_minus_b);
        System.out.println(a_minus_b.derivative().toString());
        System.out.println();

        ab = new Product(a, b);
        System.out.println("a*b");
        System.out.println(ab.valueAt(3.5));
        System.out.println(ab);
        System.out.println(ab.derivative().toString());
        System.out.println();

        Function a_divide_b = new Quotient(a, b);
        System.out.println("a/b");
        System.out.println(a_divide_b.valueAt(3.5));
        System.out.println(a_divide_b);
        System.out.println(a_divide_b.derivative().toString());
        System.out.println();
    }
}