package com.tilldawn.models.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.models.CollisionRect;

public class Elder extends Enemy {
    {
        Texture frame1 = new Texture("Enemies/Elder/ElderBrain.png");
        TextureRegion[] ElderRegion = new TextureRegion[]{
            new TextureRegion(frame1),
        };
        animation = new Animation<>(0.1f, ElderRegion);
    }

    private float dashTimer = 0;
    private final float maxTime = 5f;
    private boolean isDashing = true;

    public Elder(float x, float y) {
        super(x, y, 400, 1, 500);
        Texture texture = new Texture("Enemies/Elder/ElderBrain.png");
        this.collisionRect = new CollisionRect(x, y, texture.getWidth() * 3f,texture.getHeight() * 3f);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(texture.getWidth() * 3f, texture.getHeight() * 3f);
    }

    public boolean isDashing() {
        return isDashing;
    }

    public void setDashing(boolean dashing) {
        isDashing = dashing;
    }

    public float getDashTimer() {
        return dashTimer;
    }

    public float getDashMax() {
        return maxTime;
    }

    public void setDashTimer(float dashTimer) {
        this.dashTimer = dashTimer;
    }
}
