package cz.cvut.fit.tjv.fittour.api.converter;

import cz.cvut.fit.tjv.fittour.api.dto.SnowboardDto;
import cz.cvut.fit.tjv.fittour.domain.Snowboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class SnowboardConverter
{
    public static Snowboard toModel(SnowboardDto snowboardDto)
    {
        return new Snowboard(Objects.requireNonNull(snowboardDto.id),
                snowboardDto.brand,
                snowboardDto.modelName,
                snowboardDto.profile,
                snowboardDto.flex,
                snowboardDto.price,
                null);
    }

    public static SnowboardDto fromModel(Snowboard snowboard)
    {
        return new SnowboardDto(snowboard.getId(),
                snowboard.getBrand(),
                snowboard.getModelName(),
                snowboard.getProfile(),
                snowboard.getFlex(),
                snowboard.getPrice());
    }

    public static Collection<SnowboardDto> fromModelMany(Collection<Snowboard> snowboards)
    {
        Collection<SnowboardDto> snowboardDtos = new ArrayList<>();
        snowboards.forEach((snowboard) -> snowboardDtos.add(fromModel(snowboard)));
        return snowboardDtos;
    }
}
