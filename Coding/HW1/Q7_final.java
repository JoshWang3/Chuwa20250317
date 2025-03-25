public final class Q7_final {
    //Final means after we create it, we cannot modify it. A final class means it cannot be inherited.
    //A final variable means we cannot reassign it. A final methods means we cannot override it.
    final int number = 0;
    //error: cannot reassign
//    void setNumber(int num) {
//        this.number = num;
//    }
    final int doubleNumber() {
        return number*2;
    }
}
