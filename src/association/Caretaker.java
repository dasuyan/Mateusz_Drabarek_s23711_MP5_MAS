package association;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Sample class for Hibernate (the annotation approach).
 */
@Entity(name = "Caretaker")
public class Caretaker {
    public enum MovieCategory {Unknown, Comedy, SciFi}

    private long id;
    private String name;
    private String surname;

    private List<Cat> cats = new ArrayList<>();

    public Caretaker() {
    }

    public Caretaker(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @OneToMany(mappedBy = "caretaker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cat> getCats() {
        return cats;
    }

    private void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public void addCat(Cat cat) {
        getCats().add(cat);
        cat.setCaretaker(this);
    }

    public void removeCat(Cat cat) {
        getCats().remove(cat);
        cat.setCaretaker(null);
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
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
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Transient
    public String getFullname() {
        return getName() + " " + getSurname();
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();

        sb.append(String.format("Caretaker: %s, DB INFO ON CARETAKER (#%s @%s)", getFullname(), getId(), super.hashCode()));
        sb.append("\n   Cats: ");
        sb.append(getCats().size() == 0 ? "---" : "");
        for (var cat : getCats()) {
            sb.append(cat);
            sb.append("; ");
        }
        return sb.toString();
    }
}