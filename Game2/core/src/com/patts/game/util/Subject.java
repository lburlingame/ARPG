package com.patts.game.util;

import com.patts.game.obj.Character;

/**
 * Created on 10/1/2015.
*/
public interface Subject {

    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers(Character character, Event event);
}