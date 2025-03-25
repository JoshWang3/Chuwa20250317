package Q16_interfaceAbstractClass;

public class Lion extends Animal implements Feed{
    @Override
    public void roar() {
        System.out.println("The lion is roaring");
    }

    @Override
    public void hunt() {
        System.out.println("The lion is hunting");
    }



}
