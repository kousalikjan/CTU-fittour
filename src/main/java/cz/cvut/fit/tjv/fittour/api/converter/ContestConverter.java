package cz.cvut.fit.tjv.fittour.api.converter;

import cz.cvut.fit.tjv.fittour.api.dto.ContestDto;
import cz.cvut.fit.tjv.fittour.domain.Contest;

import java.util.ArrayList;
import java.util.Collection;

public class ContestConverter
{
    public static Contest toModel(ContestDto contestDto)
    {
        return new Contest(contestDto.id,
                contestDto.date,
                contestDto.discipline,
                contestDto.prizePool,
                null);

    }

    public static ContestDto fromModel(Contest contest)
    {
       return new ContestDto(contest.getId(),
                contest.getDate(),
                contest.getPrizePool(),
                contest.getDiscipline(),
                contest.getContestants());
    }

    public static Collection<ContestDto> fromModelMany(Collection<Contest> contests)
    {
        Collection<ContestDto> contestDtos = new ArrayList<>();
        contests.forEach(contest -> contestDtos.add(fromModel(contest)));
        return contestDtos;
    }


}
