package cz.cvut.fit.tjv.fittour.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fit.tjv.fittour.api.converter.SnowboardConverter;
import cz.cvut.fit.tjv.fittour.api.dto.SnowboardDto;
import cz.cvut.fit.tjv.fittour.api.exception.ExpectedNullIDException;
import cz.cvut.fit.tjv.fittour.api.exception.NoSnowboardFoundException;
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
    SnowboardDto newUser(@RequestBody SnowboardDto newSnowboard)
            throws EntityStateException, NoSnowboardFoundException
    {
        if(newSnowboard.id != null)
            throw new ExpectedNullIDException("Snowboard");

        Snowboard snowboard = SnowboardConverter.toModel(newSnowboard);
        snowboardService.create(snowboard);
        return SnowboardConverter.fromModel(
                snowboardService.readById(snowboard.getId()).
                        orElseThrow(NoSnowboardFoundException::new));
    }

    @JsonView(Views.Public.class)
    @GetMapping("/snowboards/{id}")
    SnowboardDto one(@PathVariable int id) throws NoSnowboardFoundException
    {
        return SnowboardConverter.fromModel(
                snowboardService.readById(id)
                        .orElseThrow(NoSnowboardFoundException::new)
        );
    }

    @JsonView(Views.Public.class)
    @PutMapping("/snowboards/{id}")
    SnowboardDto updateSnowboard(@RequestBody SnowboardDto snowboardDto, @PathVariable int id)
            throws EntityStateException, NoSnowboardFoundException
    {
        if(snowboardDto.getId() != null)
            throw new ExpectedNullIDException("Snowboard");

        snowboardDto.setId(id);
        snowboardService.updateSnowboard(snowboardDto);
        return SnowboardConverter.fromModel(snowboardService.readById(id)
                .orElseThrow(NoSnowboardFoundException::new));
    }

    @DeleteMapping("/snowboards/{id}")
    void deleteSnowboard(@PathVariable int id) throws NoSnowboardFoundException
    {
        snowboardService.readById(id)
                .orElseThrow(NoSnowboardFoundException::new);
        snowboardService.deleteById(id);
    }
}
