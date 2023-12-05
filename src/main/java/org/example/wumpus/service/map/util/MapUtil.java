/*package org.example.wumpus.service.map.util;

import org.example.wumpus.model.WumpusMapVO;

import java.util.ArrayList;
import java.util.List;

public class MapUtil {
    public List<Integer> getRowWithIndex(WumpusMapVO mapVO, int rowIndex) {
        List<Integer> result = new ArrayList<>();

        int[][] map = WumpusMapVO.getMap();
        for (int i = 0; i < mapVO.getNumberOfColumns(); i++) {
            result.add(map[rowIndex][i]);
        }

        return result;
    }

    public List<Integer> getColumnWithIndex(WumpusMapVO mapVO, int columnIndex) {
        List<Integer> result = new ArrayList<>();

        int[][] map = WumpusMapVO.getMap();
        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            result.add(map[i][columnIndex]);
        }

        return result;
    }

    public List<Integer> getBoxValues(WumpusMapVO mapVO, BoxDescription boxDescription) {
        List<Integer> result = new ArrayList<>();

        int[][] map = WumpusMapVO.getMap();
        int minRowIndex = boxDescription.getMinRowIndex();
        int maxRowIndex = boxDescription.getMaxRowIndex();
        int minColumnIndex = boxDescription.getMinColumnIndex();
        int maxColumnIndex = boxDescription.getMaxColumnIndex();

        for (int i = minRowIndex; i < maxRowIndex; i++) {
            for (int j = minColumnIndex; j < maxColumnIndex; j++) {
                result.add(map[i][j]);
            }
        }

        return result;
    }
}
*/