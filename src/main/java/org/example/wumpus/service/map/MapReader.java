package org.example.wumpus.service.map;

import java.util.List;

import org.example.wumpus.service.map.exception.MapReadException;

public interface MapReader {
    List<String> readMap() throws MapReadException;
}
