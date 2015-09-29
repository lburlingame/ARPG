package com.haruham.game.item;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.entity.Entity;
import com.haruham.game.state.Play;

/**
 * Created on 9/17/2015.
 */
public class Weapon extends Equipment {

    private int minDamage;
    private int maxDamage;
    private float attackTime; // time in seconds between attacks
    private float charge;
    private static final float MAX_CHARGE = 1.5f * 60;

    public Weapon(int itemID, String name, String description, int levelReq, int quantity) {
        super(itemID, name, description, levelReq, quantity);
        minDamage = 10;
        maxDamage = 15;
        attackTime = 1.5f;
        charge = 0;

    }

    public void attack(Play world, Entity user, Vector3 target) {
        world.addAttack(new Attack(user, "Melee attack", 16, target));

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
