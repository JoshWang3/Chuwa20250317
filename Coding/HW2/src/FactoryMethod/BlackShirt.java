package src.FactoryMethod;

public class BlackShirt implements Shirt{
    int cost = 12;

    @Override
    public void makeShirt() {
        System.out.println("Creating a black shirt");
    }
}
