package com.haruham.game._excl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.entity.Collidable;
import com.haruham.game.entity.HitCircle;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Created on 8/27/2015.
 */
public class HealthGlobe extends Collidable {


    private static BufferedImage GLOBE;
    private static Sprite GLOBE_SHEET = new Sprite(new Texture("/sprites/health_globe.png"));


    private int amount;
   // private int id;

    public HealthGlobe(Vector3 pos, Vector3 vel, int amount) {
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

        if (pos.z != 0) {
            Tile curr = getTile(pos.x + (vel.x), pos.y);
            if (curr != null && curr.walkable) {
                pos.x += (vel.x);
            }

            curr = getTile(pos.x, pos.y + (vel.y));
            if (curr != null && curr.walkable) {
                pos.y += (vel.y);
            }
           /* pos.x += vel.x;
            pos.y += vel.y;
*/

        }

    }

    public void draw(Graphics g, Camera camera) {
        Vector3 offset = camera.getOffset();
        double scale = camera.getScale();

        g.setColor(new Color(0, 0, 0, (int)(DayCycle.max_darkness * 110 + 40)));
        g.fillOval((int)((pos.x - offset.x) * scale - .5),(int)((pos.y - offset.y + dim.z/1.75)* scale),(int)(dim.x*scale),(int)(dim.z*scale)/2);



        g.drawImage(GLOBE, (int) ((pos.x - offset.x) * scale), (int) ((pos.y - offset.y + pos.z) * scale), (int) (16 * scale), (int) (16 * scale), null);
        if (Game.debug) {
            g.setColor(Color.green);
            g.drawOval((int) ((pos.x + hit.getCenter().x - hit.getRadius() - offset.x) * scale), (int) ((pos.y + hit.getCenter().y - hit.getRadius() - offset.y) * scale), (int) (hit.getRadius() * scale * 2), (int) (hit.getRadius() * scale * 2));
        }

    }


    //fix hit detection on globe and coins, its a little higher than the drawn circle
    public boolean collidesWith(Collidable other) {
        Vector3 hitCenter = hit.getCenter();

        Vector3 opos = other.getPosition();
        HitCircle o = other.getHit();
        Vector3 oCenter = o.getCenter();

        if (Util.findDistance((pos.x + hitCenter.x) - (opos.x + oCenter.x), (pos.y + hitCenter.y) - (opos.y + oCenter.y)) <= (hit.getRadius() + o.getRadius())) {
            return true;
        }

        return false;
    }
}
