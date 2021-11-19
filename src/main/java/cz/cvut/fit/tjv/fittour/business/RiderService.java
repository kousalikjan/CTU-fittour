package cz.cvut.fit.tjv.fittour.business;

import cz.cvut.fit.tjv.fittour.api.converter.RiderConverter;
import cz.cvut.fit.tjv.fittour.api.dto.RiderInputDto;
import cz.cvut.fit.tjv.fittour.dao.RiderJpaRepository;
import cz.cvut.fit.tjv.fittour.domain.Rider;
import cz.cvut.fit.tjv.fittour.domain.Snowboard;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RiderService extends AbstractCrudService<Integer, Rider, RiderJpaRepository>
{
    private final SnowboardService snowboardService;

    public RiderService(RiderJpaRepository repository, SnowboardService snowboardService)
    {
        super(repository);
        this.snowboardService = snowboardService;
    }

    public void addRider(RiderInputDto newRider) throws EntityStateException
    {
        Rider rider = RiderConverter.toModel(newRider);
        if(newRider.snowboardId != null)
        {
            Optional<Snowboard> snowboardOpt = snowboardService.readById(newRider.snowboardId);
            snowboardOpt.ifPresent(rider::setSnowboard);
        }
        create(rider);
    }


    @Override
    protected boolean exists(Rider entity)
    {
        return repository.existsById(entity.getId());
    }
}
