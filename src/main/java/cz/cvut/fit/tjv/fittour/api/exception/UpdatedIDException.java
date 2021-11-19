package cz.cvut.fit.tjv.fittour.api.exception;

public class UpdatedIDException extends RuntimeException
{
    public UpdatedIDException()
    {
        super("You cannot change entity ID");
    }
}
