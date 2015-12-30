package com.haruham.game.gfx;


import com.badlogic.gdx.math.Vector2;

public class Block {
    public int x, y, width, height;

    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Vector2[] getVertices() {
        return new Vector2[] {
                new Vector2(x, y),
                new Vector2(x, y + height),
                new Vector2(x + width, y + height),
                new Vector2(x + width, y)
        };
    }
}