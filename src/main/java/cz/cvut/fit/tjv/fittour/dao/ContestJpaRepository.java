package cz.cvut.fit.tjv.fittour.dao;

import cz.cvut.fit.tjv.fittour.domain.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestJpaRepository extends JpaRepository<Contest, Integer>
{
}
