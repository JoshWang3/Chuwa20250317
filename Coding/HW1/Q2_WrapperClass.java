import java.util.ArrayList;
import java.util.List;

public class Q2_WrapperClass {
    //Wrapper class is to wrap primitive data into objects. Collections don't accept primitives as
    //value, so we need wrapper class. And there are some built-in functions we can use.
    //Wrapper class example:
    List<Integer> wrapperList = new ArrayList<>();
    public List<Integer> getList() {
        return this.wrapperList;
    }
    public void addNum(String num) {
        wrapperList.add(Integer.parseInt(num));
    }
}
