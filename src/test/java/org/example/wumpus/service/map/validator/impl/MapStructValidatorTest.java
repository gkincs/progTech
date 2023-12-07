package org.example.wumpus.service.map.validator.impl;

import org.example.wumpus.service.map.exception.MapParseException;
import org.example.wumpus.service.map.validator.MapValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.service.map.exception.MapValidationException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)

class MapStructValidatorTest {
    MapStructValidator underTest;
    @Mock
    WumpusMapVO mapVO;
    @BeforeEach
    void setUp() {
        underTest = new MapStructValidator(mapVO);
    }
    @Test
    void testValidateWithValidMap() {
        //Given
        char[][] expectedMap = new char[][]{
                {'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', 'P', 'W'},
                {'W', 'U', '_', 'P', '_', 'W'},
                {'W', '_', 'G', '_', '_', 'W'},
                {'W', '_', '_', 'P', '_', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W'}
        };

        given(mapVO.getMap()).willReturn(expectedMap);

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

        given(mapVO.getMap()).willReturn(expectedMap);

        //When, Then
        MapValidationException exception = assertThrows(MapValidationException.class, () -> {
            underTest.validate(mapVO);
        });
        assertEquals("Map size is less than 9. Allowed: 1 Wumpus.", exception.getMessage());
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
        given(mapVO.getMap()).willReturn(expectedMap);

        //When, Then
        MapValidationException exception = assertThrows(MapValidationException.class, () -> {
            underTest.validate(mapVO);
        });
        assertEquals("Map size is less than 15. Allowed: 2 Wumpus.", exception.getMessage());
    }

    @Test
    void testLargeInvalidMap(){
        //Given
        char[][] expectedMap = new char[][]{
                {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', 'P', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', 'P', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', 'P', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W', 'W', 'W', 'W','W', 'W', 'W', 'W', 'W', 'W'}

        };
        given(mapVO.getMap()).willReturn(expectedMap);

        //When, Then
        MapValidationException exception = assertThrows(MapValidationException.class, () -> {
            underTest.validate(mapVO);
        });
        assertEquals("Map size is less than 21. Allowed: 3 Wumpus.", exception.getMessage());
    }

    @Test
    void testValidateWithoutGold() {
        //Given
        char[][] expectedMap = new char[][]{
                {'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', 'P', 'W'},
                {'W', 'U', '_', 'P', '_', 'W'},
                {'W', '_', '_', '_', '_', 'W'},
                {'W', '_', '_', 'P', '_', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W'}
        };

        given(mapVO.getMap()).willReturn(expectedMap);

        //When, Then
        MapValidationException exception = assertThrows(MapValidationException.class, () -> {
            underTest.validate(mapVO);
        });
        assertEquals("Allowed: 1 Gold.", exception.getMessage());
    }
    @Test
    void testInvalidFirstWall() {
        //Given
        char[][] expectedMap = new char[][]{
                {'W', 'W', '_', 'W', 'W', 'W'},
                {'W', '_', '_', '_', 'P', 'W'},
                {'W', 'U', '_', 'P', '_', 'W'},
                {'W', '_', 'G', '_', '_', 'W'},
                {'W', '_', '_', 'P', '_', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W'}
        };

        given(mapVO.getMap()).willReturn(expectedMap);

        //When, Then
        MapValidationException exception = assertThrows(MapValidationException.class, () -> {
            underTest.validate(mapVO);
        });
        assertEquals("First row must contain wall.", exception.getMessage());
    }

    @Test
    void testInvalidLastWall() {
        //Given
        char[][] expectedMap = new char[][]{
                {'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', 'P', 'W'},
                {'W', 'U', '_', 'P', '_', 'W'},
                {'W', '_', 'G', '_', '_', 'W'},
                {'W', '_', '_', 'P', '_', 'W'},
                {'W', '_', 'W', 'W', 'W', 'W'}
        };

        given(mapVO.getMap()).willReturn(expectedMap);

        //When, Then
        MapValidationException exception = assertThrows(MapValidationException.class, () -> {
            underTest.validate(mapVO);
        });
        assertEquals("Last row must contain wall.", exception.getMessage());
    }
}
