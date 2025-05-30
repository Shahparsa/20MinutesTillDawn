package com.tilldawn.models.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.models.CollisionRect;

public class EyeBat extends Enemy {
    {
        Texture frame1 = new Texture("Enemies/Eyebat/T_EyeBat_0.png");
        Texture frame2 = new Texture("Enemies/Eyebat/T_EyeBat_1.png");
        Texture frame3 = new Texture("Enemies/Eyebat/T_EyeBat_2.png");
        Texture frame4 = new Texture("Enemies/Eyebat/T_EyeBat_3.png");
        TextureRegion[] EyeBatRegion = new TextureRegion[]{
            new TextureRegion(frame1),
            new TextureRegion(frame2),
            new TextureRegion(frame3),
            new TextureRegion(frame4)
        };
        animation = new Animation<>(0.1f, EyeBatRegion);
    }

    private float shootTime = 0;

    public EyeBat(float x, float y) {
        super(x, y, 50, 1, 10);
        Texture texture = new Texture("Enemies/Eyebat/T_EyeBat_0.png");
        this.collisionRect = new CollisionRect(x, y, texture.getWidth() * 1.5f, texture.getHeight() * 1.5f);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(texture.getWidth() * 1.5f, texture.getHeight() * 1.5f);
    }

    public float getShootTime() {
        return shootTime;
    }

    public void setShootTime(float shootTime) {
        this.shootTime = shootTime;
    }
}
