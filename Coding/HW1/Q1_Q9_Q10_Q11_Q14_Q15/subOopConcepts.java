package Q1_Q9_Q10_Q11_Q14_Q15;

public class subOopConcepts extends OopConcepts implements OopInterface1 {
    //This refers to the current instance, but super refers to the parent instance.
    int number;
    public subOopConcepts(int number) {
        super();
        this.number = number;
    }

    @Override
    public int add(int num1, int num2) {
        return (num1+num2)*2;
    }
    @Override
    public void greet() {
        System.out.println("Hello World");
    }
}