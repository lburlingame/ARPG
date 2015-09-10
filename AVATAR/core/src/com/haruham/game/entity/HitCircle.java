package com.haruham.game.entity;


import com.badlogic.gdx.math.Vector3;

/**
 * Created on 5/9/2015.
 */
public class HitCircle {

    private Vector3 center;
    private float radius;

    public HitCircle(Vector3 center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    public Vector3 getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

}
