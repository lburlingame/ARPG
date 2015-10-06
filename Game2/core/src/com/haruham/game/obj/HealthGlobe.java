package com.haruham.game.obj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.level.TileMap;

import java.util.ArrayList;


/**
 * Created on 8/27/2015.
 */
public class HealthGlobe extends GameObject {


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


    public void update(float delta) {
        if (pos.z < 0 || vel.z < 0) {
            vel.z += TileMap.GRAVITY;
            pos.z += vel.z;
        }
        if (pos.z > 0) {
            pos.z = 0;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(globes.get(0), pos.x-8, pos.y + pos.z - 8, 16, 16);
    }


    @Override
    public int compareTo(GameObject o) {
        return 0;
    }
}
