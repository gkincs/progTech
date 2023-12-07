package org.example.wumpus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.persist.db.DbPersister;
import org.example.wumpus.service.map.MapReader;
import org.example.wumpus.service.map.exception.MapParseException;
import org.example.wumpus.service.map.exception.MapReadException;
import org.example.wumpus.service.map.impl.BufferedReaderMapReader;
import org.example.wumpus.service.map.parser.MapParser;


public class Main {
    public static void main(String[] args) throws IOException, MapReadException, MapParseException, SQLException {

        WumpusMapVO test = new WumpusMapVO(new char[][]{{'w', 'w', 'e'}, {'w', 'w', 'e'}, {'w', 'w', 'e'}});

        DbPersister dbPersister = new DbPersister();
        dbPersister.writeMap("Test", test);
        WumpusMapVO read = dbPersister.readMap("Test");
        System.out.println(read);

        InputStream inputStream = Main.class.getClassLoader().getResource("map/wumpusinput.txt").openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        MapReader mapReader = new BufferedReaderMapReader(bufferedReader);
        List<String> stringList = mapReader.readMap();
        MapParser mapParser = new MapParser(6);
        WumpusMapVO wumpusMapVO = mapParser.parseMap(stringList);

        System.out.println(wumpusMapVO);
    }
}
