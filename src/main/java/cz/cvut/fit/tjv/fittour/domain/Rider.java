package cz.cvut.fit.tjv.fittour.domain;
import java.time.LocalDate;

public class Rider
{
    private int id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String category;
    private Snowboard snowboard;

    public Rider()
    {

    }

    public Rider(int id, String name, String surname, LocalDate dateOfBirth, String category, Snowboard snowboard)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.category = category;
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

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
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
