package Q16_interfaceAbstractClass;

public abstract class Animal {
    //Abstract class can have constructor
    public Animal() {

    }
    //Abstract class can have abstract methods, abstract methods cannot have a body
    public abstract void roar();
    //Abstract class can have concrete methods, and can have different access modifier
    protected void sleep() {
        System.out.println("The animal is sleeping");
    }

}
