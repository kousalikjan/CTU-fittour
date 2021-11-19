package cz.cvut.fit.tjv.fittour.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fit.tjv.fittour.api.converter.SnowboardConverter;
import cz.cvut.fit.tjv.fittour.api.dto.SnowboardDto;
import cz.cvut.fit.tjv.fittour.api.exception.NoEntityFoundException;
import cz.cvut.fit.tjv.fittour.api.exception.UpdatedIDException;
import cz.cvut.fit.tjv.fittour.business.EntityStateException;
import cz.cvut.fit.tjv.fittour.business.SnowboardService;
import cz.cvut.fit.tjv.fittour.domain.Snowboard;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
public class SnowboardController
{

    private final SnowboardService snowboardService;

    SnowboardController(SnowboardService snowboardService)
    {
        this.snowboardService = snowboardService;
    }


    @JsonView(Views.Public.class)
    @GetMapping("/snowboards")
    Collection<SnowboardDto> all()
    {
        for (Snowboard snowboard:snowboardService.readAll())
        {
            System.out.println(snowboard);
        }
        return SnowboardConverter.fromModelMany(snowboardService.readAll());
    }

    @JsonView(Views.Public.class)
    @PostMapping("/snowboards")
    SnowboardDto newUser(@RequestBody SnowboardDto newSnowboard) throws EntityStateException
    {
        Snowboard snowboard = SnowboardConverter.toModel(newSnowboard);
        snowboardService.create(snowboard);
        return SnowboardConverter.fromModel(
                snowboardService.readById(snowboard.getId()).
                        orElseThrow(NoEntityFoundException::new));
    }

    @JsonView(Views.Public.class)
    @GetMapping("/snowboards/{id}")
    SnowboardDto one(@PathVariable int id)
    {
        return SnowboardConverter.fromModel(
                snowboardService.readById(id)
                        .orElseThrow(NoEntityFoundException::new)
        );
    }

    @JsonView(Views.Public.class)
    @PutMapping("/snowboards/{id}")
    SnowboardDto updateSnowboard(@RequestBody SnowboardDto snowboardDto, @PathVariable int id)
            throws EntityStateException, NoEntityFoundException, NullPointerException
    {
        // Check whether the id exists
        Snowboard oldSnowboard = snowboardService.readById(id).orElseThrow(NoEntityFoundException::new);
        Snowboard snowboard = SnowboardConverter.toModel(snowboardDto);
        if(snowboard.getId() != id)
            throw new UpdatedIDException();
        snowboard.setRiders(oldSnowboard.getRiders());
        snowboardService.update(snowboard);
        return SnowboardConverter.fromModel(snowboard);
    }

    @DeleteMapping("/snowboards/{id}")
    void deleteSnowboard(@PathVariable int id)
    {
        snowboardService.readById(id)
                .orElseThrow(NoEntityFoundException::new);
        snowboardService.deleteById(id);
    }
}
