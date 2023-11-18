package org.example.wumpus;

import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.service.map.MapReader;
import org.example.wumpus.service.map.exception.MapParseException;
import org.example.wumpus.service.map.exception.MapReadException;
import org.example.wumpus.service.map.impl.BufferedReaderMapReader;
import org.example.wumpus.service.map.parser.MapParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, MapReadException, MapParseException {
        InputStream inputStream = Main.class.getClassLoader().getResource("map/beginner.txt").openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        MapReader mapReader = new BufferedReaderMapReader(bufferedReader);
        List<String> stringList = mapReader.readMap();
        MapParser mapParser = new MapParser(6);
        WumpusMapVO wumpusMapVO = mapParser.parseMap(stringList);

        System.out.println(wumpusMapVO);
    }
}
