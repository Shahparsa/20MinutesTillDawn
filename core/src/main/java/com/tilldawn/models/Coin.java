package com.tilldawn.models;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Coin {
    private static Texture texture = new Texture("Coin/T_ChargeUp_1.png");
    private static Sprite sprite = new Sprite(texture);
    private float x, y;
    private CollisionRect collisionRect;

    static {
        sprite.setSize(texture.getWidth() * 1.5f, texture.getHeight() * 1.5f);
    }

    public Coin(float x, float y) {
        this.x = x;
        this.y = y;
        collisionRect = new CollisionRect(x , y , sprite.getWidth() * 1.5f, sprite.getHeight() * 1.5f);
    }

    public static Sprite getSprite() {
        return sprite;
    }

    public static void setSprite(Sprite sprite) {
        Coin.sprite = sprite;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }
}
