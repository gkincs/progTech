/*package org.example.wumpus.service.map.validator.impl;

import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.service.map.validator.MapValidator;

import java.util.List;

public class MapByRowValidator implements MapValidator {
    @Override
    public void validate(WumpusMapVO mapVO) {
        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            List<Integer> row = mapUtil.getRowWithIndex(mapVO, i);
            List<Integer> nonZeroValues = collectionUtil.getNonZeroValues(row);
    }
}
*/