package cz.cvut.fit.tjv.fittour.api.converter;

import cz.cvut.fit.tjv.fittour.api.dto.RiderInputDto;
import cz.cvut.fit.tjv.fittour.api.dto.RiderOutputDto;
import cz.cvut.fit.tjv.fittour.domain.Rider;

import java.util.ArrayList;
import java.util.Collection;

public class RiderConverter
{

    public static Rider toModel(RiderInputDto rider)
    {
        return new Rider(rider.id,
                rider.name,
                rider.surname,
                rider.dateOfBirth,
                null);
    }

    public static RiderOutputDto fromModel(Rider rider)
    {
        return new RiderOutputDto(rider.getId(),
                rider.getName(),
                rider.getSurname(),
                rider.getDateOfBirth(),
                rider.getSnowboard());
    }

    public static Collection<RiderOutputDto> fromModelMany(Collection<Rider> riders)
    {
        Collection<RiderOutputDto> riderDtos = new ArrayList<>();
        riders.forEach(rider -> riderDtos.add(fromModel(rider)));
        return riderDtos;
    }

}
