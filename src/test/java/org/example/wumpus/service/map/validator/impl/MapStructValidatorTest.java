package org.example.wumpus.service.map.validator.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.service.map.exception.MapValidationException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class MapStructValidatorTest {
    MapStructValidator underTest;
    @BeforeEach
    void setUp() {
        underTest = new MapStructValidator();
    }
    @Test
    void testValidateWithValidMap() {
        //Given
        char[][] expectedMap = new char[][]{
                {'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', 'P', 'W'},
                {'W', 'U', '_', 'P', '_', 'W'},
                {'W', '_', '_', '_', '_', 'W'},
                {'W', '_', '_', 'P', '_', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W'}
        };

        WumpusMapVO mapVO = new WumpusMapVO(expectedMap);
        //When, Then
        assertDoesNotThrow(() -> underTest.validate(mapVO));
    }

    @Test
    void testValidateWithInvalidMapSize() {
        //Given
        char[][] expectedMap = new char[][]{
                {'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', 'P', 'W'},
                {'W', '_', '_', 'P', '_', 'W'},
                {'W', '_', '_', '_', '_', 'W'},
                {'W', '_', '_', 'P', '_', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W'}
        };
        WumpusMapVO mapVO = new WumpusMapVO(expectedMap);

        //When, Then
        assertThrows(MapValidationException.class,() -> underTest.validate(mapVO));

    }

    @Test
    void testValidateWithInvalidWumpusCount() {
        //Given
        char[][] expectedMap = new char[][]{
                {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', 'P', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
        };
        WumpusMapVO mapVO = new WumpusMapVO(expectedMap);

        //When, Then
        assertThrows(MapValidationException.class,() -> underTest.validate(mapVO));
    }
    @Test
    void testLargeInvalidMap(){
        //Given
        char[][] expectedMap = new char[][]{
                {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', 'P', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', 'P', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
        };
        WumpusMapVO mapVO = new WumpusMapVO(expectedMap);

        //When, Then
        assertThrows(MapValidationException.class,() -> underTest.validate(mapVO));
    }
}
