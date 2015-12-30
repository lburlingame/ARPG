package com.haruham.game.item;

/**
 * Created on 9/17/2015.
 */
public abstract class Equipment extends Item {

    protected EquipmentStats stats;

    public Equipment(int itemID, String name, String description, int levelReq) {
        super(itemID, new HealingActive(200), name, description, levelReq, 1);
    }
}
