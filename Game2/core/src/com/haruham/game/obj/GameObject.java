package com.haruham.game.obj;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.util.Util;

/**
 * Created on 8/21/2015.
 */
public abstract class GameObject implements Comparable<GameObject>{

    protected Vector3 pos;   //position
    protected Vector3 dim;   //dimensions
    protected float smult; // size multiplier
    protected Vector3 vel;



    public Vector3 getPosition() {
        return new Vector3(pos.x, pos.y, pos.z);
    }

    // all hit circles have an angle, if angle is 360, then it wont calculate cone stuff, if its less than 360, then it will calculate cone based collison, then there wont need to be an abstract collidewith method and it will be only in superclass
   // public abstract boolean collidesWith(Collidable other);


    @Override
    public int compareTo(GameObject o) {
        if (this.pos.y < o.getY()) {
            return -1;
        }else if (this.pos.y >  o.getY()) {
            return 1;
        }
        return 0;
    }

    public abstract void update(float delta);
    public abstract void draw(SpriteBatch batch);
    public abstract void drawDebug(ShapeRenderer shapeRenderer);


    //public abstract int compareTo(GameObject o);

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public float getZ() {
        return pos.z;
    }

    public float getDx() {
        return vel.x;
    }

    public float getDy() {
        return vel.y;
    }
}
