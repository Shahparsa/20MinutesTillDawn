package com.tilldawn.models.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Elder extends Enemy{
     {
        Texture frame1 = new Texture("Enemies/Elder/ElderBrain.png");
        TextureRegion[] ElderRegion = new TextureRegion[]{
            new TextureRegion(frame1),
        };
        animation = new Animation<>(0.1f, ElderRegion);
    }

    public Elder(float x, float y, float width, float height) {
        hp = 400;

    }
}
