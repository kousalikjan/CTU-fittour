package cz.cvut.fit.tjv.fittour.business;

import cz.cvut.fit.tjv.fittour.api.converter.ContestConverter;
import cz.cvut.fit.tjv.fittour.api.dto.ContestDto;
import cz.cvut.fit.tjv.fittour.api.exception.NoContestFoundException;
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

    public void updateContestWithoutContestants(ContestDto newContest)
    {
          Contest oldContest = readById(newContest.getId()).orElseThrow(NoContestFoundException::new);
          Contest contest = ContestConverter.toModel(newContest);
          contest.setContestants(oldContest.getContestants());
          update(contest);
    }

    @Override
    protected boolean exists(Contest entity)
    {
        if(entity.getId() == null)
            return false;
        return repository.existsById(entity.getId());
    }
}
