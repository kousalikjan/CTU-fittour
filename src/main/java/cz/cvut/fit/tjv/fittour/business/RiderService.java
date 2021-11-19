package cz.cvut.fit.tjv.fittour.business;

import cz.cvut.fit.tjv.fittour.dao.RiderJpaRepository;
import cz.cvut.fit.tjv.fittour.domain.Rider;
import org.springframework.beans.factory.annotation.Autowired;
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




    @Override
    protected boolean exists(Rider entity)
    {
        return repository.existsById(entity.getId());
    }
}
