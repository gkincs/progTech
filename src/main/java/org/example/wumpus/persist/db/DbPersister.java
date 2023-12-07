package org.example.wumpus.persist.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.wumpus.model.WumpusMapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbPersister {
    String url;
    private static final Logger LOGGER = LoggerFactory.getLogger(DbPersister.class);
    private final Connection connection;

    public DbPersister() {
        try {
            Class.forName("org.h2.Driver");
            URI resFolder = getClass().getClassLoader().getSystemResource("").toURI();
            String resPath = Paths.get(resFolder).toString().replace('\\', '/');
            url = String.format("jdbc:h2:mem:test;INIT=runscript from '%s';",
                    resPath + "/create.sql");
            this.connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeMap(String name, WumpusMapVO map) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO GRID_LINES (grid_name, line, row_nr) VALUES(?, ?, ?)")) {
            char[][] gameMap = map.getMap();
                statement.setString(1, name);
                int recordCount = 0;
                for (int i = 0; i < gameMap.length; i++) {
                    String line = String.valueOf(gameMap[i]);

                    statement.setString(2, line);
                    statement.setInt(3, i + 1);
                    recordCount += statement.executeUpdate();
                }
        } catch (Exception e) {
            LOGGER.error("Nem tudtam menteni" + e.getMessage());
            }
    }

    public WumpusMapVO readMap(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM GRID_LINES WHERE grid_name = ?");
            PreparedStatement lineSize = connection.prepareStatement("SELECT COUNT(*) FROM GRID_LINES WHERE grid_name = ?");
            statement.setString(1, name);
            lineSize.setString(1, name);

            ResultSet lineResult = statement.executeQuery();
            ResultSet sizeResult = lineSize.executeQuery();
            sizeResult.next();
           int sizeOfMap = sizeResult.getInt(1);

            char[][] readLines = new char[sizeOfMap][sizeOfMap];
            while (lineResult.next()) {
                int indexOfLine = lineResult.getInt("row_nr");
               String returnedLine = lineResult.getString("line");
                readLines [indexOfLine - 1] = returnedLine.toCharArray();
            }
            return new WumpusMapVO(readLines);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new WumpusMapVO(new char[1][0]);
        }
    }
}
