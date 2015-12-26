package com.patts.game.input;

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
        }

        if (Inputs.isPressed(Inputs.N)) {
            character.getWorld().getAttacks().clear();
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



