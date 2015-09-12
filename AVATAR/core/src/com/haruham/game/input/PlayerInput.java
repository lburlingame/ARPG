package com.haruham.game.input;

import com.badlogic.gdx.Gdx;
import com.haruham.game.GameApp;
import com.haruham.game.entity.Entity;

/**
 * Created on 3/26/2015.
 */
public class PlayerInput extends InputComponent {

    private Entity character;

    private boolean clicked = false;
    private boolean stopped = false;

    public PlayerInput(GameApp game) {
        super(game);
    }

    public void update() {

        // TODO MAKE IT SO THE MOST RECENTLY ACTIVATED KEY GETS PRIORITY
        if (Inputs.isDown(Inputs.W)) {
            character.setDy(180);
        }else{
            if (Inputs.isDown(Inputs.S)) {
                character.setDy(-180);
            }else{
                character.setDy(0);
            }
        }

        if (Inputs.isDown(Inputs.A)) {
            character.setDx(-180);
        }else{
            if (Inputs.isDown(Inputs.D)) {
                character.setDx(180);
            }else{
                character.setDx(0);
            }
        }

        if (Inputs.isPressed(Inputs.SPACE)) {
            character.jump();
        }

       /* if (input.SHIFT.isPressed()) {
            character.sprint();
        }else if (input.CTRL.isPressed()) {
            character.walk();
        }else{
            character.run();
        }



        Vector3 offset = camera.getOffset();

        if (input.M1.isPressed()) {
            character.fireball.use(new Vector2(character.getX(), character.getY()), new Vector2(input.mLoc.getX() / camera.getScale() + offset.x, input.mLoc.getY() / camera.getScale() + offset.y));
            character.fireball.charge(new Vector3(character.getX(), character.getY(), -character.getHeight() / 4));
        }

        if (input.ONE.isPressed()) {
            character.nova.use(character.getPosition());
        }

        if (input.M3.isPressed()) {
            character.fireball.charge(new Vector3(character.getX(), character.getY(), -character.getHeight() / 4));
        }else if (input.M3.getPrevious()) {
            character.fireball.use(new Vector2(character.getX(), character.getY()), new Vector2(input.mLoc.getX() / camera.getScale() + offset.x, input.mLoc.getY() / camera.getScale() + offset.y));
        }*/


        if (clicked && !stopped) {

        }
    }

    // why is this needed? shouldn't the superclass method be sufficient? why is it breaking when i dont have this?
    public void setCharacter(Entity character) {
        this.character = character;
    }

}



