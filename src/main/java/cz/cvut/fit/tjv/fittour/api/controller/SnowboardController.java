package cz.cvut.fit.tjv.fittour.api.controller;

import cz.cvut.fit.tjv.fittour.api.converter.SnowboardConverter;
import cz.cvut.fit.tjv.fittour.api.dto.SnowboardDto;
import cz.cvut.fit.tjv.fittour.api.exception.NoEntityFoundException;
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


    @GetMapping("/snowboards")
    Collection<SnowboardDto> all()
    {
        return SnowboardConverter.fromModelMany(snowboardService.readAll());
    }

    @PostMapping("/snowboards")
    SnowboardDto newUser(@RequestBody SnowboardDto newSnowboard) throws EntityStateException
    {
        Snowboard snowboard = SnowboardConverter.toModel(newSnowboard);
        snowboardService.create(snowboard);
        return SnowboardConverter.fromModel(snowboard);
    }

    @GetMapping("/snowboards/{id}")
    SnowboardDto one(@PathVariable int id)
    {
        return SnowboardConverter.fromModel(
                snowboardService.readById(id)
                        .orElseThrow(NoEntityFoundException::new)
        );
    }
    
    @PutMapping("/snowboards/{id}")
    SnowboardDto updateSnowboard(@RequestBody SnowboardDto snowboardDto,
                                 @PathVariable int id) throws EntityStateException, NoEntityFoundException
    {
        // Check whether the id exists
        snowboardService.readById(id).orElseThrow(NoEntityFoundException::new);

        Snowboard snowboard = SnowboardConverter.toModel(snowboardDto);
        if(snowboardService.readById(snowboard.getId()).isPresent())
            if(snowboardService.readById(snowboard.getId()).get().getId() != id)
                throw new EntityStateException("Entity is not unique");

        // When ID is invalid ("string"), does nothing, throws EntityStateException when null
        //TODO: Parse in dto? Ask someone
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
