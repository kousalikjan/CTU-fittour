package cz.cvut.fit.tjv.fittour.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fit.tjv.fittour.api.converter.RiderConverter;
import cz.cvut.fit.tjv.fittour.api.dto.RiderDto;
import cz.cvut.fit.tjv.fittour.api.exception.NoEntityFoundException;
import cz.cvut.fit.tjv.fittour.api.exception.UpdatedIDException;
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

    @JsonView(Views.Output.class)
    @GetMapping("/riders")
    Collection<RiderDto> all()
    {
        return RiderConverter.fromModelMany(riderService.readAll());
    }

    @JsonView(Views.Output.class)
    @PostMapping("/riders")
    RiderDto newRider(@JsonView(Views.Public.class) @RequestBody RiderDto newRider)
            throws NoEntityFoundException, NullPointerException, EntityStateException
    {
        Rider rider = RiderConverter.toModel(newRider);
        riderService.create(rider);
        return RiderConverter.fromModel(
                riderService.readById(newRider.getId()).
                orElseThrow(NoEntityFoundException::new));
    }

    @JsonView(Views.Output.class)
    @GetMapping("/riders/{id}")
    RiderDto one(@PathVariable int id) throws NoEntityFoundException
    {
        return RiderConverter.fromModel(
                riderService.readById(id)
                        .orElseThrow(NoEntityFoundException::new));
    }

    @JsonView(Views.Output.class)
    @PutMapping("/riders/{id}")
    RiderDto updateRider(@JsonView(Views.Public.class) @RequestBody RiderDto riderDto, @PathVariable int id)
            throws NoEntityFoundException, UpdatedIDException, NullPointerException
    {
        if(riderDto.getId() != id)
            throw new UpdatedIDException();
        riderService.updateRiderWithoutSnowboard(riderDto);
        return RiderConverter.fromModel(
                riderService.readById(id)
                        .orElseThrow(NoEntityFoundException::new));
    }


    @JsonView({Views.Output.class})
    @PutMapping("/riders/{riderID}/snowboard")
    RiderDto updateRiderSnowboard(@PathVariable int riderID, @RequestBody int snowboardID)
            throws NoEntityFoundException, EntityStateException
    {
        riderService.updateRiderSnowboard(riderID, snowboardID);
        return RiderConverter.fromModel(
                riderService.readById(riderID)
                        .orElseThrow(NoEntityFoundException::new));
    }


    @DeleteMapping("/riders/{id}")
    void deleteRider(@PathVariable int id)
    {
        riderService.readById(id).orElseThrow(NoEntityFoundException::new);
        riderService.deleteById(id);
    }


}
