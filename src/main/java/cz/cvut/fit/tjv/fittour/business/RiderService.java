package cz.cvut.fit.tjv.fittour.business;

import cz.cvut.fit.tjv.fittour.api.converter.RiderConverter;
import cz.cvut.fit.tjv.fittour.api.dto.RiderDto;
import cz.cvut.fit.tjv.fittour.api.exception.NoRiderFoundException;
import cz.cvut.fit.tjv.fittour.api.exception.NoSnowboardFoundException;
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
        Rider oldRider = readById(newRider.getId()).orElseThrow(NoRiderFoundException::new);
        Rider rider = RiderConverter.toModel(newRider);
        rider.setSnowboard(oldRider.getSnowboard());
        update(rider);
    }

    public void updateRiderSnowboard(int riderID, int snowboardID)
            throws NoSnowboardFoundException, NoRiderFoundException, EntityStateException
    {
        Rider rider = readById(riderID).orElseThrow(NoRiderFoundException::new);
        Snowboard snowboard = snowboardService.readById(snowboardID)
                .orElseThrow(NoSnowboardFoundException::new);

        //This is not necessary, jpa does it automatically
        if(rider.getSnowboard() != null)
            rider.getSnowboard().removeRider(rider);

        rider.setSnowboard(snowboard);
        snowboard.addRider(rider);
        update(rider);
        snowboardService.update(snowboard);
    }

    public void deleteRiderSnowboard(int riderID) throws NoRiderFoundException
    {
        Rider rider = readById(riderID).orElseThrow(NoRiderFoundException::new);
        Snowboard snowboard = rider.getSnowboard();
        if(snowboard == null)
            return;

        snowboard.removeRider(rider);
        rider.setSnowboard(null);
        update(rider);
        snowboardService.update(snowboard);
    }

    public void deleteRider(int id)
    {
        Rider rider = readById(id).orElseThrow(NoRiderFoundException::new);
        if(rider.getSnowboard() != null)
        {
            Snowboard snowboard = rider.getSnowboard();
            snowboard.removeRider(rider);
            snowboardService.update(snowboard);
        }
        rider.setSnowboard(null);
        deleteById(id);
    }

    @Override
    protected boolean exists(Rider entity)
    {
        if(entity.getId() == null)
            return false;
        return repository.existsById(entity.getId());
    }
}
