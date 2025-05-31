package com.tilldawn.controller;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tilldawn.models.App;
import com.tilldawn.models.Border;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.Player;
import com.tilldawn.models.enums.Abilities;

import java.util.Calendar;

public class BorderController {
    Player player;
    Border border = App.getCurrentGame().getBorder();

    public BorderController(Player player) {
        this.player = player;
    }

    public void updateBorder(Camera camera , float delta) {
        update(delta);
        checkCollide();
        renderBorder(camera);
    }

    public void update(float delta) {
        border.update(delta);
    }

    public void renderBorder(Camera camera) {
        ShapeRenderer shapeRenderer = GameAssetManager.getInstance().getShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(border.getLeft(), border.getBottom(), border.getWidth(), border.getHeight());
        shapeRenderer.end();
    }

    public boolean checkCollide() {
        if (player.getX() >= border.getRight()
            || player.getX() <= border.getLeft()
            || player.getY() <= border.getBottom()
            || player.getY() >= border.getTop()) {
            if (App.getCurrentGame().getInvincibleTime() <= 0) {
                GameAssetManager.getInstance().getHitSFX().play();
                player.setHp(player.getHp() - 1);
                App.getCurrentGame().setInvincibleTime(5);
            }
            while (player.getX() <= border.getLeft()) {
                player.setX(player.getX() + border.getSpeed());
            }
            while (player.getY() <= border.getBottom()) {
                player.setY(player.getY() + border.getSpeed());
            }
            while (player.getY() >= border.getTop()) {
                player.setY(player.getY() - border.getSpeed());
            }
            while (player.getX() >= border.getRight()) {
                player.setX(player.getX() - border.getSpeed());
            }
            return true;
        }
        return false;
    }
}
