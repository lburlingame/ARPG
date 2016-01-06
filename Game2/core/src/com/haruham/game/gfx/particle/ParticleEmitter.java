package com.haruham.game.gfx.particle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created on 5/5/2015.
 */
public class ParticleEmitter {

    private ArrayList<Particle> particles;
    private Random rand = new Random();
    public ParticleEmitter() {
        particles = new ArrayList<Particle>();
    }

    public void bloodSpatter(Vector3 pos, Vector3 vel, int amount) {
        for (int i = 0; i < amount; i++) {
            float random_dx = rand.nextFloat() * 180 - 90;
            float random_dy = rand.nextFloat() * 180 - 90;
            float random_dz = rand.nextFloat() * 180 - 90;
            particles.add(new BloodParticle(new Vector3(pos.x, pos.y, pos.z+16), new Vector3(vel.x + random_dx, vel.y + random_dy, vel.z + random_dz)));
        }
    }

    public void update(float delta) {
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).getDuration() == 0) {
                particles.remove(i);
                i--;
            }else{
                particles.get(i).update(delta);
            }
        }
    }


    public void draw(SpriteBatch batch) {
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).draw(batch);
        }
        //batch.setColor(new Color(1,1,1,1));
    }

    public void clear() {
        System.out.println(particles.size());
        particles.clear();
    }
}
