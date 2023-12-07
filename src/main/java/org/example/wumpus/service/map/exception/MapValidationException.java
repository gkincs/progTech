package org.example.wumpus.service.map.exception;

/**
 * Kivétel, a térkép validálása során létrejövő hibák kezelésére.
 */
public class MapValidationException extends Exception {
    public MapValidationException(String message) {
        super(message);
    }
}
