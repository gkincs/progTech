package org.example.wumpus.service.map.validator;

import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.service.map.exception.MapValidationException;

public interface MapValidator {
    void validate(WumpusMapVO mapVO) throws MapValidationException;
}
