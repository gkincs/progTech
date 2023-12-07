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

    /**
     * Az adatbázis-kezelő objektum létrehozása - Perzisztencia réteg.
     *
     * Ez a konstruktor inicializálja az adatbázis-kezelő objektumot.
     * H2 memóriában futó adatbázist használ, és inicializálja a kiindulási sémát a 'create.sql' fájlból.
     *
     * @throws RuntimeException Inicializálási hiba esetén,
     *         pl. JDBC kapcsolat hiba esetén.
     */

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

    /**
     * A térkép mentése H2 adatbázisba.
     *
     * Egy megadott nevű térképet ment el az adatbázisba. A térkép a WumpusMapVO objektumból
     * származik, és a GRID_LINES táblába kerül beszúrásra (soronként).
     * Minden sorban a grid neve, a sor tartalma (stringként) és a sor sorszáma szerepel.
     *
     * @param name A térkép neve.
     * @param map A mentésre kiválasztott térkép (WumpusMapVO objektumként).
     * @throws SQLException Adatbázis hiba esetén kivétel.
     */
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
            LOGGER.error(e.getMessage());
            }
    }

    /**
     * Térkép beolvasása az adatbázisból név alapján.
     *
     * Egy adott névvel azonosított térképet olvas be az adatbázisból. Lekérdezést használ
     * a GRID_LINES táblából a megadott név alapján. A lekért sorokból
     * egy char[][] térképet állít össze, ami a WumpusMapVO objektumban tér vissza.
     *
     * @param name A térkép neve, az adatbázis lekéréshez.
     * @return A beolvasott térképből származó WumpusMapVO objektum.
     * Ha valamilyen hiba történik az olvasás közben, üres térképpel tér vissza.
     */

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
