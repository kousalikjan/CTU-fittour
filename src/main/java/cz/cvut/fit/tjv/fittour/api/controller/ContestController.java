package cz.cvut.fit.tjv.fittour.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fit.tjv.fittour.api.converter.ContestConverter;
import cz.cvut.fit.tjv.fittour.api.dto.ContestDto;
import cz.cvut.fit.tjv.fittour.api.exception.ExpectedNullIDException;
import cz.cvut.fit.tjv.fittour.api.exception.NoContestFoundException;
import cz.cvut.fit.tjv.fittour.business.ContestService;
import cz.cvut.fit.tjv.fittour.business.EntityStateException;
import cz.cvut.fit.tjv.fittour.domain.Contest;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ContestController
{

    private final ContestService contestService;

    public ContestController(ContestService contestService)
    {
        this.contestService = contestService;
    }

    @JsonView(Views.ContestOutput.class)
    @GetMapping("/contests")
    Collection<ContestDto> all()
    {
        return ContestConverter.fromModelMany(contestService.readAll());
    }

    @JsonView(Views.ContestOutput.class)
    @PostMapping("/contests")
    ContestDto newContest(@JsonView(Views.Public.class) @RequestBody ContestDto newContest)
            throws ExpectedNullIDException, NoContestFoundException, EntityStateException
    {
        if(newContest.getId() != null)
            throw new ExpectedNullIDException("Contest");
        Contest contest = ContestConverter.toModel(newContest);
        contestService.create(contest);
        return ContestConverter.fromModel(contestService.readById(contest.getId())
                .orElseThrow(NoContestFoundException::new));
    }

    @JsonView(Views.ContestOutput.class)
    @GetMapping("/contests/{id}")
    ContestDto one(@PathVariable int id)
    {
        return ContestConverter.fromModel(
                contestService.readById(id)
                        .orElseThrow(NoContestFoundException::new));
    }

    @JsonView(Views.ContestOutput.class)
    @PutMapping("/contests/{id}")
    ContestDto updateContest(@JsonView(Views.Public.class) @RequestBody ContestDto contestDto, @PathVariable int id)
            throws NoContestFoundException
    {
        if(contestDto.getId() != null)
            throw new ExpectedNullIDException("Contest");
        contestDto.setId(id);
        contestService.updateContestWithoutContestants(contestDto);
        return ContestConverter.fromModel(
                contestService.readById(id)
                        .orElseThrow(NoContestFoundException::new));
    }

    @JsonView(Views.ContestOutput.class)
    @PostMapping("/contests/{contestID}/contestants")
    ContestDto addContestant(@PathVariable int contestID, @RequestBody int riderID)
    {
        contestService.addContestant(contestID, riderID);

        return ContestConverter.fromModel(
                contestService.readById(contestID)
                    .orElseThrow(NoContestFoundException::new));
    }





    @DeleteMapping("/contests/{id}")
    void deleteContest(@PathVariable int id) throws NoContestFoundException
    {
        contestService.readById(id)
                .orElseThrow(NoContestFoundException::new);
        contestService.deleteById(id);
    }
}
