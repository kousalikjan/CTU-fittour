package cz.cvut.fit.tjv.fittour.dao;

import cz.cvut.fit.tjv.fittour.domain.Snowboard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SnowboardRepositoryTests
{
    @Autowired
    SnowboardJpaRepository snowboardJpaRepository;

    @Test
    public void testCreateReadDelete() {

        snowboardJpaRepository.deleteAll();
        Snowboard snowboard = new Snowboard(1, "Burton", "Electric", "CAMBER", 4, 4500, null);

        snowboardJpaRepository.save(snowboard);

        Iterable<Snowboard> snowboards = snowboardJpaRepository.findAll();
        Assertions.assertThat(snowboards).extracting(Snowboard::getBrand).containsOnly("Burton");
        Assertions.assertThat(snowboards).extracting(Snowboard::getPrice).containsOnly(4500);
        Assertions.assertThat(snowboards).extracting(Snowboard::getProfile).containsOnly("CAMBER");

        snowboardJpaRepository.deleteAll();
        Assertions.assertThat(snowboardJpaRepository.findAll()).isEmpty();
    }
}
