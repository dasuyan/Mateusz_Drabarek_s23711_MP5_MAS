package inheritance;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Motorcycle")
@DiscriminatorValue("Motorcycle")
public class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle() {
    }

    public Motorcycle(String brand, String model, boolean hasSidecar) {
        super(brand, model);
        this.hasSidecar = hasSidecar;
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }

    @Override
    public String toString() {
        return String.format("Motorcycle: brand: %s, model: %s, has sidecar: %s, DB INFO ON MOTORCYCLE (#%s @%s)", getBrand(), getModel(), hasSidecar(), getId(), super.hashCode());
    }
}