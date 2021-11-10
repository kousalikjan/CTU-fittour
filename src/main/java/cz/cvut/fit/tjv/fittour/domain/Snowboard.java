package cz.cvut.fit.tjv.fittour.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "snowboard")
public class Snowboard
{
    @Id
    private int id;

    private String brand;
    private String modelName;
    private String profile;
    private int flex;
    private int price;

    public Snowboard()
    {
    }

    /**
     * Saves given id in the instance.
     *
     * @param id given id; cannot be null
     * @throws NullPointerException if the given id is null
     */
    public Snowboard(Integer id, String brand, String modelName, String profile, int flex, int price)
    {
        this.id = Objects.requireNonNull(id);
        this.brand = brand;
        this.modelName = modelName;
        this.profile = profile;
        this.flex = flex;
        this.price = price;
    }

    public int getId()
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
}
