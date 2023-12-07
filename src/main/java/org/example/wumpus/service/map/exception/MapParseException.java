package org.example.wumpus.service.map.exception;

/**
 * Kivétel, a térkép feldolgozása során létrejövő hibák kezelésére.
 */

public class MapParseException extends Exception {
    public MapParseException(String message) {
        super(message);
    }
}
