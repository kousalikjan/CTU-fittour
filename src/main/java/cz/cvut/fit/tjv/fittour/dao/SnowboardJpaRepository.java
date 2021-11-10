package cz.cvut.fit.tjv.fittour.dao;

import cz.cvut.fit.tjv.fittour.domain.Snowboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnowboardJpaRepository extends JpaRepository<Snowboard, Integer>
{
}
