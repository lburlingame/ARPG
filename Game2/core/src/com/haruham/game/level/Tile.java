package com.haruham.game.level;

public class Tile {

    public int x;
    public int y;
    public int z;
    public int width;
    public int height;
    public int id;
    public boolean walkable;

    public Tile(int x, int y, int z, int width, int height, int id, boolean walkable) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.id = id;
        this.walkable = walkable;
    }
}
