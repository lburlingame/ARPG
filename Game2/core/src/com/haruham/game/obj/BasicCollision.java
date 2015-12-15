package com.haruham.game.obj;

/**
 * Created on 10/12/2015.
 */
public class BasicCollision implements CollisionBehavior {

    private int damage;

    public BasicCollision(int damage) {
        this.damage = damage;
    }


    // maybe send an release report instead, containing information such as knockback and stuff
    public void onCollision(AttackObject attack, Character character) {
        character.takeDamage(damage);
    }
}
