package com.haruham.game.obj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.gfx.TextureLoader;
import com.haruham.game.input.InputComponent;
import com.haruham.game.item.Weapon;
import com.haruham.game.level.World;


// enemy aggro could be cone based on vision, and circular based on noise, your noise level depends on whether you are running or walking or sprinting, and maybe even have a talent tree that reduces noise and stuff
// vision is reduced in poorly lit areas

//only calculates lighting on tiles that are either on _screen, or in between the player and an enemy that is in aggro range
//death() function called when hp = 0, death behavior on death is then called, will have a boolean dead, if dead then on the next for loop, the obj will be removed from the arraylist, or maybe moved to another arraylist to have its body still remain ticking.
public class Character extends Entity {

    private static int NEXT_UID = 0;
    public static final int STOPPED = 0;
    public static final int WALKING = 1;
    public static final int RUNNING = 2;
    public static final int SPRINTING = 3;
    public static final int DASHING =  4;
    public static final int JUMPING = -1;
    private int STATE = 0;
    private int PREV_STATE = 0; // ??

    private int id;
    private Integer UID;

    private Vector2 dest;  //destination

    private float vmult = 1;  // velocity multiplier

    private World world;
    private HealthComponent health;
    private Weapon weapon;
    private InputComponent input;

    private int gold;

    public Character(World world, int id, InputComponent input, Vector3 pos) {
        input.setCharacter(this);
        this.world = world;
        this.health = new PlayerHealth();
        this.id = id;
        this.UID = new Integer(NEXT_UID);
        NEXT_UID++;
        this.weapon = new Weapon(1, "Hello World", "Hi there friend", 25);

        this.input = input;
        this.smult = 1;

        this.pos = pos;
        this.dest = new Vector2(pos.x, pos.y);
        STATE = STOPPED;
        this.vel = new Vector3(0,0,0);

        this.dim = new Vector3(32 * smult, 32 * smult, 32 * smult);

        this.hit = new HitCircle(new Vector3(0, 0, 0), dim.x / 3);

        //this.hit = new HitCircle(new Vector3(dim.x * .667f, dim.z*.43f, 0), dim.x / 3);
        gold = (int)(Math.random() * 58) + 5;
    }

    public void changeSize() {
        smult *= 1.03;
        dim.x = 32 * smult;
        dim.y = 32 * smult;
        dim.z = 32 * smult;
        hit = new HitCircle(new Vector3(dim.x * .667f, dim.z*.43f, 0), dim.x / 4);
    }

    public void update(float delta) {
        input.update(delta);
/*
        if ((pos.x + (vel.x * vmult) > dest.x && vel.x > 0) || (pos.x + (vel.x  * vmult) < dest.x && vel.x < 0)) {
            vel.x = 0;
            pos.x = dest.x;
        }
        if ((pos.y + (vel.y * vmult) > dest.y && vel.y > 0) || (pos.y + (vel.y * vmult) < dest.y && vel.y < 0)) {
            vel.y = 0;
            pos.y = dest.y;
        }*/

        if (pos.z > 0 || vel.z > 0) {
            vel.z += -.8;
            pos.z += vel.z;
        }

        if (pos.z <= 0 && STATE == JUMPING) {
            pos.z = 0;
            vel.z = 0;
            STATE = PREV_STATE;
        }

     /*   Tile curr = getTile(pos.x + (vel.x * vmult), pos.y);
        if (curr != null && curr.walkable) {
        }

        curr = getTile(pos.x, pos.y + (vel.y * vmult));
        if (curr != null && curr.walkable) {
        }*/
        pos.x += (vel.x * vmult * delta);
        pos.y += (vel.y * vmult * delta);


        if ((vel.x != 0 || vel.y != 0) && STATE != JUMPING) {
            if (vmult < .75f) {
                STATE = WALKING;
            }else if (vmult >= .75f && vmult <= 1.25f) {
                STATE = RUNNING;
            }else if (vmult > 1.25f) {
                STATE = SPRINTING;
            }
        }else if (STATE != JUMPING){
            STATE = STOPPED;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(TextureLoader.getSprite(id, id++), pos.x-dim.x/2, pos.y - dim.y*.25f + pos.z, 32,32);
    }


/*
    public void move(Vector2 dest)
    {
        if (Util.findDistance(dest.x - pos.x, dest.y - pos.y) > BASE_VELOCITY * 1.1) {
            this.dest.x = dest.x;
            this.dest.y = dest.y;

            Direction dir = Util.findSlope(pos.x, pos.y, this.dest.x, this.dest.y);

            vel.x = Util.findX(BASE_VELOCITY, dir.slope) * dir.xdir;
            vel.y = dir.slope * vel.x;

            if (dir.slope == 200000 || dir.slope == -200000)
            {
                vel.y = BASE_VELOCITY * dir.slope / Math.abs(dir.slope);
            }
        }
    }*/


    public void walk() {
        vmult = .1f;//.5f;
    }

    public void run() {
        vmult = 1.0f;
    }

    public void sprint() {
        vmult = 1.5f;//1.5f;
    }

    public void stop() {
        dest.x = pos.x;
        dest.y = pos.y;
        vel.x = 0;
        vel.y = 0;
    }

    public void jump() {
        if (STATE != JUMPING) {
            vel.z = 10f;
            PREV_STATE = STATE;
            STATE = JUMPING;
        }
    }

    public Vector3 getPosition() {
        return new Vector3(pos.x,pos.y,pos.z);
    }

    public float getX() {
        return pos.x;
    }

    public void setX(float x) {
        this.pos.x = x;
        dest.x = x;
    }

    public float getY() {
        return pos.y;
    }

    public void setY(float y) {
        this.pos.y = y;
        dest.y = y;
    }

    public float getZ() {
        return pos.z;
    }

    public float getDy() {
        return vel.y;
    }

    public float getDx() {
        return vel.x;
    }

    public void setDy(float dy) {
        this.vel.y = dy;
    }

    public void setDx(float dx) {
        this.vel.x = dx;
    }

    public float getDest_y() {
        return dest.y;
    }

    public float getDest_x() {
        return dest.x;
    }

    public float getWidth() {
        return dim.x;
    }

    public float getHeight() {
        return dim.z;
    }

    public void setInput(InputComponent input) {
        this.input = input;
        input.setCharacter(this);
    }

    public InputComponent getInput() {
        return input;
    }



    public int getState() {
        return STATE;
    }

    public int getID() {
        return id;
    }

    public Integer getUID() { return UID; }

    public int getGold() {
        return gold;
    }

    public void grabGold(int gold) {
        this.gold += gold;
    }

    public void heal(int amount) {

    }

    public Vector2[] getVertices() {
        return new Vector2[] {
                new Vector2(pos.x, pos.y),
                new Vector2(pos.x + dim.x, pos.y),
        };
    }

    public void attack(Vector3 target) {
        weapon.attack(world, this, target);
    }
}


/* attack(Entity target)

    target.takeDamage(AttackReport rep)

    sprite implements collidable
    fireball implements collidable

 */


/*
 public Vector3 feet;
    feet = IsoCalculator.isoTo2D(new Vector2((dim.x/2), (dim.z/8*7)));

 */
