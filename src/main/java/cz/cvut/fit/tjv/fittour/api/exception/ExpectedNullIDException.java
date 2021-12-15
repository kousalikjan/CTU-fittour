package cz.cvut.fit.tjv.fittour.api.exception;

public class ExpectedNullIDException extends RuntimeException
{
    public ExpectedNullIDException(String entity)
    {
        super(entity + " ID is expected to be null");
    }
}
