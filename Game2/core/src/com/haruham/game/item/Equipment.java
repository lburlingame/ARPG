package com.haruham.game.item;

/**
 * Created on 9/17/2015.
 */
public abstract class Equipment extends Item {

    public Equipment(int itemID, String name, String flavorText, int levelReq, int quantity) {
        super(itemID, name, flavorText, levelReq, quantity);
    }
}
