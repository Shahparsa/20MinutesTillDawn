package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.tilldawn.Main;
import com.tilldawn.models.*;
import com.tilldawn.models.Enemies.Enemy;
import com.tilldawn.models.Enemies.Tree;
import com.tilldawn.models.enums.Heroes.HeroesIdle;
import com.tilldawn.models.enums.Heroes.HeroesWalk;


public class PlayerController {
    private Player player;
    private Animation<TextureRegion> animationIdle;
    private Animation<TextureRegion> animationRun;
    // Moving
    private float stateTime = 0;
    private boolean isMoving = false;
    private boolean facingRight = true;
    private float sfxTime = 0;
    // Cheat
    private float cheatAddDamageTime = 0;

    //Damage
    private float flashTimer = 0f;
    private static final float FLASH_INTERVAL = 0.1f;
    private boolean draw = true;

    public PlayerController(Player player) {
        this.player = player;
        Hero hero = player.getHero();
        HeroesWalk heroWalk = hero.getType().getWalk();
        HeroesIdle heroIdle = hero.getType().getIdle();
        Texture frame1Idle = new Texture(heroIdle.getAnimationAddresses().get(0));
        Texture frame2Idle = new Texture(heroIdle.getAnimationAddresses().get(1));
        Texture frame3Idle = new Texture(heroIdle.getAnimationAddresses().get(2));
        Texture frame4Idle = new Texture(heroIdle.getAnimationAddresses().get(3));
        Texture frame5Idle = new Texture(heroIdle.getAnimationAddresses().get(4));
        Texture frame6Idle = new Texture(heroIdle.getAnimationAddresses().get(5));
        Texture frame1Run = new Texture(heroWalk.getAnimationAddresses().get(0));
        Texture frame2Run = new Texture(heroWalk.getAnimationAddresses().get(1));
        Texture frame3Run = new Texture(heroWalk.getAnimationAddresses().get(2));
        Texture frame4Run = new Texture(heroWalk.getAnimationAddresses().get(3));
        TextureRegion[] idleRegion = new TextureRegion[]{
            new TextureRegion(frame1Idle),
            new TextureRegion(frame2Idle),
            new TextureRegion(frame3Idle),
            new TextureRegion(frame4Idle),
            new TextureRegion(frame5Idle),
            new TextureRegion(frame6Idle),
        };
        TextureRegion[] runRegion = new TextureRegion[]{
            new TextureRegion(frame1Run),
            new TextureRegion(frame2Run),
            new TextureRegion(frame3Run),
            new TextureRegion(frame4Run),
        };
        animationIdle = new Animation<>(0.1f, idleRegion);
        animationRun = new Animation<>(0.1f, runRegion);
    }

    public void update(float delta) {
        handlePlayerInput();
        updateAnimation(delta);
        playSFX(delta);
        renderPlayer();
    }

