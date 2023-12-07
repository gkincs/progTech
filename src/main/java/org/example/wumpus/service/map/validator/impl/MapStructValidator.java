package org.example.wumpus.service.map.validator.impl;

import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.service.map.exception.MapValidationException;
import org.example.wumpus.service.map.validator.MapValidator;
import org.slf4j.Logger;

public class MapStructValidator implements MapValidator {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MapStructValidator.class);
    /**
     * @param mapVO
     */
    @Override
    public void validate(WumpusMapVO mapVO) throws MapValidationException {
    wumpusCheck(mapVO);
    goldCheck(mapVO);
    firstAndLastRowCheck(mapVO);
    }

    private void wumpusCheck(WumpusMapVO mapVO) throws MapValidationException {
        int counter = 0;
        char[][] map = mapVO.getMap();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'U') {
                    counter++;
                }
            }
        }
        if (map.length <= 8 && counter != 1) {
            LOGGER.error("Map size is less than 9. Allowed: 1 Wumpus.");
            throw new MapValidationException("Map size is less than 9. Allowed: 1 Wumpus.");
        }

        if (map.length <= 14 && map.length >= 9 && counter != 2) {
            LOGGER.error("Map size is less than 15. Allowed: 2 Wumpus.");
            throw new MapValidationException("Map size is less than 15. Allowed: 2 Wumpus.");
        }
        if (map.length <= 20 && map.length >= 14 && counter != 2) {
            LOGGER.error("Map size is less than 21. Allowed: 3 Wumpus.");
            throw new MapValidationException("Map size is less than 21. Allowed: 3 Wumpus.");
        }
    }
    private void goldCheck(WumpusMapVO mapVO) throws MapValidationException {
        int counter = 0;
        char[][] map = mapVO.getMap();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'G') {
                    counter++;
                }
            }
        }
        if (counter !=1) {
            LOGGER.error("Allowed: 1 Gold.");
            throw new MapValidationException("Allowed: 1 Gold.");
        }

    }
    private void firstAndLastRowCheck(WumpusMapVO mapVO) throws MapValidationException {
        char[][] map = mapVO.getMap();

        for (char element : map[0]) {
            if (element != 'W') {
                LOGGER.error("First row must contain wall.");
                throw new MapValidationException("First row must contain wall.");
            }
        }
        for (char element : map[map.length - 1]) {
            if (element != 'W') {
                LOGGER.error("Last row must contain wall.");
                throw new MapValidationException("Last row must contain wall.");
            }
        }
    }
}
