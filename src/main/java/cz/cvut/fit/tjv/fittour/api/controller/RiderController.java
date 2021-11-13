package cz.cvut.fit.tjv.fittour.api.controller;

import cz.cvut.fit.tjv.fittour.api.converter.RiderConverter;
import cz.cvut.fit.tjv.fittour.api.dto.RiderDto;
import cz.cvut.fit.tjv.fittour.business.RiderService;
import cz.cvut.fit.tjv.fittour.domain.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class RiderController
{

    private final RiderService riderService;

    public RiderController(RiderService riderService)
    {
        this.riderService = riderService;
    }

    @GetMapping("/riders")
    Collection<RiderDto> all()
    {
        return RiderConverter.fromModelMany(riderService.readAll());
    }

    @PostMapping("/riders")
    RiderDto newRider(@RequestBody RiderDto newRider)
    {
        System.out.println("Adding new rider");
        Rider rider = RiderConverter.toModel(newRider);
        System.out.println(rider.getSnowboard().getId());
        riderService.create(rider);
        return RiderConverter.fromModel(rider);
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
