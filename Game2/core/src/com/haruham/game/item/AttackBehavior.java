package com.haruham.game.item;

import com.badlogic.gdx.math.Vector3;
import com.haruham.game.level.World;
import com.haruham.game.obj.*;

/**
 * Created on 10/17/2015.
 */
public interface AttackBehavior {
    public void attack(World world, com.haruham.game.obj.Character user, Vector3 target);
}
