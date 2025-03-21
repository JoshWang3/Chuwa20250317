import java.util.Objects;

public class Q12_equalHashcode {
    //equals is to compare whether two elements are the same. We can override equal based our own needs
    //but should also override hashcode, if we want to elements to be equal, they must have the same hashcode.
    String name;
    int age;
    public Q12_equalHashcode(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        Q12_equalHashcode another = (Q12_equalHashcode)obj;
        return this.name.equals(another.name) && this.age==another.age;
    }
}
