package cz.cvut.fit.tjv.fittour.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fit.tjv.fittour.api.converter.RiderConverter;
import cz.cvut.fit.tjv.fittour.api.dto.RiderDto;
import cz.cvut.fit.tjv.fittour.api.exception.ExpectedNullIDException;
import cz.cvut.fit.tjv.fittour.api.exception.NoRiderFoundException;
import cz.cvut.fit.tjv.fittour.api.exception.NoSnowboardFoundException;
import cz.cvut.fit.tjv.fittour.business.EntityStateException;
import cz.cvut.fit.tjv.fittour.business.RiderService;
import cz.cvut.fit.tjv.fittour.domain.Rider;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class RiderController
{

    private final RiderService riderService;

    public RiderController(RiderService riderService)
    {
        this.riderService = riderService;
    }

    @JsonView(Views.RiderOutput.class)
    @GetMapping("/riders")
    Collection<RiderDto> all()
    {
        return RiderConverter.fromModelMany(riderService.readAll());
    }

    @JsonView(Views.RiderOutput.class)
    @PostMapping("/riders")
    RiderDto newRider(@JsonView(Views.Public.class) @RequestBody RiderDto newRider)
            throws NoRiderFoundException, NullPointerException, EntityStateException
    {
        if(newRider.getId() != null)
            throw new ExpectedNullIDException("Rider");

        Rider rider = RiderConverter.toModel(newRider);
        riderService.create(rider);
        return RiderConverter.fromModel(
                riderService.readById(rider.getId()).
                orElseThrow(NoRiderFoundException::new));
    }

    @JsonView(Views.RiderOutput.class)
    @GetMapping("/riders/{id}")
    RiderDto one(@PathVariable int id) throws NoRiderFoundException
    {
        return RiderConverter.fromModel(
                riderService.readById(id)
                        .orElseThrow(NoRiderFoundException::new));
    }

    @JsonView(Views.RiderOutput.class)
    @PutMapping("/riders/{id}")
    RiderDto updateRider(@JsonView(Views.Public.class) @RequestBody RiderDto riderDto, @PathVariable int id)
            throws NoRiderFoundException
    {
        if(riderDto.getId() != null)
            throw new ExpectedNullIDException("Rider");

        riderDto.setId(id);
        riderService.updateRiderWithoutRelations(riderDto);
        return RiderConverter.fromModel(
                riderService.readById(id)
                        .orElseThrow(NoRiderFoundException::new));
    }


    @JsonView(Views.RiderOutput.class)
    @PutMapping("/riders/{riderID}/snowboard")
    RiderDto updateRiderSnowboard(@PathVariable int riderID, @RequestBody int snowboardID)
            throws NoRiderFoundException, NoSnowboardFoundException, EntityStateException
    {
        riderService.updateRiderSnowboard(riderID, snowboardID);
        return RiderConverter.fromModel(
                riderService.readById(riderID)
                        .orElseThrow(NoRiderFoundException::new));
    }

    @JsonView(Views.RiderOutput.class)
    @DeleteMapping("/riders/{riderID}/snowboard")
    RiderDto deleteRiderSnowboard(@PathVariable int riderID) throws NoRiderFoundException
    {
        riderService.deleteRiderSnowboard(riderID);
        return RiderConverter.fromModel(
                riderService.readById(riderID)
                        .orElseThrow(NoRiderFoundException::new));
    }


    @DeleteMapping("/riders/{id}")
    void deleteRider(@PathVariable int id) throws NoRiderFoundException
    {
        riderService.readById(id)
                .orElseThrow(NoRiderFoundException::new);
        riderService.deleteRider(id);
    }


}
