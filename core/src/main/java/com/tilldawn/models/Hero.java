package com.tilldawn.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.models.Weapon.Weapon;
import com.tilldawn.models.enums.Heroes.HeroesType;

public class Hero {
    private HeroesType type;
    private Texture texture;
    private Sprite sprite;

    public Hero(HeroesType type) {
        this.type = type;
        this.texture = new Texture(type.getIdle().getAnimationAddresses().get(0));
        this.sprite = new Sprite(texture);
    }

    public HeroesType getType() {
        return type;
    }
}
