package cz.cvut.fit.tjv.fittour.api.exception;

public abstract class NoEntityFoundException extends RuntimeException
{
    NoEntityFoundException(String entity)
    {
        super(entity);
    }
}
