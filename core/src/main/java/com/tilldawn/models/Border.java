package com.tilldawn.models;

import java.awt.*;

public class Border {
    private float right;
    private float left;
    private float top;
    private float bottom;
    private float width;
    private float height;
    private final float speed;

    public Border(float speed) {
        this.speed = speed;
        this.left = GameAssetManager.getInstance().getLeft();
        this.right = GameAssetManager.getInstance().getRight();
        this.top = GameAssetManager.getInstance().getTop();
        this.bottom = GameAssetManager.getInstance().getBottom();
        this.width = right - left;
        this.height = top - bottom;
    }

    public void update(float delta) {
        if(App.getCurrentGame().isActive()){
            width = Math.max(1000 , width - speed * delta * 2);
            height = Math.max(500 , height - speed * delta * 2);
            if(width != 20){
                left += speed * delta;
                right = left + width;
            }
            if(height != 10){
                bottom += speed * delta;
                top = bottom + height;
            }
        }
    }

    public float getSpeed() {
        return speed;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getBottom() {
        return bottom;
    }

    public float getTop() {
        return top;
    }

    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }


}
