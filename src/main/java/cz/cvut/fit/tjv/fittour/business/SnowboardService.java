package cz.cvut.fit.tjv.fittour.business;

import cz.cvut.fit.tjv.fittour.dao.SnowboardJpaRepository;
import cz.cvut.fit.tjv.fittour.domain.Snowboard;
import org.springframework.stereotype.Component;

@Component
public class SnowboardService extends AbstractCrudService<Integer, Snowboard, SnowboardJpaRepository>
{

    public SnowboardService(SnowboardJpaRepository snowboardRepository)
    {
        super(snowboardRepository);
    }

    @Override
    protected boolean exists(Snowboard entity)
    {
        return repository.existsById(entity.getId());
    }
}
