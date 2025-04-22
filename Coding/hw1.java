import java.util.ArrayList;
import java.util.List;

class Animal{
    private String kind;
    //example of encapsulation
    public Animal(String kind){
        this.kind = kind;
    }

    public String getKind(){
        return this.kind;
    }
    public void sound(){
        System.out.println("Animal is making sound");
    }
}

class Dog extends Animal{
    public Dog(String kind){
        super(kind);
    }

    @Override
    public void sound() {
        System.out.println("Bark Bark!");
    }

}

//example of inheirtance
class Cat extends Animal{
    public Cat(String kind){
        super(kind);
    }

    @Override
    public void sound() {
        System.out.println("Meow Meow!");
    }

}

public class hw1 {
    public static void main(String[] args){
        List<Animal> animals = new ArrayList<>();
        animals.add(new Dog("dog"));
        animals.add(new Cat("cat"));

        //example of polymorphism
        for (Animal animal : animals){
            animal.sound();
        }
    }
}
