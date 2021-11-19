package cz.cvut.fit.tjv.fittour.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity(name = "snowboard")
public class Snowboard
{
    @Id
    private Integer id;

    private String brand;
    private String modelName;
    private String profile;
    private int flex;
    private int price;

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
        this.id = Objects.requireNonNull(id);
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

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Set<Rider> getRiders()
    {
        return riders;
    }

    public void setRiders(Set<Rider> riders)
    {
        this.riders = riders;
    }
}
