package association;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "Cat")
public class Cat {
    private long id;
    private String name;
    private LocalDate birthDate;
    private String sex;
    private String breed;

    private Caretaker caretaker;
    public Cat() {
    }

    public Cat(String firstName, LocalDate birthDate, String sex, String breed) {
        this.name = firstName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.breed = breed;
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

    @ManyToOne
    private Caretaker getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Transient
    public int getAge() {
        return Period.between(getBirthDate(), LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return String.format("Cat: %s born on %s, age: %s, caretaker: %s, DB INFO ON CAT (#%s @%s)", getName(), getBirthDate(), getAge(), getCaretaker() != null ? getCaretaker().getFullname() : "---", getId(), super.hashCode());
    }
}
