package com.tilldawn.models;

import com.tilldawn.models.Enemies.Enemy;
import com.tilldawn.models.Weapon.Bullet;
import com.tilldawn.models.Weapon.Weapon;

import java.util.ArrayList;

public class Game {
    private Player player;
    private Hero hero;
    private Weapon weapon;

    //Times
    private final float fullTime;
    private float realTime = 0;
    private float tentacleMonsterTime = 0;
    private float eyeBatMonsterTime = 0;
    private float invincibleTime = 0;
    private float doubleDamageTime = 0;
    private float doubleSpeedTime = 0;

    // World things
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Coin> coins = new ArrayList<>();
    private ArrayList<Bullet> deletedBullets = new ArrayList<>();
    private ArrayList<Enemy> deletedEnemies = new ArrayList<>();
    private ArrayList<Coin> deletedCoins = new ArrayList<>();

    //Enemy
    private int numberOfTree = 20;

    //Aim
    private boolean aimBot = false;

    //Ability
    private int VitalityNumber = 0;
    private int DamagerNumber = 0;
    private int ProCreaseNumber = 0;
    private int AmoCreaseNumber = 0;
    private int SpeedyNumber = 0;


    public Game(float time, Hero hero, Player player, Weapon weapon) {
        this.fullTime = time;
        this.hero = hero;
        this.player = player;
        this.weapon = weapon;
    }

    public Player getPlayer() {
        return player;
    }

    public Hero getHero() {
        return hero;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public float getRealTime() {
        return realTime;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public int getNumberOfTree() {
        return numberOfTree;
    }

    public float getFullTime() {
        return fullTime;
    }

    public float getTentacleMonsterTime() {
        return tentacleMonsterTime;
    }

    public float getEyeBatMonsterTime() {
        return eyeBatMonsterTime;
    }

    public void setRealTime(float realTime) {
        this.realTime = realTime;
    }

    public void setTentacleMonsterTime(float tentacleMonsterTime) {
        this.tentacleMonsterTime = tentacleMonsterTime;
    }

    public void setEyeBatMonsterTime(float eyeBatMonsterTime) {
        this.eyeBatMonsterTime = eyeBatMonsterTime;
    }

    public ArrayList<Bullet> getDeletedBullets() {
        return deletedBullets;
    }

    public void resetDeletedBullets() {
        deletedBullets = new ArrayList<>();
    }

    public boolean isAimBot() {
        return aimBot;
    }

    public void changeAimBot() {
        aimBot = !aimBot;
    }

    public ArrayList<Enemy> getDeletedEnemies() {
        return deletedEnemies;
    }

    public ArrayList<Coin> getDeletedCoins() {
        return deletedCoins;
    }

    public void resetDeletedEnemies() {
        deletedEnemies = new ArrayList<>();
    }

    public void resetDeletedCoins() {
        deletedCoins = new ArrayList<>();
    }

    public int getVitalityNumber() {
        return VitalityNumber;
    }

    public int getDamagerNumber() {
        return DamagerNumber;
    }

    public int getProCreaseNumber() {
        return ProCreaseNumber;
    }

    public int getAmoCreaseNumber() {
        return AmoCreaseNumber;
    }

    public int getSpeedyNumber() {
        return SpeedyNumber;
    }

    public void setNumberOfTree(int numberOfTree) {
        this.numberOfTree = numberOfTree;
    }

    public float getInvincibleTime() {
        return invincibleTime;
    }

    public void setInvincibleTime(float invincibleTime) {
        this.invincibleTime = invincibleTime;
    }

    public void setVitalityNumber(int vitalityNumber) {
        VitalityNumber = vitalityNumber;
    }

    public void setDamagerNumber(int damagerNumber) {
        DamagerNumber = damagerNumber;
    }

    public void setProCreaseNumber(int proCreaseNumber) {
        ProCreaseNumber = proCreaseNumber;
    }

    public void setAmoCreaseNumber(int amoCreaseNumber) {
        AmoCreaseNumber = amoCreaseNumber;
    }

    public void setSpeedyNumber(int speedyNumber) {
        SpeedyNumber = speedyNumber;
    }

    public float getDoubleSpeedTime() {
        return doubleSpeedTime;
    }

    public void setDoubleSpeedTime(float doubleSpeedTime) {
        this.doubleSpeedTime = doubleSpeedTime;
    }

    public float getDoubleDamageTime() {
        return doubleDamageTime;
    }

    public void setDoubleDamageTime(float doubleDamageTime) {
        this.doubleDamageTime = doubleDamageTime;
    }

}
