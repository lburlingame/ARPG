package com.haruham.game.entity;

import com.haruham.game.item.Item;

import java.util.ArrayList;

/**
 * Created on 9/15/2015.
 */
public class Inventory {

    private ArrayList<Item> items;
    private int size;
    private int capacity;
    private static final int MAX_CAPACITY = 40;

    public Inventory(int capacity) {
        if (capacity > MAX_CAPACITY) {
            capacity = MAX_CAPACITY;
        }
        this.capacity = capacity;
        items = new ArrayList<Item>();
        size = 0;

        for (int i = 0; i < capacity; i++) {
            items.add(null);
        }
    }

    public void increaseCapacity(int amount) {
        for (int i = 0; i < amount && capacity < MAX_CAPACITY; i++) {
            items.add(null);
        }
    }

    public boolean addItem(Item item) {
        if (size < capacity) {
            for (int i = 0; i < capacity; i++) {
                if (items.get(i) == null) {
                    items.set(i, item);
                    size++;
                    return true;
                }
            }
        }
        return false;
    }

    public Item addItem(Item item, int index) {
        Item remove = removeItem(index);
        items.add(index, item);
        return remove;
    }

    public Item removeItem(int index) {
        Item item = items.get(index);
        if (item != null) {
            items.set(index, null);
            size--;
        }
        return item;
    }

    // in the Inventory frame class, it will keep track of indexes/items that have been clicked, and call the appropriate functions
}
