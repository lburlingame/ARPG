package com.haruham.game.obj;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.gfx.Art;
import com.haruham.game.gfx.TextureLoader;
import com.haruham.game.level.TileMap;
import com.haruham.game.level.World;

import java.util.ArrayList;


/**
 * Created on 8/27/2015.
 */
public class HealthGlobe extends Pickup {


    private static Texture globe_tex = new Texture("sprites/health_globe.png");
    private static ArrayList<TextureRegion> globes;


    private int amount;
   // private int id;

    public HealthGlobe(World world, Vector3 pos, Vector3 vel, int amount) {
        this.world = world;

        if (globes == null)
        {
            globes = new ArrayList<TextureRegion>();
            globes.add(new TextureRegion(globe_tex,0,0,16,16));
        }

        this.pos = pos;
        this.vel = vel;
        this.dim = new Vector3(16, 16, 16);
        this.hit = new HitCircle(new Vector3(0, 0, 0), 8);

        this.amount = amount;

        //id = amount / 10;

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
    }

    public void draw(SpriteBatch batch) {
        if (pos.x + dim.x > world.getCamX() - world.getCamWidth() / 2 && pos.x - dim.x < world.getCamX() + world.getCamWidth() /2
                && pos.y + dim.y > world.getCamY() - world.getCamHeight() / 2 && pos.y - dim.y < world.getCamY() + world.getCamHeight() / 2) {
            batch.draw(Art.shadow16, pos.x - 8, pos.y - dim.z * .625f, 16,8);
            batch.draw(globes.get(0), pos.x - 8, pos.y + pos.z - 8, 16, 16);

        }
    }

    @Override
    public void pickup(Character character) {
        character.heal(amount);
    }

    // CYAN for pickups
    @Override
    public void drawDebug(ShapeRenderer renderer) {
        Color color = renderer.getColor();
        renderer.setColor(Color.PURPLE);
        renderer.circle(pos.x + hit.getCenter().x, pos.y + hit.getCenter().y, hit.getRadius());
        renderer.setColor(color);
    }

}
