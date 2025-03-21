package Q16_interfaceAbstractClass;

public interface Feed {
    //Interface cannot have constructor, cannot be instantiated

    //All fields are implicitly public, static, final
    String habitat = "Mountain";

    //All methods are public
    void hunt();

    default void run() {
        System.out.println("The animal is running");
    }
}
