package cz.cvut.fit.tjv.fittour.domain;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
public class Rider
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rider")
    @SequenceGenerator(name = "seq_rider", sequenceName = "seq_rider", initialValue = 1, allocationSize = 1)
    private Integer id;

    private String name;
    private String surname;
    private LocalDate dateOfBirth;

    @ManyToOne(cascade = {javax.persistence.CascadeType.PERSIST})
    @JoinColumn(name = "snowboard_id")
    private Snowboard snowboard;

    @ManyToMany(mappedBy = "contestants")
    private Set<Contest> contests;

    public Rider()
    {

    }

    public Rider(Integer id, String name, String surname, LocalDate dateOfBirth, Snowboard snowboard, Set<Contest> contests)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.snowboard = snowboard;
        this.contests = contests;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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

    public Set<Contest> getContests()
    {
        return contests;
    }

    public void setContests(Set<Contest> contests)
    {
        this.contests = contests;
    }

    public void addContest(Contest contest)
    {
        contests.add(contest);
    }

    public void removeContest(Contest contest)
    {
        contests.remove(contest);
    }

    @Override
    public String toString()
    {
        return "Rider{" +
                "id=" + id +
                '}';
    }
}
