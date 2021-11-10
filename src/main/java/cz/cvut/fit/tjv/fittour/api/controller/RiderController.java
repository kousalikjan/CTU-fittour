package cz.cvut.fit.tjv.fittour.api.controller;

import cz.cvut.fit.tjv.fittour.domain.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class RiderController
{
    @GetMapping("/riders")
    Collection<Rider> all()
    {
        ArrayList<Rider> res = new ArrayList<>();
        res.add(new Rider(1,
                "Marcus",
                "Kleveland",
                LocalDate.of(2001, 8, 24),
                "PROFESSIONAL",
                new Snowboard(2, "Nitro", "Electric", "ROCKER", 6, 14000)
                ));
        return res;
    }

    @PostMapping("/riders")
    Rider newRider(@RequestBody Rider newRider)
    {
        return new Rider();
    }

    @GetMapping("/riders/{id}")
    Rider one(@PathVariable int id)
    {
        return new Rider();
    }

    @PutMapping("/riders/{id}")
    Rider updateRider(@RequestBody Rider riderDto, @PathVariable int id)
    {
        return new Rider();
    }

    @DeleteMapping("/riders/{id}")
    void deleteRider(@PathVariable int id)
    {
    }


}
