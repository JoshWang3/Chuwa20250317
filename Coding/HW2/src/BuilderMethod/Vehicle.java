package src.BuilderMethod;

public class Vehicle {
    String brand;
    String model;
    int year;
    boolean has360Camera;

    public Vehicle(Builder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.year = builder.year;
        this.has360Camera = builder.has360Camera;
    }

    public static class Builder {
        private String brand;
        private String model;
        int year;
        boolean has360Camera;

        public Builder(String brand, String model) {
            this.brand = brand;
            this.model = model;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder has360Camera(boolean has360Camera) {
            this.has360Camera = has360Camera;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle.Builder("BMW", "Z4")
                .year(2024)
                .has360Camera(true)
                .build();
    }

}
