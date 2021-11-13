package cz.cvut.fit.tjv.fittour.domain;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Rider
{

    @Id
    private int id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;


    @ManyToOne
    @JoinColumn(name = "snowboard_id")
    private Snowboard snowboard;

    public Rider()
    {

    }

    public Rider(int id, String name, String surname, LocalDate dateOfBirth, Snowboard snowboard)
    {
        this.id = Objects.requireNonNull(id);
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.snowboard = snowboard;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Snowboard getSnowboard()
    {
        return snowboard;
    }

    public void setSnowboard(Snowboard snowboard)
    {
        this.snowboard = snowboard;
    }
}
