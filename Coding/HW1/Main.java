import Q1_Q9_Q10_Q11_Q14_Q15.OopConcepts;
import Q1_Q9_Q10_Q11_Q14_Q15.subOopConcepts;

public class Main {
    static  {
        System.out.println("Main class static method is called");
    }
    public static void main(String[] args) {
        //Q1
        OopConcepts oop = new OopConcepts();
        System.out.println(oop.getSecret());
        oop.setSecret(10);
        System.out.println(oop.getSecret());
        System.out.println(oop.add(1, 2));
        System.out.println(oop.add(1.5, 2.5));
        subOopConcepts subOop = new subOopConcepts(2);
        System.out.println(subOop.add(1, 2));
        subOop.greet();

        //Q2
        Q2_WrapperClass wc = new Q2_WrapperClass();
        wc.addNum("124");
        System.out.println(wc.getList().getFirst());

        //Q4
        String s1 = "String";
        String s2 = "String";
        Q4_stringPool sp = new Q4_stringPool();
        System.out.println(sp.compare(s1, s2));
        s1.concat("String");
        System.out.println(s1);

        //Q8
        Q8_static.Nested nested = new Q8_static.Nested();
        System.out.println(Q8_static.num1);
        System.out.println(Q8_static.doubleNum(2));

        //Q8
        Q12_equalHashcode one = new Q12_equalHashcode("Tony", 12);
        Q12_equalHashcode another = new Q12_equalHashcode("Tony", 12);
        System.out.println(one.equals(another));
        System.out.println(one.hashCode() == another.hashCode());

        //Q13
        //Java load sequence refers to the order in which Java loads, links and initialize classes.
        //Class loading is lazy, they are loaded only when they are first referenced. A parent class
        //is always load before child.
        System.out.println("Main method is called");

    }
}

