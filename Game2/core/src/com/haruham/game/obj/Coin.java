package com.haruham.game.obj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;


/**
 * Created on 8/27/2015.
 */
public class Coin extends Entity {
    private static Texture coin_tex = new Texture("effects/fireball.png");
    private static ArrayList<TextureRegion> coins;


    private int amount;
    private int id;

    public Coin(Vector3 pos, Vector3 vel, int amount) {
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
        this.hit = new HitCircle(new Vector3(8, 8, 0), 12);

        this.amount = amount;

        id = amount / 10;
        if (id > coins.size()-1) {
            id = coins.size()-1;
        }
    }


    public void update(float delta) {
        if (pos.z < 0 || vel.z < 0) {
            vel.z += 5;
            pos.z += vel.z;
        }
        if (pos.z > 0) {
            pos.z = 0;
        }

        if (pos.z != 0) {
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

        }

    }

    public void draw(SpriteBatch batch) {
        batch.draw(coins.get(id), pos.x - 8, pos.y - 8, 16,16);
    }


    public int getAmount() {
        return amount;
    }


}
