package com.tilldawn.models.enums.Heroes;

public enum HeroesType {
    Dasher(HeroesIdle.Dasher , HeroesWalk.Dasher , 2 , 10),
    Diamond(HeroesIdle.Diamond , HeroesWalk.Diamond , 7 , 1),
    Hina(HeroesIdle.Hina , HeroesWalk.Hina , 5 , 4),
    Lilith(HeroesIdle.Lilith , HeroesWalk.Lilith , 5 , 3),
    Scarlet(HeroesIdle.Scarlet , HeroesWalk.Scarlet , 3 , 5),
    Shana(HeroesIdle.Shana , HeroesWalk.Shana , 4 , 4),;

    private final HeroesIdle idle;
    private final HeroesWalk walk;
    private final int HP;
    private final float speed;

    HeroesType(HeroesIdle idle, HeroesWalk walk, int HP, int speed) {
        this.idle = idle;
        this.walk = walk;
        this.HP = HP;
        this.speed = speed;
    }

    public HeroesIdle getIdle() {
        return idle;
    }

    public HeroesWalk getWalk() {
        return walk;
    }

    public int getHP() {
        return HP;
    }

    public float getSpeed() {
        return speed;
    }
}
