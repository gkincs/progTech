package org.example.wumpus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.example.wumpus.model.GameState;
import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.persist.db.DbPersister;
import org.example.wumpus.persist.json.JsonPersister;
import org.example.wumpus.service.map.MapReader;
import org.example.wumpus.service.map.exception.MapParseException;
import org.example.wumpus.service.map.exception.MapReadException;
import org.example.wumpus.service.map.impl.BufferedReaderMapReader;
import org.example.wumpus.service.map.parser.MapParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, MapReadException, MapParseException, SQLException {
        GameState gameState = new GameState();

        Scanner username = new Scanner(System.in);
        System.out.println("Enter username: ");
        String name = username.nextLine();
        gameState.setUsername(name);

        WumpusMapVO test = new WumpusMapVO(new char[][]{{'w', 'w', 'e'}, {'w', 'w', 'e'}, {'w', 'w', 'e'}});

        DbPersister dbPersister = new DbPersister();
        dbPersister.writeMap(gameState.getUsername(), test);
        WumpusMapVO read = dbPersister.readMap(gameState.getUsername());
        System.out.println(read);
        LOGGER.info("Database Load");

        JsonPersister jsonPersister = new JsonPersister();
        jsonPersister.writeMap(gameState.getUsername(), test);
        WumpusMapVO readJson = jsonPersister.readMap(gameState.getUsername());
        System.out.println(readJson);
        LOGGER.info("Json File Load");

        InputStream inputStream = Main.class.getClassLoader().getResource("map/wumpusinput.txt").openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        MapReader mapReader = new BufferedReaderMapReader(bufferedReader);
        List<String> stringList = mapReader.readMap();
        MapParser mapParser = new MapParser(6);
        WumpusMapVO wumpusMapVO = mapParser.parseMap(stringList);
        LOGGER.info("Input File Load");
        System.out.println(wumpusMapVO);
    }
}
