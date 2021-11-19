package cz.cvut.fit.tjv.fittour.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fit.tjv.fittour.api.converter.RiderConverter;
import cz.cvut.fit.tjv.fittour.api.dto.RiderInputDto;
import cz.cvut.fit.tjv.fittour.api.dto.RiderOutputDto;
import cz.cvut.fit.tjv.fittour.api.exception.NoEntityFoundException;
import cz.cvut.fit.tjv.fittour.business.RiderService;
import cz.cvut.fit.tjv.fittour.business.SnowboardService;
import cz.cvut.fit.tjv.fittour.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
import java.util.Collection;
import java.util.Optional;

@RestController
public class RiderController
{

    private final RiderService riderService;

    public RiderController(RiderService riderService)
    {
        this.riderService = riderService;
    }

    @GetMapping("/riders")
    Collection<RiderOutputDto> all()
    {
        return RiderConverter.fromModelMany(riderService.readAll());
    }

    @PostMapping("/riders")
    RiderOutputDto newRider(@RequestBody RiderInputDto newRider)
    {
        riderService.addRider(newRider);
        return RiderConverter.fromModel(
                riderService.readById(newRider.getId()).
                orElseThrow(NoEntityFoundException::new));
    }

    @GetMapping("/riders/{id}")
    RiderOutputDto one(@PathVariable int id)
    {
        return RiderConverter.fromModel(
                riderService.readById(id)
                        .orElseThrow(NoEntityFoundException::new));
    }

    @PutMapping("/riders/{id}")
    RiderOutputDto updateRider(@RequestBody RiderInputDto riderDto, @PathVariable int id)
    {
        return new RiderOutputDto();
    }

    @DeleteMapping("/riders/{id}")
    void deleteRider(@PathVariable int id)
    {
    }


}
