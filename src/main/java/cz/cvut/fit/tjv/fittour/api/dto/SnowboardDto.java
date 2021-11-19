package cz.cvut.fit.tjv.fittour.api.dto;

public class SnowboardDto
{
    public Integer id;
    public String brand;
    public String modelName;
    public String profile;
    public int flex;
    public int price;

    public SnowboardDto()
    {

    }

    public SnowboardDto(Integer id, String brand, String modelName, String profile, int flex, int price)
    {
        this.id = id;
        this.brand = brand;
        this.modelName = modelName;
        this.profile = profile;
        this.flex = flex;
        this.price = price;
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
}
