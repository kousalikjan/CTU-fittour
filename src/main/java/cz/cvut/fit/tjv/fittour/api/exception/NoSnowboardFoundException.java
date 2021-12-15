package cz.cvut.fit.tjv.fittour.api.exception;

public class NoSnowboardFoundException extends NoEntityFoundException {

    public NoSnowboardFoundException() {
        super("Snowboard has not been found");
    }
}
