package cz.cvut.fit.tjv.fittour.controller;

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
    Collection<Contest> all()
    {
        ArrayList<Contest> res = new ArrayList<>();
        res.add(new Contest(1, LocalDate.of(2020, 12, 6), "Aspen USA", 20000, "SLOPESTYLE", new ArrayList<Rider>()));
        return res;
    }

    @PostMapping("/contests")
    Contest newContest(@RequestBody Contest newContest)
    {
        return new Contest();
    }

    @GetMapping("/contests/{id}")
    Contest one(@PathVariable int id)
    {
        return new Contest();
    }

    @PutMapping("/contests/{id}")
    Contest updateContest(@RequestBody Contest contestDto, @PathVariable int id)
    {
        return new Contest();
    }

    @DeleteMapping("/contests/{id}")
    void deleteContest(@PathVariable int id)
    {

    }
}
