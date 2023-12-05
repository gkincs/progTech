package org.example.wumpus.persist.db;

import org.example.wumpus.model.WumpusMapVO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbPersister {
    String url;
    public DbPersister() {
        try {
            Class.forName("org.h2.Driver");
            String resFolder = getClass().getClassLoader().getResource("").getPath();
            url= String.format("jdbc:h2:mem:test;INIT=runscript from '%s';",
                    resFolder+"create.sql");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void writeMap(String name, WumpusMapVO map ) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url)) {
            char[][] gameMap = map.getMap();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO GRID_LINES (grid_name, line, row_nr) VALUES(?, ?, ?)");
                statement.setString(1, name);
                int recordCount = 0;
                for (int i = 0; i < gameMap.length; i++) {
                    String line= String.valueOf(gameMap[i]);

                    statement.setString(2, line);
                    statement.setInt(3, i + 1);
                    recordCount += statement.executeUpdate();}
        }catch(Exception e){

            }
    }
}
