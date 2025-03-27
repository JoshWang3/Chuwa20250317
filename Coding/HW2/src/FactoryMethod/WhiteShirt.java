package src.FactoryMethod;

public class WhiteShirt implements Shirt{
    int cost = 10;

    @Override
    public void makeShirt() {
        System.out.println("Creating a White Shirt");
    }

}
