package com.tilldawn.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.models.Weapon.Weapon;

public class Player{
    private Texture texture;
    private Sprite sprite;
    private CollisionRect collisionRect;


    private Hero hero;
    private int maxHp;
    private int Hp;
    private float speed;
    private int kills = 0;
    private float x = Gdx.graphics.getWidth()/2f;
    private float y = Gdx.graphics.getHeight()/2f;

    private int Xp = 0;
    private int level = 1;
    private boolean levelUp = false;


    public Player(Hero hero){
        this.hero = hero;
        this.texture = new Texture(hero.getType().getIdle().getAnimationAddresses().get(0));
        this.sprite = new Sprite(texture);
        this.sprite.setPosition((float) Gdx.graphics.getWidth()/2f, (float) Gdx.graphics.getHeight()/2f);
        this.sprite.setSize(texture.getWidth() * 3, texture.getHeight() * 3);
        collisionRect = new CollisionRect(Gdx.graphics.getWidth() / 2f , Gdx.graphics.getHeight() / 2f,
            texture.getWidth() * 3, texture.getHeight() * 3);
        this.speed = hero.getType().getSpeed();
        this.maxHp = hero.getType().getHP();
        this.Hp = maxHp;
    }


    public void addXp(int xp){
        // If return true it means level up
        int max = level * 20;
        Xp += xp;
        if (Xp >= max){
            level++;
            Xp -= max;
            levelUp = true;
        }
    }

    public Hero getHero() {
        return hero;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        Hp = hp;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getXp() {
        return Xp;
    }

    public void setXp(int xp) {
        Xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void updateRec(){
        this.collisionRect.setX(x);
        this.collisionRect.setY(y);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public boolean isLevelUp() {
        return levelUp;
    }

    public void setLevelUp(boolean levelUp) {
        this.levelUp = levelUp;
    }
}
