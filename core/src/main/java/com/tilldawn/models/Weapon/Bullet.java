package com.tilldawn.models.Weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.CollisionRect;
import com.tilldawn.models.GameAssetManager;

import javax.swing.*;

public class Bullet {
    private Texture texture = new Texture(GameAssetManager.getInstance().getBullet());
    private Sprite sprite = new Sprite(texture);
    private static Texture textureImage = new Texture("Weapons/T_AmmoIcon.png");
    private CollisionRect collision;
    private int damage;
    private float posX;
    private float posY;
    // Bullet vector
    private float deltaX = 1;
    private float deltaY = 1;
    private boolean isAvailable = true;

    private boolean isFromPlayer;

    public Bullet(float x, float y , float deltaX , float deltaY) {
        this.posX = x;
        this.posY = y;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.isFromPlayer = true;
        sprite.setSize(texture.getWidth(), texture.getHeight());
        this.damage = App.getCurrentGame().getWeapon().getDamage();
        sprite.setPosition(x, y);
        sprite.setSize(texture.getWidth()/25f, texture.getHeight()/25f);
        collision = new CollisionRect(posX , posY , sprite.getWidth()/25f, sprite.getHeight()/25f);
    }

    public boolean removeExtra() {
        float x = this.posX;
        float y = this.posY;
        if(x > 1850 || x < -1900){
            return true;
        }
        if(y > 1300 || y < -1350){
            return true;
        }
        return false;
    }

    public Bullet(float x, float y , float deltaX , float deltaY , int damage) {
        this.posX = x;
        this.posY = y;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        sprite.setSize(texture.getWidth(), texture.getHeight());
        this.damage = damage;
        this.isFromPlayer = false;
        sprite.setPosition(x, y);
        sprite.setSize(texture.getWidth()/25f, texture.getHeight()/25f);
        collision = new CollisionRect(posX , posY , sprite.getWidth()/25f, sprite.getHeight()/25f);
    }

    public void update(float delta) {
        collision.setX(posX);
        collision.setY(posY);
        if(this.isFromPlayer){
            posX += (delta + 10) * deltaX;
            posY += (delta + 10) * deltaY;
        }else {
            posX += (delta + 3) * deltaX;
            posY += (delta + 3) * deltaY;
        }
        sprite.setPosition(posX, posY);
        sprite.draw(Main.getBatch());
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public CollisionRect getCollision() {
        return collision;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setCollision(CollisionRect collision) {
        this.collision = collision;
    }

    public void setX(float x) {
        this.posX = x;
    }

    public void setY(float y) {
        this.posY = y;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public static Texture getTextureImage() {
        return textureImage;
    }

    public void useBullet() {
        isAvailable = false;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public boolean isFromPlayer() {
        return isFromPlayer;
    }

    public int getDamage() {
        return damage;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public float getDeltaX() {
        return deltaX;
    }
}
