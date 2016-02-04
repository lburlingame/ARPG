package com.haruham.game.obj;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.level.World;

import java.util.ArrayList;

/**
 * Created on 2/2/2016.
 */
public class Obstacle extends Entity {
    private static Texture tex = new Texture("sprites/trees.png");
    private static ArrayList<TextureRegion> trees;
    private int id;
    private float alpha = 1f;

    public Obstacle(World world, Vector3 pos) {
        this.world = world;
        this.pos = pos;
        this.dim = new Vector3(105,135,135);
        this.hit = new HitCircle(new Vector3(0,0,0), 24);

        if (trees == null) {
            trees = new ArrayList<TextureRegion>();
            for (int i = 20; i < 10 + 105*4; i+=120) {
                trees.add(new TextureRegion(tex,i,15,105,135));
            }

        }
        // img = images.get((int)(Math.random() * 5));
        id = (int)(Math.random() * trees.size());
    }

    public void update(float delta) {

    }

    public void draw(SpriteBatch batch) {
        if (pos.x + dim.x > world.getCamX() - world.getCamWidth() / 2 && pos.x - dim.x < world.getCamX() + world.getCamWidth() /2
        && pos.y + dim.y > world.getCamY() - world.getCamHeight() / 2 && pos.y - dim.y < world.getCamY() + world.getCamHeight() / 2) {
            Color c = batch.getColor();
            Character player = world.getPlayer();
            if (player.getX() > pos.x - dim.x/2 && player.getX() < pos.x + dim.x/2 &&
                    player.getY() > pos.y && player.getY() < pos.y + dim.y) {
                alpha = MathUtils.lerp(alpha, .35f, .075f);

            }else{
                if (alpha < 1f) {
                    alpha = MathUtils.lerp(alpha, 1f, .075f);
                }
            }
            batch.setColor(c.r, c.g, c.b, alpha);
            batch.draw(trees.get(id), pos.x - dim.x/2, pos.y - dim.y * .15f + pos.z, 105, 135);
            batch.setColor(c.r, c.g, c.b, 1); //set alpha to 1
        }
    }
}
