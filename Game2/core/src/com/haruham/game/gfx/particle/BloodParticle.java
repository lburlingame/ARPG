package com.haruham.game.gfx.particle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.level.TileMap;

import java.util.ArrayList;

/**
 * Created on 5/5/2015.
 */
public class BloodParticle extends Particle {
    private static Texture blood_tex = new Texture("effects/blood_sheet.png");
    private static ArrayList<TextureRegion> blood;
    private int id;

    public BloodParticle(Vector3 pos, Vector3 vel) {
        super(pos, vel);
        duration = (int)((Math.random() * 120) + 120); // 240  240  420

        if (blood == null) {
            blood = new ArrayList<>();
            blood.add(new TextureRegion(blood_tex,0,0,16,16));
            blood.add(new TextureRegion(blood_tex,16,0,16,16));
            blood.add(new TextureRegion(blood_tex,32,0,16,16));
            blood.add(new TextureRegion(blood_tex,48,0,16,16));
            blood.add(new TextureRegion(blood_tex,64,0,16,16));

        }
       // img = images.get((int)(Math.random() * 5));
        id = (int)(Math.random() * blood.size());
    }

    public void update(float delta) {
        if (pos.z > 0 || vel.z > 0) {
            vel.z += TileMap.GRAVITY * delta;
            pos.z += vel.z * delta;
        }
        if (pos.z < 0) {
            pos.z = 0;
        }

        if (pos.z != 0) {
            pos.x += vel.x * delta;
            pos.y += vel.y * delta;
        }

        if (duration > 0) {
           duration--;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(blood.get(id), pos.x-8, pos.y + pos.z - 8, 16, 16);
    }


}
