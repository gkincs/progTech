package org.example.wumpus.service.map.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.wumpus.service.map.MapReader;
import org.example.wumpus.service.map.exception.MapReadException;

public class BufferedReaderMapReader implements MapReader {

    private final BufferedReader bufferedReader;

    public BufferedReaderMapReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public List<String> readMap() throws MapReadException {
        String line;
        List<String> result = new ArrayList<>();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            throw new MapReadException("Failed to read map");
        }

        return result;
    }

}
