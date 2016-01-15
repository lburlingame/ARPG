package com.haruham.game.item;

import com.badlogic.gdx.math.Vector3;
import com.haruham.game.level.World;
import com.haruham.game.obj.*;
import com.haruham.game.obj.Character;

/**
 * Created on 9/17/2015.
 */
public class Weapon extends Equipment {

    private int damage;
    private float attackTime; // time in seconds between attacks
    private float charge;
    private static final float MAX_CHARGE = 1500;
    private float remainingCD = 0;
    private float cooldown = 500;
    private AttackMethod method;
    private AttackBehavior behavior;

    public Weapon(int itemID, String name, String description, int levelReq) {
        super(itemID, name, description, levelReq);
        damage = 25;
        attackTime = 1.5f;
        charge = 0;
    }



    public void charge(World world, Character user, Vector3 target) {

    }

    public void release(World world, Character user, Vector3 target) {
            target = new Vector3(target);
            //world.addAttack(new AttackObject(user, new MeleeAttack(), new KnockbackCollision(), target, damage));
            world.addAttack(new AttackObject(user, new Projectile(64), new BasicCollision(), target, damage));

    }

}
