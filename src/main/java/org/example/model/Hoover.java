package org.example.model;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Hoover {
    private int x;
    private int y;

    public Hoover(int[] coords) {
        this.x = coords[0];
        this.y = coords[1];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveNorth(int roomSize) {
        if (roomSize - 1 > y) {
            y++;
        } else {
            log.info("Roomba hit wall");
        }
    }

    public void moveSouth() {
        if (y > 0) {
            y--;
        } else {
            log.info("Roomba hit wall");
        }
    }

    public void moveEast(int roomSize) {
        if (roomSize - 1 > x) {
            x++;
        } else {
            log.info("Roomba hit wall");
        }
    }

    public void moveWest() {
        if (x > 0) {
            x--;
        } else {
            log.info("Roomba hit wall");
        }
    }

    public int[] getCoords() {
        return new int[]{x, y};
    }
}
