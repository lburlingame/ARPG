package com.haruham.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.level.Tile;
import com.haruham.game.level.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * Created on 8/27/2015.
 */
public class HealthGlobe extends Collidable {


    private static Texture globe_tex = new Texture("effects/fireball.png");
    private static ArrayList<TextureRegion> globes;


    private int amount;
   // private int id;

    public HealthGlobe(Vector3 pos, Vector3 vel, int amount) {
        if (globes == null)
        {
            globes.add(new TextureRegion(globe_tex,0,0,16,16));
        }

        this.pos = pos;
        this.vel = vel;
        this.dim = new Vector3(16, 16, 16);
        this.hit = new HitCircle(new Vector3(8, 8, 0), 12);

        this.amount = amount;

        //id = amount / 10;

    }


    public void tick() {
        if (pos.z < 0 || vel.z < 0) {
            vel.z += TileMap.GRAVITY;
            pos.z += vel.z;
        }
        if (pos.z > 0) {
            pos.z = 0;
        }

       /* if (pos.z != 0) {
            Tile curr = getTile(pos.x + (vel.x), pos.y);
            if (curr != null && curr.walkable) {
                pos.x += (vel.x);
            }

            curr = getTile(pos.x, pos.y + (vel.y));
            if (curr != null && curr.walkable) {
                pos.y += (vel.y);
            }
           *//* pos.x += vel.x;
            pos.y += vel.y;
*//*

        }*/

    }

    public void draw(SpriteBatch batch) {
        batch.draw(globes.get(0), pos.x-8, pos.y + pos.z - 8, 16, 16);
    }


}
