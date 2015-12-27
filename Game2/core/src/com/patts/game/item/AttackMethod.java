package com.patts.game.item;

import com.badlogic.gdx.math.Vector3;
import com.patts.game.level.World;
import com.patts.game.obj.Character;

/**
 * Created on 10/16/2015.
 */
public interface AttackMethod {

    public void charge(AttackBehavior behavior, World world, Character user, Vector3 target);
    public void release(AttackBehavior behavior, World world, Character user, Vector3 target);
    public void update(float delta);
}