package com.haruham.game.gfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.obj.Entity;

/**
 * Created on 1/1/2016.
 */
public class Light{

    private Vector3 pos;
    private float radius;

    public Light(int x, int y, float radius) {
        pos = new Vector3(x,y,0);
        this.radius = radius;
    }

    public Light(Vector3 pos, float radius) {
        this.pos = new Vector3(pos);
        this.radius = radius;
    }


    public void update(float delta) {

    }





    public void setRadius(float radius) {
        this.radius = radius;
    }



    public Vector3 getPosition() {
        return new Vector3(pos);
    }
}
