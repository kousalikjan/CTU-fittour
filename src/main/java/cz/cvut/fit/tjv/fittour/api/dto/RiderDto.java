package cz.cvut.fit.tjv.fittour.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fit.tjv.fittour.api.controller.Views;
import cz.cvut.fit.tjv.fittour.domain.Snowboard;

import java.time.LocalDate;

public class RiderDto
{
    @JsonView(Views.Public.class)
    public Integer id;

    @JsonView(Views.Public.class)
    public String name;

    @JsonView(Views.Public.class)
    public String surname;

    @JsonView(Views.Public.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d.M.yyyy")
    public LocalDate dateOfBirth;

    @JsonView(Views.Output.class)
    public Snowboard snowboard;

    public RiderDto()
    {

    }

    public RiderDto(Integer id, String name, String surname, LocalDate dateOfBirth, Snowboard snowboard)
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
