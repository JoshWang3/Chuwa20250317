public class Q8_static {
    //Static keywords means it belongs to a class not a specific object. A static class cannot be
    //instantiated. A static variable is shared by all instances of the class. A static method belongs
    //to the class but not a instance and cannot access to the non-static variables or methods directly.
   //Only a nested class can be static
    static int num1 = 0;
    static int num2 = 2;
    static int doubleNum(int num) {
        return 2*num;
    }
    static class Nested {

    }
}
