//Builder pattern: When I have lots of fields, when the constructor is super complex, it makes the constructor
//more flexible and the code more readable.
//How to use it: create a real class and create an inner class builder, instead of passing all the fields into
//real class constructor, I pass the builder into the constructor. So I can choose what parameters I want to use
//when creating an object.
public class Car {
    String brand;
    String model;
    int year;

    public Car(Builder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.year = builder.year;
    }

    static class Builder {
        String brand;
        String model;
        int year;

        public Builder(String brand, String model) {
            this.brand = brand;
            this.model = model;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

class Main {
    public static void main(String[] args) {
        Car car = new Car.Builder("BMW", "H")
                .setYear(2024)
                .build();
        System.out.println(car);
    }
}
