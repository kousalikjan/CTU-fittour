package cz.cvut.fit.tjv.fittour.service;


import cz.cvut.fit.tjv.fittour.api.exception.NoEntityFoundException;
import cz.cvut.fit.tjv.fittour.api.exception.NoSnowboardFoundException;
import cz.cvut.fit.tjv.fittour.business.SnowboardService;
import cz.cvut.fit.tjv.fittour.dao.SnowboardJpaRepository;
import cz.cvut.fit.tjv.fittour.domain.Snowboard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SnowboardServiceTests
{
    @InjectMocks
    SnowboardService snowboardService;

    @Mock
    SnowboardJpaRepository dao;

    @Test
    public void findAllSnowboardsTest()
    {
        List<Snowboard> list = new ArrayList<>();
        Snowboard sn1 = new Snowboard(1, "Burton", "Electric", "CAMBER", 4, 4500, null);
        Snowboard sn2 = new Snowboard(2, "Nidecker", "Pamela", "ROCKER", 7, 7500, null);
        Snowboard sn3 = new Snowboard(3, "Arbor", "Wood", "BANANA", 2, 11000, null);

        list.add(sn1);
        list.add(sn2);
        list.add(sn3);

        when(dao.findAll()).thenReturn(list);

        //Real test
        Collection<Snowboard> userList = snowboardService.readAll();
        assertEquals(3, userList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void createSnowboardTest()
    {
        Snowboard snowboard = new Snowboard(1, "Burton", "Electric", "CAMBER", 4, 4500, null);
        snowboardService.create(snowboard);
        verify(dao, times(1)).save(snowboard);
    }

    @Test
    public void readOneSnowboardTest()
    {
        Snowboard snowboard = new Snowboard(1, "Burton", "Electric", "CAMBER", 4, 4500, null);
        when(dao.findById(1)).thenReturn(Optional.of(snowboard));
        Snowboard res = snowboardService.readById(1).orElseThrow(NoSnowboardFoundException::new);
        Assertions.assertNotNull(res);
        assertEquals("Burton", res.getBrand());
        assertEquals("Electric", res.getModelName());
        assertEquals("CAMBER", res.getProfile());
        assertEquals(4, res.getFlex());
        assertEquals(4500, res.getPrice());
        Assertions.assertNull(snowboard.getRiders());
    }

    @Test
    public void deleteSnowboardTest()
    {
        Snowboard snowboard = new Snowboard(1, "Burton", "Electric", "CAMBER", 4, 4500, null);
        snowboardService.create(snowboard);
        snowboardService.deleteById(1);
        Collection<Snowboard> all = snowboardService.readAll();
        assertEquals(0, all.size());
    }


}
