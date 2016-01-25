package com.haruham.game.input;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.obj.Character;

/**
 * Created on 3/26/2015.
 */
public class PlayerInput implements InputComponent {

    private float cooldown = .15f;

    public PlayerInput() {

    }


    int i = 0;
    public void update(Character character, float delta) {
        float velocity = 150;
        // TODO MAKE IT SO THE MOST RECENTLY ACTIVATED KEY GETS PRIORITY
        if (Inputs.isDown(Inputs.MOVE_UP)) {
            character.setDy(velocity);
        }else{
            if (Inputs.isDown(Inputs.MOVE_DOWN)) {
                character.setDy(-velocity);
            }else{
                character.setDy(0);
            }
        }

        if (Inputs.isDown(Inputs.MOVE_LEFT)) {
            character.setDx(-velocity);
        }else{
            if (Inputs.isDown(Inputs.MOVE_RIGHT)) {
                character.setDx(velocity);
            }else{
                character.setDx(0);
            }
        }

       /* if (Inputs.isPressed(Inputs.SPACE)) {
            character.jump();
        }*/


        if (Inputs.isDown(Inputs.M1)) {
            cooldown -= delta;
            if (cooldown <= 0) {
                character.attack(Inputs.pos);
                cooldown = .15f + cooldown;
            }
        }
        if (Inputs.isReleased(Inputs.M1)) {
            cooldown = 0;
        }

        if (Inputs.isPressed(Inputs.M3)) {
            character.knockback(new Vector3(MathUtils.random()* 300 - 150, MathUtils.random() * 300 - 150, 0));
        }

        if (Inputs.isPressed(Inputs.SPACE)) {
            character.setX(Inputs.pos.x);
            character.setY(Inputs.pos.y);
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


    }

}



