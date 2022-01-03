package cz.cvut.fit.tjv.fittour.controller;

import cz.cvut.fit.tjv.fittour.business.ContestService;
import cz.cvut.fit.tjv.fittour.business.RiderService;
import cz.cvut.fit.tjv.fittour.business.SnowboardService;
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

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SnowboardControllerTests
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
        Snowboard sn2 = new Snowboard(2, "Nidecker", "Pamela", "ROCKER", 7, 7500, null);
        List<Snowboard> snowboards = List.of(sn1, sn2);


        Mockito.when(snowboardService.readAll()).thenReturn(snowboards);

        mockMvc.perform(get("/snowboards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].brand", Matchers.is("Burton")))
                .andExpect(jsonPath("$[1].modelName", Matchers.is("Pamela")));
    }

    @Test
    public void findOneTest() throws Exception
    {
        Snowboard sn1 = new Snowboard(1, "Burton", "Electric", "CAMBER", 4, 4500, null);
        Mockito.when(snowboardService.readById(1)).thenReturn(Optional.of(sn1));

        mockMvc.perform(get("/snowboards/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", Matchers.is("Burton")));

        mockMvc.perform(get("/snowboards/2"))
                .andExpect(status().isBadRequest());

    }



}
