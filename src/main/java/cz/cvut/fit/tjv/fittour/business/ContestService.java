package cz.cvut.fit.tjv.fittour.business;

import cz.cvut.fit.tjv.fittour.api.converter.ContestConverter;
import cz.cvut.fit.tjv.fittour.api.dto.ContestDto;
import cz.cvut.fit.tjv.fittour.api.exception.NoContestFoundException;
import cz.cvut.fit.tjv.fittour.api.exception.NoRiderFoundException;
import cz.cvut.fit.tjv.fittour.dao.ContestJpaRepository;
import cz.cvut.fit.tjv.fittour.domain.Contest;
import cz.cvut.fit.tjv.fittour.domain.Rider;
import org.springframework.stereotype.Component;

@Component
public class ContestService extends AbstractCrudService<Integer, Contest, ContestJpaRepository>
{
    private final RiderService riderService;

    public ContestService(ContestJpaRepository repository, RiderService riderService)
    {
        super(repository);
        this.riderService = riderService;
    }

    public void updateContestWithoutContestants(ContestDto newContest)
    {
          Contest oldContest = readById(newContest.getId()).orElseThrow(NoContestFoundException::new);
          Contest contest = ContestConverter.toModel(newContest);
          contest.setContestants(oldContest.getContestants());
          update(contest);
    }


    public void addContestant(int contestID, int riderID)
            throws NoContestFoundException, NoRiderFoundException
    {
        Contest contest = readById(contestID).orElseThrow(NoContestFoundException::new);
        Rider rider = riderService.readById(riderID).orElseThrow(NoRiderFoundException::new);

        contest.addContestant(rider);
        rider.addContest(contest);

        update(contest);
        riderService.update(rider);
    }

    public void removeContestant(int contestID, int riderID)
            throws NoContestFoundException, NoRiderFoundException
    {
        Contest contest = readById(contestID).orElseThrow(NoContestFoundException::new);
        Rider rider = riderService.readById(riderID).orElseThrow(NoRiderFoundException::new);

        contest.removeContestant(rider);
        rider.removeContest(contest);

        update(contest);
        riderService.update(rider);
    }



    @Override
    protected boolean exists(Contest entity)
    {
        if(entity.getId() == null)
            return false;
        return repository.existsById(entity.getId());
    }
}
