package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HooverTest {

    @Test
    public void testGetX() {
        Hoover hoover = new Hoover(new int[]{0, 0});
        assertEquals(0, hoover.getX());
    }

    @Test
    public void testGetY() {
        Hoover hoover = new Hoover(new int[]{0, 0});
        assertEquals(0, hoover.getY());
    }

    @Test
    public void testMoveNorth() {
        Hoover hoover = new Hoover(new int[]{0, 0});
        hoover.moveNorth(5);
        assertEquals(1, hoover.getY());
    }

    @Test
    public void testMoveNorthWithWall() {
        Hoover hoover = new Hoover(new int[]{0, 4});
        hoover.moveNorth(5);
        assertEquals(4, hoover.getY());
    }

    @Test
    public void testMoveSouth() {
        Hoover hoover = new Hoover(new int[]{0, 1});
        hoover.moveSouth();
        assertEquals(0, hoover.getY());
    }

    @Test
    public void testMoveSouthWithWall() {
        Hoover hoover = new Hoover(new int[]{0, 0});
        hoover.moveSouth();
        assertEquals(0, hoover.getY());
    }

    @Test
    public void testMoveEast() {
        Hoover hoover = new Hoover(new int[]{1, 0});
        hoover.moveEast(5);
        assertEquals(2, hoover.getX());
    }

    @Test
    public void testMoveEastWithWall() {
        Hoover hoover = new Hoover(new int[]{4, 0});
        hoover.moveEast(5);
        assertEquals(4, hoover.getX());
    }

    @Test
    public void testMoveWest() {
        Hoover hoover = new Hoover(new int[]{1, 0});
        hoover.moveWest();
        assertEquals(0, hoover.getX());
    }

    @Test
    public void testMoveWestWithWall() {
        Hoover hoover = new Hoover(new int[]{0, 0});
        hoover.moveWest();
        assertEquals(0, hoover.getX());
    }

    @Test
    public void testGetCoords() {
        Hoover hoover = new Hoover(new int[]{3, 4});
        assertArrayEquals(new int[]{3, 4}, hoover.getCoords());
    }
}