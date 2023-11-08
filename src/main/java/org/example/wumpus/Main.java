package org.example.wumpus;

import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.service.map.MapReader;
import org.example.wumpus.service.map.impl.MapReaderImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        int[][] map = {
                {0, 1},
                {2, 3}
        };
        boolean[][] fixed = {
                {false, true},
                {true, true}
        };

        WumpusMapVO wumpusMapVO = new WumpusMapVO(2,2, map, fixed);


        System.out.println(wumpusMapVO);

        MapReader mapReader = new MapReaderImpl();
        List<String> stringList = mapReader.readMap();
        System.out.println(stringList);
    }
}