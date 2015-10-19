package com.haruham.game.item;

import com.badlogic.gdx.math.Vector3;
import com.haruham.game.obj.*;
import com.haruham.game.level.World;
import com.haruham.game.obj.Character;

/**
 * Created on 9/17/2015.
 */
public class Weapon extends Equipment {

    private int damage;
    private float attackTime; // time in seconds between attacks
    private float charge;
    private static final float MAX_CHARGE = 1500;

    public Weapon(int itemID, String name, String description, int levelReq) {
        super(itemID, name, description, levelReq);
        damage = 100;
        attackTime = 1.5f;
        charge = 0;
    }

    public void charge(World world, Character user, Vector3 target) {

    }

    public void attack(World world, Character user, Vector3 target) {
        target = new Vector3(target);
        world.addAttack(new AttackObject(user, new Lightning(), new BasicCollision(damage), target, name));
        world.addAttack(new AttackObject(user, new Lightning(), new BasicCollision(damage), target, name));
        world.addAttack(new AttackObject(user, new Lightning(), new BasicCollision(damage), target, name));
        world.addAttack(new AttackObject(user, new Lightning(), new BasicCollision(damage), target, name));







      /*  world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
        world.addAttack(new Attack(user, new Projectile(48), new BasicCollision(damage),  target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0)), name));
*/


        // based on distance from camera center

        /*world.addAttack(new Attack(user, "Melee attack", 16, target.add(50,50,0)));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(-50,50,0)));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(50,-50,0)));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(-50,-50,0)));*/

        /*world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));*/
        /*world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*100,(float)Math.random()*100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));
        world.addAttack(new Attack(user, "Melee attack", 16, target.add(new Vector3((float)Math.random()*-100,(float)Math.random()*-100f,0))));*/

    }
}
