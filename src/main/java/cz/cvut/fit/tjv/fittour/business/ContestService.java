package cz.cvut.fit.tjv.fittour.business;

import cz.cvut.fit.tjv.fittour.dao.ContestJpaRepository;
import cz.cvut.fit.tjv.fittour.domain.Contest;
import org.springframework.stereotype.Component;

@Component
public class ContestService extends AbstractCrudService<Integer, Contest, ContestJpaRepository>
{
    public ContestService(ContestJpaRepository repository)
    {
        super(repository);
    }

    @Override
    protected boolean exists(Contest entity)
    {
        if(entity.getId() == null)
            return false;
        return repository.existsById(entity.getId());
    }
}
