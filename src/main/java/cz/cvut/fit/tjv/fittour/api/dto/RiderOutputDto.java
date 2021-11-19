package cz.cvut.fit.tjv.fittour.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.cvut.fit.tjv.fittour.domain.Rider;
import cz.cvut.fit.tjv.fittour.domain.Snowboard;

import java.time.LocalDate;

public class RiderOutputDto
{
    public Integer id;
    public String name;
    public String surname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d.M.yyyy")
    public LocalDate dateOfBirth;

    public Snowboard snowboard;

    public RiderOutputDto()
    {

    }

    public RiderOutputDto(Integer id, String name, String surname, LocalDate dateOfBirth, Snowboard snowboard)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.snowboard = snowboard;
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
}
