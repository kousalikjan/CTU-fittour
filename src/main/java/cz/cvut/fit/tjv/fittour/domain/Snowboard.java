package cz.cvut.fit.tjv.fittour.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fit.tjv.fittour.api.controller.Views;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity(name = "snowboard")
public class Snowboard
{
    @Id
    @GeneratedValue
    private Integer id;

    private String brand;
    private String modelName;
    private String profile;
    private int flex;
    private int price;


    @JsonIgnore
    @OneToMany(mappedBy = "snowboard")
    private Set<Rider> riders;

    public Snowboard()
    {
    }

    /**
     * Saves given id in the instance.
     *
     * @param id given id; cannot be null
     * @throws NullPointerException if the given id is null
     */
    public Snowboard(Integer id, String brand, String modelName, String profile, int flex, int price, Set<Rider> riders)
    {
        this.id = id;
        this.brand = brand;
        this.modelName = modelName;
        this.profile = profile;
        this.flex = flex;
        this.price = price;
        this.riders = riders;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getModelName()
    {
        return modelName;
    }

    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    public String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        this.profile = profile;
    }

    public int getFlex()
    {
        return flex;
    }

    public void setFlex(int flex)
    {
        this.flex = flex;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }



    public Set<Rider> getRiders()
    {
        return riders;
    }

    public void setRiders(Set<Rider> riders)
    {
        this.riders = riders;
    }

    public void addRider(Rider rider)
    {
        riders.add(rider);
    }

    public void removeRider(Rider rider)
    {
        riders.remove(rider);
    }

    @PreRemove
    private void preRemove()
    {
        for (Rider r: riders)
            r.setSnowboard(null);
    }

    @Override
    public String toString()
    {
        return "Snowboard{" +
                "id=" + id +
                ", riders=" + riders +
                '}';
    }
}
