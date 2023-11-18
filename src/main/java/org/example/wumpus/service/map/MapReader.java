package org.example.wumpus.service.map;

import org.example.wumpus.service.map.exception.MapReadException;

import java.util.List;

public interface MapReader {
    List<String> readMap() throws MapReadException;
}
