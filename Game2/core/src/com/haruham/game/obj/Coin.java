package com.haruham.game.obj;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.level.TileMap;
import com.haruham.game.level.World;

import java.util.ArrayList;


/**
 * Created on 8/27/2015.
 */
public class Coin extends Pickup {
    private static Texture coin_tex = new Texture("sprites/coins_sheet.png");
    private static ArrayList<TextureRegion> coins;


    private int amount;
    private int id;

    public Coin(World world, Vector3 pos, Vector3 vel, int amount) {
        this.world = world;
        if (coins == null)
        {
            coins = new ArrayList<TextureRegion>();
            for (int i = 0; i < 7; i++) {
                coins.add(new TextureRegion(coin_tex,i*16,0,16,16));
            }
        }
        this.pos = pos;
        this.vel = vel;
        this.dim = new Vector3(16, 16, 16);
        this.hit = new HitCircle(new Vector3(0, 0, 0), 8);

        this.amount = amount;

        id = amount / 10;
        if (id > coins.size()-1) {
            id = coins.size()-1;
        }
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
        /* Tile curr = getTile(pos.x + (vel.x), pos.y);
            if (curr != null && curr.walkable) {
                pos.x += (vel.x);
            }

            curr = getTile(pos.x, pos.y + (vel.y));
            if (curr != null && curr.walkable) {
                pos.y += (vel.y);
            }*/
           /* pos.x += vel.x;
            pos.y += vel.y;
*/





    public void draw(SpriteBatch batch) {
        if (pos.x + dim.x > world.getCamX() - world.getCamWidth() / 2 && pos.x - dim.x < world.getCamX() + world.getCamWidth() /2
                && pos.y + dim.y > world.getCamY() - world.getCamHeight() / 2 && pos.y - dim.y < world.getCamY() + world.getCamHeight() / 2) {
            batch.draw(coins.get(id), pos.x - 8, pos.y + pos.z - 8, 16, 16);
        }
    }

    @Override
    public void pickup(Character character) {
        character.grabGold(amount);
    }

    @Override
    public void drawDebug(ShapeRenderer renderer) {
        Color color = renderer.getColor();
        renderer.setColor(Color.GOLD);
        renderer.circle(pos.x + hit.getCenter().x, pos.y + hit.getCenter().y, hit.getRadius());
        renderer.setColor(color);
    }

    public int getAmount() {
        return amount;
    }


}
