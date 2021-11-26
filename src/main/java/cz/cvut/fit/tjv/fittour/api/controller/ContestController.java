package cz.cvut.fit.tjv.fittour.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fit.tjv.fittour.api.converter.ContestConverter;
import cz.cvut.fit.tjv.fittour.api.dto.ContestDto;
import cz.cvut.fit.tjv.fittour.api.exception.ExpectedNullIDException;
import cz.cvut.fit.tjv.fittour.api.exception.NoContestFoundException;
import cz.cvut.fit.tjv.fittour.business.ContestService;
import cz.cvut.fit.tjv.fittour.domain.Contest;
import cz.cvut.fit.tjv.fittour.domain.Rider;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
            throws ExpectedNullIDException
    {
        if(newContest.getId() != null)
            throw new ExpectedNullIDException("Contest");
        Contest contest = ContestConverter.toModel(newContest);
        contestService.create(contest);
        return ContestConverter.fromModel(contestService.readById(contest.getId())
                .orElseThrow(NoContestFoundException::new));
    }

    @GetMapping("/contests/{id}")
    ContestDto one(@PathVariable int id)
    {
        return new ContestDto();
    }

    @PutMapping("/contests/{id}")
    ContestDto updateContest(@RequestBody ContestDto contestDto, @PathVariable int id)
    {
        return new ContestDto();
    }

    @DeleteMapping("/contests/{id}")
    void deleteContest(@PathVariable int id)
    {

    }
}
