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
        super(x , y , -1 , 1 , 0);
        Texture texture = new Texture("Enemies/Tree/T_TreeMonster_0.png");
        this.collisionRect = new CollisionRect(x , y , texture.getWidth() , texture.getHeight());
        this.sprite = new Sprite(texture);
        this.sprite.setSize(texture.getWidth()*2 , texture.getHeight()*2);
    }

}
