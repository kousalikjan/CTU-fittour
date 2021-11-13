package cz.cvut.fit.tjv.fittour.business;

import cz.cvut.fit.tjv.fittour.dao.RiderJpaRepository;
import cz.cvut.fit.tjv.fittour.domain.Rider;
import org.springframework.stereotype.Component;

@Component
public class RiderService extends AbstractCrudService<Integer, Rider, RiderJpaRepository>
{

    public RiderService(RiderJpaRepository repository)
    {
        super(repository);
    }

    @Override
    protected boolean exists(Rider entity)
    {
        return repository.existsById(entity.getId());
    }
}
