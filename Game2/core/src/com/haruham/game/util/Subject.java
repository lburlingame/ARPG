package com.haruham.game.util;

import com.haruham.game.obj.Entity;

/**
 * Created on 10/1/2015.
*/
public interface Subject {

    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers(Entity entity, Event event);
}
