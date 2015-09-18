package com.haruham.game.item;

import com.haruham.game.entity.Entity;

/**
 * Created on 9/17/2015.
 */
public abstract class Active {

    private String description;

    public Active(String description) {
        this.description = description;
    }

    public abstract void use(Entity target);
}
