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
    SnowboardDto newUser(@RequestBody SnowboardDto newSnowboard)
            throws EntityStateException, NoEntityFoundException, NullPointerException
    {
        Snowboard snowboard = SnowboardConverter.toModel(newSnowboard);
        snowboardService.create(snowboard);
        return SnowboardConverter.fromModel(
                snowboardService.readById(snowboard.getId()).
                        orElseThrow(NoEntityFoundException::new));
    }

    @JsonView(Views.Public.class)
    @GetMapping("/snowboards/{id}")
    SnowboardDto one(@PathVariable int id) throws NoEntityFoundException
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
        if(snowboardDto.getId() != id)
            throw new UpdatedIDException();
        snowboardService.updateSnowboard(snowboardDto);
        return SnowboardConverter.fromModel(snowboardService.readById(id)
                .orElseThrow(NoEntityFoundException::new));
    }

    @DeleteMapping("/snowboards/{id}")
    void deleteSnowboard(@PathVariable int id) throws NoEntityFoundException
    {
        snowboardService.readById(id)
                .orElseThrow(NoEntityFoundException::new);
        snowboardService.deleteById(id);
    }
}
