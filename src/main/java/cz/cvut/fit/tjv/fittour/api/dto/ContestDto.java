package cz.cvut.fit.tjv.fittour.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fit.tjv.fittour.api.controller.Views;
import cz.cvut.fit.tjv.fittour.api.converter.RiderConverter;
import cz.cvut.fit.tjv.fittour.domain.Rider;

import java.time.LocalDate;
import java.util.Collection;

public class ContestDto
{

    @JsonView(Views.Public.class)
    public Integer id;

    @JsonView(Views.Public.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d.M.yyyy")
    public LocalDate date;

    @JsonView(Views.Public.class)
    public int prizePool;

    @JsonView(Views.Public.class)
    public String discipline;

    @JsonView(Views.ContestOutput.class)
    public Collection<RiderDto> contestants;


    public ContestDto()
    {
    }

    public ContestDto(Integer id, LocalDate date, int prizePool, String discipline, Collection<Rider> contestants)
    {
        this.id = id;
        this.date = date;
        this.prizePool = prizePool;
        this.discipline = discipline;
        this.contestants = contestants == null ? null : RiderConverter.fromModelMany(contestants);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public int getPrizePool()
    {
        return prizePool;
    }

    public void setPrizePool(int prizePool)
    {
        this.prizePool = prizePool;
    }

    public String getDiscipline()
    {
        return discipline;
    }

    public void setDiscipline(String discipline)
    {
        this.discipline = discipline;
    }

    public Collection<RiderDto> getContestants()
    {
        return contestants;
    }

    public void setContestants(Collection<RiderDto> contestants)
    {
        this.contestants = contestants;
    }
}
