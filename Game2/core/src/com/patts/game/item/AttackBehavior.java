package com.patts.game.item;

import com.badlogic.gdx.math.Vector3;
import com.patts.game.level.World;
import com.patts.game.obj.Character;

/**
 * Created on 10/17/2015.
 */
public interface AttackBehavior {
    public void attack(World world, Character user, Vector3 target);
}
