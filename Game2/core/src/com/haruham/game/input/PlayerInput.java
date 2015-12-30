package com.haruham.game.input;

import com.haruham.game.level.World;

/**
 * Created on 3/26/2015.
 */
public class PlayerInput extends InputComponent {

    public PlayerInput() {

    }


    int i = 0;
    public void update(float delta) {

        // TODO MAKE IT SO THE MOST RECENTLY ACTIVATED KEY GETS PRIORITY
        if (Inputs.isDown(Inputs.MOVE_UP)) {
            character.setDy(180);
        }else{
            if (Inputs.isDown(Inputs.MOVE_DOWN)) {
                character.setDy(-180);
            }else{
                character.setDy(0);
            }
        }

        if (Inputs.isDown(Inputs.MOVE_LEFT)) {
            character.setDx(-180);
        }else{
            if (Inputs.isDown(Inputs.MOVE_RIGHT)) {
                character.setDx(180);
            }else{
                character.setDx(0);
            }
        }

       /* if (Inputs.isPressed(Inputs.SPACE)) {
            character.jump();
        }*/


        if (Inputs.isDown(Inputs.M1)) {
            if (i++ % 6 ==0)
            character.attack(Inputs.pos);
        }
        if (Inputs.isReleased(Inputs.M1)) {
            i = 0;
        }

        if (Inputs.isPressed(Inputs.SPACE)) {
            character.setX(Inputs.pos.x);
            character.setY(Inputs.pos.y);
        }
        if (Inputs.isPressed(Inputs.N)) {
            character.getWorld().getEmitter().clear();
            character.getWorld().clearAttacks();
        }

        if (Inputs.isPressed(Inputs.ONE)) {
            character.getWorld().setShader(World.ShaderSelection.Default);
            System.out.println("set default");
        }
        if (Inputs.isPressed(Inputs.TWO)) {
            character.getWorld().setShader(World.ShaderSelection.Ambient);
            System.out.println("set ambiant");

        }
        if (Inputs.isPressed(Inputs.THREE)) {
            character.getWorld().setShader(World.ShaderSelection.Light);
            System.out.println("set light");

        }
        if (Inputs.isPressed(Inputs.FOUR)) {
            character.getWorld().setShader(World.ShaderSelection.Final);
            System.out.println("set final");

        }
        if (Inputs.isPressed(Inputs.M3)) {
            character.getWorld().lightOscillate = !character.getWorld().lightOscillate;
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



