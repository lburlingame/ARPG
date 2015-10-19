package com.haruham.game.item;

import com.haruham.game.obj.Character;

/**
 * Created on 9/15/2015.
 */

/* Extends GameObject, and has its own pos and velocities */
public class Item {


    // pos and velocity too
    protected int itemID;
    protected String name;
    protected String description;  //  flavor text
    protected int levelReq;
    protected int quantity;
    protected Active active;

    public Item(int itemID, Active active, String name, String description, int levelReq, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.levelReq = levelReq;
        this.quantity = quantity;

        this.active = active;
    }

    public void use(Character user) {
        active.use(user);
    }


}
