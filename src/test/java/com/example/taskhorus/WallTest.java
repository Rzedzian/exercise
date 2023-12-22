package com.example.taskhorus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WallTest {

    @Mock
    CompositeBlock compositeBlockMock;

    @InjectMocks
    Wall wall;

    @Mock
    Block blockMock1;

    @Mock
    Block blockMock2;

    List<Block> blocks;

    @BeforeEach
    void init() {
        blocks = Arrays.asList(blockMock1, blockMock2);
        when(compositeBlockMock.getBlocks()).thenReturn(blocks);
        wall = new Wall(compositeBlockMock);
    }

    @Test
    void testFindBlockByColor_ResultsInBlockBeingReturned() {
        when(blockMock1.getColor()).thenReturn("Red");
        Optional<Block> result = wall.findBlockByColor("Red");


        assertNotNull(result);
        assertEquals("Red", result.get().getColor());
    }

    @Test
    void testFindBlocksByMaterial_HappyPath_ResultsInBlockListBeingReturned() {

        when(blockMock1.getMaterial()).thenReturn("Concrete");
        when(blockMock2.getMaterial()).thenReturn("Concrete");

        List<Block> result = wall.findBlocksByMaterial("Concrete");

        assertEquals(result, blocks);
    }

    @Test
    void testFindBlocksByMaterial_ReturnEmptyList_ResultsInBlockEmptyListBeingReturned() {

        when(blockMock1.getMaterial()).thenReturn("Concrete");
        when(blockMock2.getMaterial()).thenReturn("Concrete");

        List<Block> result = wall.findBlocksByMaterial("Wood");

        assertTrue(result.isEmpty());
    }

    @Test
    void testCount_ResultsCountListBeingReturned() {

        assertEquals(wall.count(), 2);
    }

}