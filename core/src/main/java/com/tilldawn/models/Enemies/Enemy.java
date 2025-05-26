package com.tilldawn.models.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.models.CollisionRect;

public abstract class Enemy {
    protected Texture texture;
    protected Sprite sprite;
    protected Animation<TextureRegion> animation;

    protected float x;
    protected float y;
    protected boolean isFacingRight = true;
    protected float stateTime = 0;
    protected float speed;

    protected int damage = 1;
    protected int hp;
    protected CollisionRect collisionRect;

    public Sprite getSprite() {
        return sprite;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }

    public float getStateTime() {
        return stateTime;
    }

    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void changeFacingRight() {
        isFacingRight = !isFacingRight;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }

    public float getSpeed() {
        return speed;
    }
}
