package src.FactoryMethod;

public class ShirtFactory {
    public static Shirt produceShirt(String type) {
        if(type.equals("white")) {
            return new WhiteShirt();
        } else {
            return new BlackShirt();
        }
    }
}
