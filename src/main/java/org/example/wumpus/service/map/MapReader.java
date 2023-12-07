package org.example.wumpus.service.map;

import java.util.List;

import org.example.wumpus.service.map.exception.MapReadException;

/**
 * Interface, amelyet a térképfájlok beolvasására szolgáló osztályok implementálnak.
 *
 * Az implementáló osztályoknak tartalmazni kell a readMap-et.
 * A readMap - lista formájában visszaadja a beolvasott térkép sorait.
 */

public interface MapReader {
    List<String> readMap() throws MapReadException;
}
