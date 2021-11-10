package cz.cvut.fit.tjv.fittour.domain;

import java.time.LocalDate;
import java.util.List;

public class Contest
{
    private int id;
    private LocalDate date;
    private String resort;
    private int prizePool;
    private String discipline;
    private List<Rider> contestants;

    public Contest()
    {

    }

    public Contest(int id, LocalDate date, String resort, int prizePool, String discipline, List<Rider> contestants)
    {
        this.id = id;
        this.date = date;
        this.resort = resort;
        this.prizePool = prizePool;
        this.discipline = discipline;
        this.contestants = contestants;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    public String getResort()
    {
        return resort;
    }

    public void setResort(String resort)
    {
        this.resort = resort;
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

    public List<Rider> getContestants()
    {
        return contestants;
    }

    public void setContestants(List<Rider> contestants)
    {
        this.contestants = contestants;
    }
}
