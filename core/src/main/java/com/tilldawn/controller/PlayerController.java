package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.Hero;
import com.tilldawn.models.Player;
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
    // Cheat
    private float cheatAddDamageTime = 0;

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
        renderPlayer();
        timeUpdate(delta);
    }

    private void timeUpdate(float delta) {
        if (cheatAddDamageTime > 0) {
            cheatAddDamageTime -= delta;
        }
    }

    private void handlePlayerInput() {
        isMoving = false;
        if (Gdx.input.isKeyPressed(App.getUp())) {
            player.setY(player.getY() + player.getSpeed());
            isMoving = true;
            if (!canGoFurther()) {
                player.setY(player.getY() - player.getSpeed());
            }
            player.updateRec();
        }
        if (Gdx.input.isKeyPressed(App.getRight())) {
            player.setX(player.getX() + player.getSpeed());
            isMoving = true;
            if (!canGoFurther()) {
                player.setX(player.getX() - player.getSpeed());
            }
            player.updateRec();
        }
        if (Gdx.input.isKeyPressed(App.getDown())) {
            player.setY(player.getY() - player.getSpeed());
            isMoving = true;
            if (!canGoFurther()) {
                player.setY(player.getY() + player.getSpeed());
            }
            player.updateRec();
        }
        if (Gdx.input.isKeyPressed(App.getLeft())) {
            player.setX(player.getX() - player.getSpeed());
            isMoving = true;
            if (!canGoFurther()) {
                player.setX(player.getX() + player.getSpeed());
            }
            player.updateRec();
        }
        if (Gdx.input.isKeyPressed(App.getCheatAddDamage())) {
            if (cheatAddDamageTime <= 0) {
                App.getCurrentGame().getWeapon().setDamage(App.getCurrentGame().getWeapon().getDamage() + 1);
                cheatAddDamageTime = 2f;
            }
        }
//        if(Gdx.input.isKeyPressed(App.getCheatAddHealth())){
//            isMoving = false;
//        }
//        if(Gdx.input.isKeyPressed(App.getCheatAddLevel())){
//            isMoving = false;
//        }
//        if(Gdx.input.isKeyPressed(App.getCheatTime())){
//            isMoving = false;
//        }
//        if(Gdx.input.isKeyPressed(App.getCheatSpawnBoss())){
//            isMoving = false;
//        }
    }

    private boolean canGoFurther() {
        if (player.getX() < -1900
            || player.getX() > 1850) {
            return false;
        }
        if (player.getY() < -1350
            || player.getY() > 1300){
            return false;
        }
        return true;
    }

    private void updateAnimation(float delta) {
        stateTime += delta;

        TextureRegion currentFrame = isMoving ?
            animationRun.getKeyFrame(stateTime, true) :
            animationIdle.getKeyFrame(stateTime, true);

        if ((Gdx.input.isKeyPressed(App.getLeft()) && facingRight) ||
            (Gdx.input.isKeyPressed(App.getRight()) && !facingRight)) {
            facingRight = !facingRight;
            flipTextures();
        }

        player.getSprite().setRegion(currentFrame);
    }

    private void renderPlayer() {
        player.getSprite().setPosition(player.getX(), player.getY());
        player.getSprite().draw(Main.getBatch());
    }

    private void flipTextures() {
        for (TextureRegion region : animationRun.getKeyFrames()) {
            region.flip(true, false);
        }
        for (TextureRegion region : animationIdle.getKeyFrames()) {
            region.flip(true, false);
        }
    }
}
