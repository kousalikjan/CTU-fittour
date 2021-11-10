package cz.cvut.fit.tjv.fittour.api.controller;

import cz.cvut.fit.tjv.fittour.api.converter.SnowboardConverter;
import cz.cvut.fit.tjv.fittour.api.dto.SnowboardDto;
import cz.cvut.fit.tjv.fittour.domain.Snowboard;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class SnowboardController
{
    @GetMapping("/snowboards")
    Collection<SnowboardDto> all()
    {
        ArrayList<Snowboard> res = new ArrayList<>();
        res.add(new Snowboard(null, "Burton", "Spiral", "CAMBER", 7, 6000));
        res.add(new Snowboard(2, "Nidecker", "Pamela Anderson", "HYBRID", 6, 7500));

        return SnowboardConverter.fromModelMany(res);
    }

    @PostMapping("/snowboards")
    SnowboardDto newUser(@RequestBody SnowboardDto newSnowboard)
    {
        System.out.println(newSnowboard.getId());

        return new SnowboardDto(newSnowboard.getId(),
                newSnowboard.getBrand(),
                newSnowboard.getModelName(),
                newSnowboard.getProfile(),
                newSnowboard.getFlex(),
                newSnowboard.getPrice());
    }

    @GetMapping("/snowboards/{id}")
    SnowboardDto one(@PathVariable String id)
    {
        int newId = 0;
        try {
            newId = Integer.parseInt(id);
        }catch (NumberFormatException e)
        {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid ID");
        }
        return new SnowboardDto(newId,
                "Nitro",
                "Kleveland",
                "ROCKER",
                4,
                11000);
    }


    @PutMapping("/snowboards/{id}")
    SnowboardDto updateSnowboard(@RequestBody SnowboardDto snowboardDto, @PathVariable int id)
    {
        return new SnowboardDto(1,
                "Updated",
                "Snowboard",
                "FLAT",
                4,
                7400);
    }

    @DeleteMapping("/snowboards/{id}")
    void deleteSnowboard(@PathVariable int id)
    {
    }
}
