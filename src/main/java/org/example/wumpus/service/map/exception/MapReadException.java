package org.example.wumpus.service.map.exception;

/**
 * Kivétel, a térkép beolvasása során létrejövő hibák kezelésére.
 */

public class MapReadException extends Exception {
    public MapReadException(String message) {
        super(message);
    }
}
