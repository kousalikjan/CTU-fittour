package cz.cvut.fit.tjv.fittour.app;

import cz.cvut.fit.tjv.fittour.api.controller.ContestController;
import cz.cvut.fit.tjv.fittour.api.controller.RiderController;
import cz.cvut.fit.tjv.fittour.api.controller.SnowboardController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DITests
{
    @Autowired
    SnowboardController snowboardController;

    @Autowired
    RiderController riderController;

    @Autowired
    ContestController contestController;

    @Test
    public void contextLoadsTest()
    {
        Assertions.assertThat(snowboardController).isNotNull();
        Assertions.assertThat(riderController).isNotNull();
        Assertions.assertThat(contestController).isNotNull();
    }
}