    private void handlePlayerInput() {
        isMoving = false;
        int coefficient = 1;
        if (App.getCurrentGame().getDoubleSpeedTime() > 0) {
            coefficient = 2;
        }
        if (Gdx.input.isKeyPressed(App.getUp())) {
            player.setY(player.getY() + player.getSpeed() * coefficient);
            isMoving = true;
            if (!canGoFurther()) {
                player.setY(player.getY() - player.getSpeed() * coefficient);
            }
            player.updateRec();
        }
        if (Gdx.input.isKeyPressed(App.getRight())) {
            player.setX(player.getX() + player.getSpeed() * coefficient);
            isMoving = true;
            if (!canGoFurther()) {
                player.setX(player.getX() - player.getSpeed() * coefficient);
            }
            player.updateRec();
        }
        if (Gdx.input.isKeyPressed(App.getDown())) {
            player.setY(player.getY() - player.getSpeed() * coefficient);
            isMoving = true;
            if (!canGoFurther()) {
                player.setY(player.getY() + player.getSpeed() * coefficient);
            }
            player.updateRec();
        }
        if (Gdx.input.isKeyPressed(App.getLeft())) {
            player.setX(player.getX() - player.getSpeed() * coefficient);
            isMoving = true;
            if (!canGoFurther()) {
                player.setX(player.getX() + player.getSpeed() * coefficient);
            }
            player.updateRec();
        }
        if (Gdx.input.isKeyJustPressed(App.getCheatAddDamage())) {
//            if (cheatAddDamageTime <= 0) {
            App.getCurrentGame().getWeapon().setDamage(App.getCurrentGame().getWeapon().getDamage() + 1);
//                cheatAddDamageTime = 2f;
//            }
        }
        if (Gdx.input.isKeyJustPressed(App.getCheatAddLevel())) {
            int amount = App.getCurrentGame().getPlayer().getLevel() * 20;
            App.getCurrentGame().getPlayer().addXp(amount);
            isMoving = false;
        }
        if (Gdx.input.isKeyJustPressed(App.getCheatTime())) {
            App.getCurrentGame().setRealTime(App.getCurrentGame().getRealTime() + 60);
            isMoving = false;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            App.getCurrentGame().changeAimBot();
            isMoving = false;
        }
    }

    private boolean canGoFurther() {
        if (player.getX() < -1900
            || player.getX() > 1850) {
            return false;
        }
        if (player.getY() < -1350
            || player.getY() > 1300) {
            return false;
        }
        if(App.getCurrentGame().isActive()){
            BorderController borderController = new BorderController(App.getCurrentGame().getPlayer());
            return !borderController.checkCollide();
        }
        return true;
    }

    private void updateAnimation(float delta) {
        stateTime += delta;

        TextureRegion currentFrame = isMoving ?
            animationRun.getKeyFrame(stateTime, true) :
            animationIdle.getKeyFrame(stateTime, true);

        if (App.getCurrentGame().getInvincibleTime() > 0) {
            flashTimer += delta;
            if (flashTimer >= FLASH_INTERVAL) {
                flashTimer -= FLASH_INTERVAL;
                draw = !draw;
            }
        } else {
            flashTimer = 0;
            draw = true;
        }
        if ((Gdx.input.isKeyPressed(App.getLeft()) && facingRight) ||
            (Gdx.input.isKeyPressed(App.getRight()) && !facingRight)) {
            facingRight = !facingRight;
            flipTextures();
        }

        player.getSprite().setRegion(currentFrame);
    }

    private void renderPlayer() {
        if (draw) {
            player.getSprite().setPosition(player.getX(), player.getY());
            player.getSprite().draw(Main.getBatch());
        }
    }

    private void playSFX(float delta) {
        if(sfxTime >= 1/2f){
            if(isMoving) {
                GameAssetManager.getInstance().getFootStepSFX().play();
            }
            sfxTime = 0;
        }else {
            sfxTime += delta;
        }
    }

    private void flipTextures() {
        for (TextureRegion region : animationRun.getKeyFrames()) {
            region.flip(true, false);
        }
        for (TextureRegion region : animationIdle.getKeyFrames()) {
            region.flip(true, false);
        }
    }


    public void aimBot(Camera camera) {
        float minDistance = 50000;
        Enemy nearestEnemy = null;
        for(Enemy enemy : App.getCurrentGame().getEnemies()){
            if(enemy instanceof Tree){
                continue;
            }else {
                float newDistance = App.getDistance(enemy.getX(), enemy.getY());
                if(newDistance < minDistance){
                    minDistance = newDistance;
                    nearestEnemy = enemy;
                }
            }
        }
        if (nearestEnemy != null) {
            Vector3 screenCoords = camera.project(new Vector3(nearestEnemy.getX(), nearestEnemy.getY(), 0));
            int cursorX = Math.round(screenCoords.x);
            int cursorY = Gdx.graphics.getHeight() - Math.round(screenCoords.y);
            Gdx.input.setCursorPosition(cursorX, cursorY);
        }
    }

}
