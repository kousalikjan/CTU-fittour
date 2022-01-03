package cz.cvut.fit.tjv.fittour.integration;


import cz.cvut.fit.tjv.fittour.api.controller.SnowboardController;
import cz.cvut.fit.tjv.fittour.api.dto.SnowboardDto;
import cz.cvut.fit.tjv.fittour.api.exception.NoSnowboardFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SnowboardIntegrationTest
{
    @Autowired
    SnowboardController snowboardController;


    @Test
    public void testCreateRead()
    {
        SnowboardDto snowboardDto = new SnowboardDto(null, "Bataleon", "Spehere", "CAMROCK", 4, 9500);

        SnowboardDto snowboardResult = snowboardController.newUser(snowboardDto);

        Iterable<SnowboardDto> snowboards = snowboardController.all();

        Assertions.assertThat(snowboards).anyMatch(snowboard -> Objects.equals(snowboard.getId(), snowboardResult.getId()));

        snowboardController.deleteSnowboard(snowboardResult.getId());

    }

    @Test
    public void errorHandlingNoSnowboardFoundExceptionThrown() {

        Assertions.assertThatExceptionOfType(NoSnowboardFoundException.class)
                .isThrownBy(() -> snowboardController.one(0));
    }


}
