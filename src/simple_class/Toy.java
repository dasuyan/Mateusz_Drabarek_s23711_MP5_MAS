package simple_class;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "Toy")
public class Toy {
    private long id;
    private String name;
    private LocalDate releaseDate;

    public Toy() {
    }

    public Toy(String name, LocalDate releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Transient
    public int getAge() {
        return Period.between(getReleaseDate(), LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return String.format("Toy: %s released on %s, age: %s, DB INFO ON TOY (#%s @%s)", getName(), getReleaseDate(), getAge(), getId(), super.hashCode());
    }
}
