package cz.cvut.fit.tjv.fittour.api.converter;

import cz.cvut.fit.tjv.fittour.api.dto.RiderDto;
import cz.cvut.fit.tjv.fittour.domain.Rider;

import java.util.ArrayList;
import java.util.Collection;

public class RiderConverter
{
    public static Rider toModel(RiderDto riderDto)
    {
        return new Rider(riderDto.id,
                riderDto.name,
                riderDto.surname,
                riderDto.dateOfBirth,
                riderDto.category,
                riderDto.snowboard);
    }

    public static RiderDto fromModel(Rider rider)
    {
        return new RiderDto(rider.getId(),
                rider.getName(),
                rider.getSurname(),
                rider.getDateOfBirth(),
                rider.getCategory(),
                rider.getSnowboard());
    }

    public static Collection<RiderDto> fromModelMany(Collection<Rider> riders)
    {
        Collection<RiderDto> riderDtos = new ArrayList<>();
        riders.forEach(rider -> riderDtos.add(fromModel(rider)));
        return riderDtos;
    }

}
