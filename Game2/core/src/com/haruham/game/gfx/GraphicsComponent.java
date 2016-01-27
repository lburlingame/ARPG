package com.haruham.game.gfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haruham.game.obj.Character;

/**
 * Created on 8/22/2015.
 */
public class GraphicsComponent {

    private int frame = 0;
    private int animation_timer = 3;

    public GraphicsComponent() {
        frame = 0;
    }

    public void update(com.haruham.game.obj.Character character) {
        if (character.getState() > 0) {
            animation_timer--;
            if (animation_timer == 0) {
                if (character.getState() == 1) {
                    animation_timer = 8;
                }else if (character.getState() == 2){
                    animation_timer = 3;
                }else if (character.getState() == 3) {
                    animation_timer = 2;
                }

                frame++;
                frame = frame % 8;  // limit here to sprites frame count, only change when sprite id is changed
            }
        }else{
            frame = 0;
        }
    }

    public void draw(SpriteBatch batch, Character character){
        batch.draw(Art.shadow, character.getX()-character.getDimx()/2, character.getY() - character.getDimy()*.425f, 32,16);
        batch.draw(TextureLoader.getSprite(character.getID(), frame), character.getX()-character.getDimx()/2, character.getY() - character.getDimy()*.25f + character.getZ(), 32,32);

    }


}
