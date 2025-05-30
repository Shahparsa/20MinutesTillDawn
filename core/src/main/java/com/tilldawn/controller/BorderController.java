package com.tilldawn.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tilldawn.models.App;
import com.tilldawn.models.Border;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.Player;
import com.tilldawn.models.enums.Abilities;

public class BorderController {
    Player player;
    Border border = App.getCurrentGame().getBorder();

    public BorderController(Player player) {
        this.player = player;
    }

    public void updateBorder(float delta) {
        update(delta);
        checkCollide();
        renderBorder();
    }

    public void update(float delta) {
        border.update(delta);
    }

    public void renderBorder() {
        ShapeRenderer shapeRenderer = GameAssetManager.getInstance().getShapeRenderer();
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(border.getLeft(), border.getBottom(), border.getWidth(), border.getHeight());
        shapeRenderer.end();
    }

    public boolean checkCollide() {
        if (player.getX() >= border.getRight()
            && player.getX() <= border.getLeft()
            && player.getY() <= border.getBottom()
            && player.getY() >= border.getTop()) {
            if (App.getCurrentGame().getInvincibleTime() > 0) {
                return true;
            } else {
                GameAssetManager.getInstance().getHitSFX().play();
                player.setHp(player.getHp() - 1);
                App.getCurrentGame().setInvincibleTime(1);
            }
            return true;
        }
        return false;
    }
}
