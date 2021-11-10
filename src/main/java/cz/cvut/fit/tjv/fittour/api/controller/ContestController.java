package cz.cvut.fit.tjv.fittour.api.controller;

import cz.cvut.fit.tjv.fittour.api.dto.ContestDto;
import cz.cvut.fit.tjv.fittour.domain.Contest;
import cz.cvut.fit.tjv.fittour.domain.Rider;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class ContestController
{
    @GetMapping("/contests")
    Collection<ContestDto> all()
    {
        ArrayList<ContestDto> res = new ArrayList<>();
        res.add(new ContestDto(1, LocalDate.of(2020, 12, 6), "Aspen USA", 20000, "SLOPESTYLE", new ArrayList<Rider>()));
        return res;
    }

    @PostMapping("/contests")
    ContestDto newContest(@RequestBody ContestDto newContest)
    {
        return new ContestDto();
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
