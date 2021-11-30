package cz.cvut.fit.tjv.fittour.dao;

import cz.cvut.fit.tjv.fittour.domain.Snowboard;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SnowboardJpaRepository extends JpaRepository<Snowboard, Integer>
{
    Collection<Snowboard> findSnowboardByBrandIsIgnoreCase(String brand);
    
}
