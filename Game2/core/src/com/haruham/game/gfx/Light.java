package com.haruham.game.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.obj.Entity;

/**
 * Created on 1/1/2016.
 */
public class Light{

    public static final float speed = 12.0f;
    public static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;

    private Vector3 pos;
    private float radius;
    private float angle;
    private Color color;

    public Light(int x, int y, float radius) {
        pos = new Vector3(x,y,0);
        this.radius = radius;
    }

    public Light(Vector3 pos, float radius) {
        this.pos = new Vector3(pos);
        this.radius = radius;
    }


    public void update(float delta) {
        angle += .014 * 60 * speed * delta;
        if(angle > PI2) {
            angle -= PI2;
        }
    }

    public void draw(SpriteBatch batch) {
        float lightRadius = radius + radius * .01f * MathUtils.sin(angle) + .2f * MathUtils.random();
        batch.draw(Art.light, pos.x - lightRadius, pos.y - lightRadius, lightRadius * 2, lightRadius * 2);
    }





    public void setRadius(float radius) {
        this.radius = radius;
    }



    public Vector3 getPosition() {
        return new Vector3(pos);
    }
}
