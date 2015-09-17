package com.haruham.game.item;

/**
 * Created on 9/15/2015.
 */
public class Item {

    protected int itemID;
    protected String name;
    protected String flavorText;
    protected int levelReq;
    protected int quantity;
    protected UseComponent usable;

    public Item(int itemID, String name, String flavorText, int levelReq, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.flavorText = flavorText;
        this.levelReq = levelReq;
        this.quantity = quantity;
    }
}
