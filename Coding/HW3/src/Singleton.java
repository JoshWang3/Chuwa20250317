public class Singleton {
    public static volatile Singleton instance;

    private Singleton() {}

    //use synchronized keyword to make thread safe, but lower performance
    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    //double check lock using class object lock and make the instance volatile
    public static Singleton anotherGetInstance() {
        if(instance == null) {
            synchronized(Singleton.class) {
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
