package src.SingletonMethod;

public class Library {
    private static volatile Library library;

    private Library() {
    }

    public static synchronized Library getLibrary() {
        if(library == null) {
            library = new Library();
        }
        return library;
    }
}
