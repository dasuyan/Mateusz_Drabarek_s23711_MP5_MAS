package inheritance;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Car")
@DiscriminatorValue("Car")
public class Car extends Vehicle {
    private int numberOfDoors;

    public Car() {
    }

    public Car(String brand, String model, int numberOfDoors) {
        super(brand, model);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String toString() {
        return String.format("Car: brand: %s, model: %s, number of doors: %s, DB INFO ON CAR (#%s @%s)", getBrand(), getModel(), getNumberOfDoors(), getId(), super.hashCode());
    }
}