package cz.cvut.fit.tjv.fittour.controller;

import cz.cvut.fit.tjv.fittour.business.ContestService;
import cz.cvut.fit.tjv.fittour.business.RiderService;
import cz.cvut.fit.tjv.fittour.business.SnowboardService;
import cz.cvut.fit.tjv.fittour.domain.Contest;
import cz.cvut.fit.tjv.fittour.domain.Rider;
import cz.cvut.fit.tjv.fittour.domain.Snowboard;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ContestControllerTests
{
    @MockBean
    SnowboardService snowboardService;

    @MockBean
    RiderService riderService;

    @MockBean
    ContestService contestService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAllTest() throws Exception
    {
        Snowboard sn1 = new Snowboard(1, "Burton", "Electric", "CAMBER", 4, 4500, null);
        Rider r1 = new Rider(1, "Marcus", "Kleveland", LocalDate.of(1985, 6, 19), sn1, null);
        Rider r2 = new Rider(1, "Jasper", "Tjader", LocalDate.of(1985, 11, 19), sn1, null);
        Contest c1 = new Contest(1,  LocalDate.of(2020, 6, 19), "SLOPESTYLE", 8000, Set.of(r1));
        Contest c2 = new Contest(2,  LocalDate.of(2021, 6, 19), "HALFPIPE", 11000, Set.of(r1, r2));
        List<Contest> contests = List.of(c1, c2);

        Mockito.when(contestService.readAll()).thenReturn(contests);

        mockMvc.perform(get("/contests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].discipline", Matchers.is("SLOPESTYLE")))
                .andExpect(jsonPath("$[0].prizePool", Matchers.is(8000)))
                .andExpect(jsonPath("$[0].contestants[0].name", Matchers.is("Marcus")))
                .andExpect(jsonPath("$[1].discipline", Matchers.is("HALFPIPE")));
    }

}
