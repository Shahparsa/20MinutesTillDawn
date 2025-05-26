package com.tilldawn.models.Weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.models.enums.WeaponsType;

public class Weapon {
    private WeaponsType type;
    private final Texture texture;
    private final Sprite sprite;
    private int ammo;
    private int damage;
    private int maxAmmo;
    private int reloadTime;
    private int fireRate;


    public Weapon(WeaponsType type) {
        this.type = type;
        this.reloadTime = type.getReloadTime();
        this.maxAmmo = type.getMaxAmmo();
        this.damage = type.getDamage();
        this.ammo = type.getMaxAmmo();
        this.fireRate = type.getFireRate();
        this.texture = new Texture(type.getTexturePath());
        this.sprite = new Sprite(texture);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public WeaponsType getType() {
        return type;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void useAmmo(){
        this.ammo--;
    }

    public void reloadAmmo(){
        ammo = maxAmmo;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
