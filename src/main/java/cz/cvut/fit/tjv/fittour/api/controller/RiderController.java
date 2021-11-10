package cz.cvut.fit.tjv.fittour.api.controller;

import cz.cvut.fit.tjv.fittour.api.dto.RiderDto;
import cz.cvut.fit.tjv.fittour.domain.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class RiderController
{
    @GetMapping("/riders")
    Collection<RiderDto> all()
    {
        ArrayList<RiderDto> res = new ArrayList<>();
        res.add(new RiderDto(1,
                "Marcus",
                "Kleveland",
                LocalDate.of(2001, 8, 24),
                "PROFESSIONAL",
                new Snowboard(2, "Nitro", "Electric", "ROCKER", 6, 14000)
                ));
        return res;
    }

    @PostMapping("/riders")
    RiderDto newRider(@RequestBody RiderDto newRider)
    {
        return new RiderDto();
    }

    @GetMapping("/riders/{id}")
    RiderDto one(@PathVariable int id)
    {
        return new RiderDto();
    }

    @PutMapping("/riders/{id}")
    RiderDto updateRider(@RequestBody RiderDto riderDto, @PathVariable int id)
    {
        return new RiderDto();
    }

    @DeleteMapping("/riders/{id}")
    void deleteRider(@PathVariable int id)
    {
    }


}
