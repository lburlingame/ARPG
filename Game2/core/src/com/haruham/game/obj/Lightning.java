package com.haruham.game.obj;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.gfx.Line;
import com.haruham.game.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created on 10/14/2015.
 *
 * from http://gamedevelopment.tutsplus.com/tutorials/how-to-generate-shockingly-good-2d-lightning-effects--gamedev-2681
 */
public class Lightning implements AttackType {

    private float alpha;
    private float alphaMultiplier;
    private float fadeOutRate;
    private HitCircle hit;

    private Color tint;
    public List<Line> Segments = new ArrayList<Line>();
    public Vector2 Start(){
        return Segments.get(0).A;
    }
    public Vector2 End(){
        return Segments.get(Segments.size()-1).B;
    }
    public boolean isComplete() {
        return (getAlpha() <=0);
    }

    public float getAlpha(){
        return alpha;
    }
    public float getAlphaMultiplier(){
        return alphaMultiplier;
    }
    public Color getTint(){
        return tint;
    }
    public void setAlpha(float alpha){
        this.alpha = alpha;
    }
    public void setAlphaMultiplier(float alpha){
        this.alphaMultiplier = alpha;
    }
    public void setTint(Color tint){
        this.tint = tint;
    }
    public float getFadeOutRate() {
        return fadeOutRate;
    }
    public void setFadeOutRate(float fadeOutRate) {
        this.fadeOutRate = fadeOutRate;
    }

    static Random rand = new Random(System.currentTimeMillis());
    public Lightning() {
    }
    public Lightning(Vector3 source, Vector3 dest)
    {
        //init(source, dest, new Color(Color.WHITE));
    }

    public Lightning(Vector3 source, Vector3 dest, Color color)
    {
        //init(source, dest, color);
    }

    private void init(Vector3 source, Vector3 dest, Color color){
        Segments = CreateBolt(source, dest, 10);
        tint = color;
        alpha = 1.2f;
        alphaMultiplier = 1;
        fadeOutRate = 0.03f; //.03f

        hit = new HitCircle(new Vector3(0,0,0), 8);

    }


    protected static List<Line> CreateBolt(Vector3 source, Vector3 dest, float thickness)
    {
        List<Line> results = new ArrayList<Line>();
        Vector3 tangent = new Vector3(dest).sub(new Vector3(source));
        Vector3 normal = new Vector3(tangent.y, -tangent.x, 0).nor();
        float length = tangent.len();

        List<Float> positions = new ArrayList<Float>();
        positions.add(0f);
        for (int i = 0; i < length / 4; i++)
            positions.add(Rand(0, 1));

        Collections.sort(positions);

        float Sway = 25;
        float Jaggedness = 1 / Sway;

        Vector3 prevPoint = source;
        float prevDisplacement = 0;
        for (int i = 1; i < positions.size(); i++)
        {
            float pos = positions.get(i);

            // used to prevent sharp angles by ensuring very close positions also have small perpendicular variation.
            float scale = (length * Jaggedness) * (pos - positions.get(i-1));

            // defines an envelope. Points near the middle of the bolt can be further from the central line.
            float envelope = pos > 0.95f ? 20 * (1 - pos) : 1;

            float displacement = Rand(-Sway, Sway);
            displacement -= (displacement - prevDisplacement) * (1 - scale);
            displacement *= envelope;

            Vector3 point =new Vector3(source).add(new Vector3(0,0,0).mulAdd(tangent, pos)).add(new Vector3().mulAdd(normal, displacement)) ;
            results.add(new Line(prevPoint, point, thickness));
            prevPoint = point;
            prevDisplacement = displacement;
        }

        results.add(new Line(new Vector3(prevPoint), new Vector3(dest), thickness));

        return results;
    }

    private Line find(List<Line> list, Vector2 start, Vector2 dir, float position){
        // FInd (x => Vector2.Dot(x.B - start, dir) >= position

        for(int i=0; i<list.size(); i++){
            Vector2 sub1 = list.get(i).B.sub(start);
            float dot = Vector2.dot(sub1.x, sub1.y, dir.x, dir.y);
            if (dot >= position){
                return list.get(i);
            }
        }
        if (list.size() > 0)
            return list.get(0);
        return null;
    }



    // Returns the point where the bolt is at a given fraction of the way through the bolt. Passing
    // zero will return the start of the bolt, and passing 1 will return the end.
    public Vector2 GetPoint(float position)
    {
        Vector2 start = Start();
        Vector2 end = End();
        float length = Vector2.dst(start.x, start.y, end.x, end.y);
        Vector2 dir = new Vector2().mulAdd(end.sub(start), 1/length);
        position *= length;

        Line line = find(Segments, start, dir, position);
        Vector2 dot1 = line.A.sub(start);
        Vector2 dot2 = line.B.sub(start);
        float lineStartPos = Vector2.dot(dot1.x, dot1.y,dir.x, dir.y);
        float lineEndPos = Vector2.dot(dot2.x, dot1.y, dir.x, dir.y);
        float linePos = (position - lineStartPos) / (lineEndPos - lineStartPos);
        return line.A.lerp(line.B, linePos);
    }

    private static float Rand(float min, float max)
    {
        return (float)rand.nextDouble() * (max - min) + min;
    }


    public void init(AttackObject attack) {
        init(attack.getPosition(), attack.getTarget(), new Color(MathUtils.random() *.2f + .4f,MathUtils.random() *.3f + .5f,MathUtils.random() *.3f + .7f,1));
    }

    public boolean collidesWith(AttackObject attack, Entity other) {
        Vector3 hitCenter = hit.getCenter();

        Vector3 opos = other.getPosition();
        HitCircle o = other.getHit();
        Vector3 oCenter = o.getCenter();

        if (Util.findDistance((attack.getTarget().x + hitCenter.x) - (opos.x + oCenter.x), (attack.getTarget().y + hitCenter.y) - (opos.y + oCenter.y)) <= (hit.getRadius() + o.getRadius())) {
            return true;
        }

        return false;
    }

    public void update(AttackObject attack, float delta) {
        alpha -= fadeOutRate;
    }

    public void draw(AttackObject attack, SpriteBatch batch) {
        if (alpha <= 0)
            return;
        for(int i=0; i<Segments.size(); i++){
            Line segment = Segments.get(i);
            segment.draw(batch, new Color(tint).mul(alpha * alphaMultiplier));
        }
    }

    public void drawDebug(AttackObject attack, ShapeRenderer shapeRenderer) {

    }
}
