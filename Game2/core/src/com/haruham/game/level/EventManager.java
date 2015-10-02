package com.haruham.game.level;

/**
 * Created on 10/1/2015.
 */

// example, when going through a door, an event is called, and that event checks to see if the level is a boss level
// if its a boss level, the doors will be locked.. another event will be called when the boss dies, reopening the doors
// would probably be a good place to use observer pattern.

// level will be an GameEvent listener listening to all events
// Player ui will be a GameEvent listener that is only registered for events relative to the player
// get rid of EventManager and only have EventListener and Event classes, and then subclasses of those events/listener types

public class EventManager {

}
