public class Q4_stringPool {
    //String pool is a special memory place in java heap that stores unique string literals.
    //When we want to create a new string, java checks the string pool first, it the string
    //exits, return the existing reference otherwise create a new string. Since strings are
    //immutable and can be reused, string pool can save memory and time. String is immutable,
    // once it's created we cannot modify it.

    public boolean compare(String s1, String s2) {
        return s1 == s2;
    }
}
