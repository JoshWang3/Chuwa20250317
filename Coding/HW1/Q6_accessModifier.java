public class Q6_accessModifier {
    //Access modifier defines the accessibility of fields, class, methods. Public means any class can
    //get access to it, private means only the current class can get access to it, protected means any
    //class in the package and subclasses can get access to it. Default means classes in the package can
    //get access to it.
    private String myWord = "Hello";
    protected String getMyWord() {
        return this.myWord;
    }
    void setWord(String word) {
        this.myWord = word;
    }
}
