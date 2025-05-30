package com.tilldawn.models.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.models.CollisionRect;

public class TentacleMonster extends Enemy {
    {
        Texture frame1 = new Texture("Enemies/BrainMonster/BrainMonster_0.png");
        Texture frame2 = new Texture("Enemies/BrainMonster/BrainMonster_1.png");
        Texture frame3 = new Texture("Enemies/BrainMonster/BrainMonster_3.png");
        TextureRegion[] tentacleRegions = new TextureRegion[]{
            new TextureRegion(frame1),
            new TextureRegion(frame2),
            new TextureRegion(frame3),
        };
        animation = new Animation<>(0.2f, tentacleRegions);
    }

    public TentacleMonster(float x, float y) {
        super(x, y, 25, 1, 10);
        Texture texture = new Texture("Enemies/TentacleMonster/T_TentacleEnemy_0.png");
        this.collisionRect = new CollisionRect(x, y, texture.getWidth() * 1.5f, texture.getHeight() * 1.5f);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(texture.getWidth() * 2, texture.getHeight() * 1.5f);
    }
}
