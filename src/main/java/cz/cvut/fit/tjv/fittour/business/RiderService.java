package cz.cvut.fit.tjv.fittour.business;

import cz.cvut.fit.tjv.fittour.api.converter.RiderConverter;
import cz.cvut.fit.tjv.fittour.api.dto.RiderDto;
import cz.cvut.fit.tjv.fittour.api.exception.NoEntityFoundException;
import cz.cvut.fit.tjv.fittour.dao.RiderJpaRepository;
import cz.cvut.fit.tjv.fittour.domain.Rider;
import cz.cvut.fit.tjv.fittour.domain.Snowboard;
import org.springframework.stereotype.Component;

@Component
public class RiderService extends AbstractCrudService<Integer, Rider, RiderJpaRepository>
{
    private final SnowboardService snowboardService;

    public RiderService(RiderJpaRepository repository, SnowboardService snowboardService)
    {
        super(repository);
        this.snowboardService = snowboardService;
    }


    public void updateRiderWithoutSnowboard(RiderDto newRider) throws EntityStateException, NullPointerException
    {
        Rider oldRider = readById(newRider.getId()).orElseThrow(NoEntityFoundException::new);
        Rider rider = RiderConverter.toModel(newRider);
        rider.setSnowboard(oldRider.getSnowboard());
        update(rider);
    }

    public void updateRiderSnowboard(int riderID, int snowboardID)
            throws NoEntityFoundException, EntityStateException
    {
        Rider rider = readById(riderID).orElseThrow(NoEntityFoundException::new);
        Snowboard snowboard = snowboardService.readById(snowboardID)
                .orElseThrow(NoEntityFoundException::new);
        rider.setSnowboard(snowboard);
        snowboard.addRider(rider);
        update(rider);
        snowboardService.update(snowboard);
    }


    @Override
    protected boolean exists(Rider entity)
    {
        return repository.existsById(entity.getId());
    }
}
