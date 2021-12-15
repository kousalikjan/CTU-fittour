package cz.cvut.fit.tjv.fittour.api.exception;

public class NoContestFoundException extends NoEntityFoundException
{
    public NoContestFoundException()
    {
        super("Contest has not been found");
    }
}
