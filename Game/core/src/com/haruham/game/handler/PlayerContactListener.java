package com.haruham.game.handler;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created on 5/16/2015.
 */
public class PlayerContactListener implements ContactListener {

    public boolean playerOnGround = false;
    public boolean playerOnWallLeft = false;
    public boolean playerOnWallRight = false;

    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (((fa.getUserData() != null && fa.getUserData().equals("player:foot")) && (fb.getUserData() != null && fb.getUserData().equals("ground")))
                || ((fb.getUserData() != null && fb.getUserData().equals("player:foot")) && (fa.getUserData() != null && fa.getUserData().equals("ground")))
                ||((fa.getUserData() != null && fa.getUserData().equals("player:foot")) && (fb.getUserData() != null && fb.getUserData().equals("wall")))
                || ((fb.getUserData() != null && fb.getUserData().equals("player:foot")) && (fa.getUserData() != null && fa.getUserData().equals("wall")))
                ) {
            playerOnGround = true;
        }else if (((fa.getUserData() != null && fa.getUserData().equals("player:left")) && (fb.getUserData() != null && fb.getUserData().equals("wall")))
                || ((fb.getUserData() != null && fb.getUserData().equals("player:left")) && (fa.getUserData() != null && fa.getUserData().equals("wall")))) {
            playerOnWallLeft = true;
        }else if (((fa.getUserData() != null && fa.getUserData().equals("player:right")) && (fb.getUserData() != null && fb.getUserData().equals("wall")))
                || ((fb.getUserData() != null && fb.getUserData().equals("player:right")) && (fa.getUserData() != null && fa.getUserData().equals("wall")))) {
            playerOnWallRight = true;
        }

        System.out.println("begin: " + fa.getUserData() + ", " + fb.getUserData());
    }

    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (((fa.getUserData() != null && fa.getUserData().equals("player:foot")) && (fb.getUserData() != null && fb.getUserData().equals("ground")))
                || ((fb.getUserData() != null && fb.getUserData().equals("player:foot")) && (fa.getUserData() != null && fa.getUserData().equals("ground")))
                ||((fa.getUserData() != null && fa.getUserData().equals("player:foot")) && (fb.getUserData() != null && fb.getUserData().equals("wall")))
                || ((fb.getUserData() != null && fb.getUserData().equals("player:foot")) && (fa.getUserData() != null && fa.getUserData().equals("wall")))
                ) {
            playerOnGround = false;
        }else if (((fa.getUserData() != null && fa.getUserData().equals("player:left")) && (fb.getUserData() != null && fb.getUserData().equals("wall")))
                || ((fb.getUserData() != null && fb.getUserData().equals("player:left")) && (fa.getUserData() != null && fa.getUserData().equals("wall")))) {
            playerOnWallLeft = false;
        }else if (((fa.getUserData() != null && fa.getUserData().equals("player:right")) && (fb.getUserData() != null && fb.getUserData().equals("wall")))
                || ((fb.getUserData() != null && fb.getUserData().equals("player:right")) && (fa.getUserData() != null && fa.getUserData().equals("wall")))) {
            playerOnWallRight = false;
        }


        System.out.println("end: " + fa.getUserData() + ", " + fb.getUserData());
    }

    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
