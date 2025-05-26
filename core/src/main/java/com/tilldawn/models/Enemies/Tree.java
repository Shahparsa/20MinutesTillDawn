package com.tilldawn.models.Enemies;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.models.CollisionRect;

public class Tree extends Enemy {
    {
        Texture frame1 = new Texture("Enemies/Tree/T_TreeMonster_0.png");
        Texture frame2 = new Texture("Enemies/Tree/T_TreeMonster_1.png");
        Texture frame3 = new Texture("Enemies/Tree/T_TreeMonster_2.png");
        TextureRegion[] treeRegions = new TextureRegion[]{
            new TextureRegion(frame1),
            new TextureRegion(frame2),
            new TextureRegion(frame3)
        };
        animation = new Animation<>(1f, treeRegions);
    }

    public Tree(float x, float y) {
        Texture texture = new Texture("Enemies/Tree/T_TreeMonster_0.png");
        this.x = x;
        this.y = y;
        this.damage = 1;
        this.hp = -1;
        this.collisionRect = new CollisionRect(x , y , texture.getWidth()*2 , texture.getHeight()*2);
        this.sprite = new Sprite(texture);
        this.speed = 0;
        this.sprite.setSize(texture.getWidth()*2 , texture.getHeight()*2);
        sprite.setSize(texture.getWidth()*2, texture.getHeight()*2);
    }

}
