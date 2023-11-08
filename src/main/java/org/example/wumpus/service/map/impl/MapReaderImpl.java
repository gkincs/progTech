package org.example.wumpus.service.map.impl;

import org.example.wumpus.Main;
import org.example.wumpus.service.map.MapReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MapReaderImpl implements MapReader {
    @Override
    public List<String> readMap() {
        String line;
        List<String> result = new ArrayList<>();
        InputStream inputStream = null;
        try {
            inputStream = Main.class.getClassLoader().getResource("map/beginner.txt").openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

            System.out.println(result);
        return result;
    }
}
