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
public class RainParticle extends Particle {
    private static Texture rain_tex = new Texture("effects/blood_sheet.png");
    private static ArrayList<TextureRegion> rain;
    private int id;

    public RainParticle(Vector3 pos, Vector3 vel) {
        super(pos, vel);
        duration = (int)((Math.random() * 120) + 120); // 240  240  420

        if (rain == null) {
            rain = new ArrayList<TextureRegion>();
            rain.add(new TextureRegion(rain_tex, 0, 0, 16, 16));


        }
        // img = images.get((int)(Math.random() * 5));
        id = (int)(Math.random() * rain.size());
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
        batch.draw(rain.get(id), pos.x-8, pos.y + pos.z - 8, 16, 16);
    }


}
