package Q1_Q9_Q10_Q11_Q14_Q15;

public class OopConcepts {
    //Encapsulation is one of the important OOP concepts of Java to avoid unwanted access to data, we usually
    //use keyword private, setters and getters to realize it.
    //Encapsulation example:
    private int secret = 2;
    public int getSecret() {
        return this.secret;
    }
    public void setSecret(int secret) {
        this.secret = secret;
    }

    //Polymorphism means different types, in Java we have two kinds of polymorphism, one is in compile time,
    //overload, which means we allow multiple methods sharing the same name but with different inputs.
    //Another is override, we can override a method defined in its parent class.

    //Method signatures include method name and parameter list. Overloading happens when methods have
    //same name but different parameter list. Overriding allows to override a method in parent class
    //that has the same signature.

    //Polymorphism example:
    public int add(int num1, int num2) {
        return num1+num2;
    }
    public double add(double num1, double num2) {
        return num1+num2;
    }

    //Inheritance is like a parent and child relationship, a class can inherit from another class using
    //keyword extends Java only supports single inheritance, but we can make multiple inheritance using
    //interfaces.


}
