package cz.cvut.fit.tjv.fittour.api.exception;

public class NoRiderFoundException extends NoEntityFoundException
{
    public NoRiderFoundException() {
        super("Rider has not been found");
    }
}
