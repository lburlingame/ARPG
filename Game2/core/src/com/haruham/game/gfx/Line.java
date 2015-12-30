package com.haruham.game.gfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created on 10/14/2015.
 */

public class Line {

    public Vector2 A;
    public Vector2 B;
    public float Thickness;

    public Line() { }
    public Line(Vector3 a, Vector3 b, float thickness)
    {
        A = new Vector2(a.x, a.y);
        B = new Vector2(b.x, b.y);
        Thickness = thickness;
    }

    public void draw(SpriteBatch spriteBatch, Color tint)
    {
        Vector2 tangent = new Vector2(B).sub(new Vector2(A));
        float theta = (float)Math.toDegrees(Math.atan2(tangent.y, tangent.x));
        //Thickness *=1.03;
        float scale = Thickness / Art.HalfCircle.getRegionHeight();
        Color prevColor = spriteBatch.getColor();
        spriteBatch.setColor(tint);
        int blfn = spriteBatch.getBlendDstFunc();
        spriteBatch.setBlendFunction(spriteBatch.getBlendSrcFunc(), GL20.GL_ONE);
                //Pixmap.Blending.SourceOver.ordinal());

        spriteBatch.draw(Art.LightningSegment, A.x, A.y, 0,Thickness/2, getLength(), Thickness, 1,1, theta);
        spriteBatch.draw(Art.HalfCircle, A.x, A.y, 0,Thickness/2, scale * Art.HalfCircle.getRegionWidth(), Thickness, 1,1, theta);
        spriteBatch.draw(Art.HalfCircle2, B.x, B.y, 0,Thickness/2, scale * Art.HalfCircle2.getRegionWidth(), Thickness, 1,1, theta);

        spriteBatch.setColor(prevColor);
        spriteBatch.setBlendFunction(spriteBatch.getBlendSrcFunc(), blfn);
    }

    public float getLength(){
        float len = (float)Math.sqrt((A.x - B.x)*(A.x - B.x) + (A.y - B.y)*(A.y - B.y));
        return len;
    }
}

