package com.haruham.game.obj;

/**
 * Created on 10/3/2015.
 */
public abstract class HealthComponent {

    protected int currHealth;
    protected int maxHealth;

    protected boolean alive;

    public abstract void takeHit(AttackObject attack, Character character);

    protected float takeDamage(int amount) {
        float percentage = amount / (float)maxHealth;
        currHealth = currHealth - amount;
        if (currHealth <= 0) {
            currHealth = 0;
            die();
        }
        return percentage;
    }

    public void heal(int amount) {
        currHealth = currHealth + amount;
        if (currHealth > maxHealth) {
            currHealth = maxHealth;
        }
    }

    protected abstract void die();

    public boolean isAlive() {
        return alive;
    }

}
