package cz.cvut.fit.tjv.fittour.business;
import cz.cvut.fit.tjv.fittour.api.converter.SnowboardConverter;
import cz.cvut.fit.tjv.fittour.api.dto.SnowboardDto;
import cz.cvut.fit.tjv.fittour.api.exception.NoSnowboardFoundException;
import cz.cvut.fit.tjv.fittour.dao.SnowboardJpaRepository;
import cz.cvut.fit.tjv.fittour.domain.Snowboard;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SnowboardService extends AbstractCrudService<Integer, Snowboard, SnowboardJpaRepository>
{
    public SnowboardService(SnowboardJpaRepository snowboardRepository)
    {
        super(snowboardRepository);
    }

    public void updateSnowboard(SnowboardDto snowboardDto)
    {
        Snowboard oldSnowboard = readById(snowboardDto.getId()).orElseThrow(NoSnowboardFoundException::new);
        Snowboard snowboard = SnowboardConverter.toModel(snowboardDto);
        snowboard.setRiders(oldSnowboard.getRiders());
        update(snowboard);
    }


    public Collection<Snowboard> findByBrand(String brand)
    {
        return repository.findSnowboardByBrandIsIgnoreCase(brand);
    }

    @Override
    protected boolean exists(Snowboard entity)
    {
        if(entity.getId() == null)
            return false;
        return repository.existsById(entity.getId());
    }
}
