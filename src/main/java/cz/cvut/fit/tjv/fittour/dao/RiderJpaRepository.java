package cz.cvut.fit.tjv.fittour.dao;

import cz.cvut.fit.tjv.fittour.domain.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderJpaRepository extends JpaRepository<Rider, Integer>
{
}
