package cz.cvut.fit.tjv.fittour.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Contest
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_contest")
    @SequenceGenerator(name = "seq_contest", sequenceName = "seq_contest", initialValue = 1, allocationSize = 1)
    private Integer id;

    private LocalDate date;
    private String discipline;
    private int prizePool;

    @ManyToMany
    @JoinTable(name = "rider_contest",
            joinColumns = @JoinColumn(name = "rider_id"),
            inverseJoinColumns = @JoinColumn(name = "contest_id")
            )
    private Set<Rider> contestants;

    public Contest()
    {

    }

    public Contest(Integer id, LocalDate date, String discipline, int prizePool, Set<Rider> contestants)
    {
        this.id = id;
        this.date = date;
        this.discipline = discipline;
        this.prizePool = prizePool;
        this.contestants = contestants;
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

    public String getDiscipline()
    {
        return discipline;
    }

    public void setDiscipline(String discipline)
    {
        this.discipline = discipline;
    }

    public int getPrizePool()
    {
        return prizePool;
    }

    public void setPrizePool(int prizePool)
    {
        this.prizePool = prizePool;
    }

    public Set<Rider> getContestants()
    {
        return contestants;
    }

    public void setContestants(Set<Rider> contestants)
    {
        this.contestants = contestants;
    }

    public void addContestant(Rider rider)
    {
        contestants.add(rider);
    }

    public void removeContestant(Rider rider)
    {
        contestants.remove(rider);
    }



}
