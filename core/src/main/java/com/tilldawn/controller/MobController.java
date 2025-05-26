package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.Coin;
import com.tilldawn.models.Enemies.*;
import com.tilldawn.models.Player;
import com.tilldawn.models.Weapon.Bullet;

import java.util.Random;

public class MobController {
    static Random random = new Random();

    static {
        //Size -1900 <= x <= 1850 & -1350 <= y <= 1300
        int number = App.getCurrentGame().getNumberOfTree();
        while (number > 0) {
            float x = random.nextFloat(1850 + 1900) - 1900;
            float y = random.nextFloat(1300 + 1350) - 1350;
            Tree tree = new Tree(x, y);
            App.getCurrentGame().getEnemies().add(tree);
            number--;
        }
    }

    public void checkCollide(Enemy enemy){
        Player player = App.getCurrentGame().getPlayer();
        if(enemy.getCollisionRect().collidesWith(player.getCollisionRect())){
            player.setHp(player.getHp() - enemy.getDamage());
            // TODO : Animation
        }
    }

    public void createTentacleMonster() {
        float x = random.nextFloat(1850 + 1900) - 1900;
        float y = random.nextFloat(1300 + 1350) - 1350;
        TentacleMonster tentacleMonster = new TentacleMonster(x, y);
        App.getCurrentGame().getEnemies().add(tentacleMonster);
    }

    public void createEyeBatMonster() {
        float x = random.nextFloat(1850 + 1900) - 1900;
        float y = random.nextFloat(1300 + 1350) - 1350;
        EyeBat eyeBat = new EyeBat(x, y);
        App.getCurrentGame().getEnemies().add(eyeBat);
    }

    public void update(float delta, Enemy enemy) {
        if(enemy.isDying()){
            if(!enemy.isDead()){
                updateExplosionAnimation(delta , enemy);
            }
        }else {
            if (enemy instanceof Tree) {
                Tree tree = (Tree) enemy;
                updateAnimation(delta, tree);
                renderMob(tree);
            } else if (enemy instanceof EyeBat) {
                EyeBat eyeBat = (EyeBat) enemy;
                updatePosMob(delta, eyeBat);
                if (eyeBat.getShootTime() > 3) {
                    shootMob(eyeBat);
                    eyeBat.setShootTime(0);
                } else {
                    eyeBat.setShootTime(eyeBat.getShootTime() + delta);
                }
                updatePosMob(delta, eyeBat);
                updateAnimation(delta, eyeBat);
                renderMob(eyeBat);
            } else if (enemy instanceof TentacleMonster) {
                TentacleMonster tentacleMonster = (TentacleMonster) enemy;
                updatePosMob(delta, tentacleMonster);
                updateAnimation(delta, tentacleMonster);
                renderMob(tentacleMonster);
            } else if (enemy instanceof Elder) {

            } else if (enemy instanceof ShubNiggurath) {

            }
        }
    }

    private void shootMob(Enemy enemy) {
        Player player = App.getCurrentGame().getPlayer();
        float deltaX = player.getX() - enemy.getX();
        float deltaY = player.getY() - enemy.getY();
        float p = deltaX * deltaX + deltaY * deltaY;
        p = (float) Math.sqrt(p);
        Bullet bullet = new Bullet(enemy.getX(), enemy.getY(), deltaX / p, deltaY / p, 1);
        App.getCurrentGame().getBullets().add(bullet);
    }

    private void updatePosMob(float delta, Enemy enemy) {
        float realDistance = getDistance(enemy.getX(), enemy.getY());
        int newX = 0;
        int newY = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                float newDistance = getDistance(enemy.getX() + i, enemy.getY() + j);
                if (newDistance < realDistance) {
                    newX = i;
                    newY = j;
                    realDistance = newDistance;
                }
            }
            enemy.setX(enemy.getX() + newX * delta * enemy.getSpeed());
            enemy.setY(enemy.getY() + newY * delta * enemy.getSpeed());
            enemy.getCollisionRect().setX(enemy.getX());
            enemy.getCollisionRect().setY(enemy.getY());
        }
    }

    private float getDistance(float x, float y) {
        Player player = App.getCurrentGame().getPlayer();
        float dx = x - player.getX();
        float dy = y - player.getY();
        if (dy < 0) {
            dy *= -1;
        }
        if (dx < 0) {
            dx *= -1;
        }
        return dx + dy;
    }

    private void updateAnimation(float delta, Enemy enemy) {
        enemy.setStateTime(enemy.getStateTime() + delta);

        TextureRegion currentFrame = enemy.getAnimation().getKeyFrame(enemy.getStateTime(), true);

        if ((Gdx.input.isKeyPressed(App.getLeft()) && enemy.isFacingRight()) ||
            (Gdx.input.isKeyPressed(App.getRight()) && !enemy.isFacingRight())) {
            enemy.changeFacingRight();
            flipTextures(enemy);
        }

        enemy.getSprite().setRegion(currentFrame);
    }

    private void flipTextures(Enemy enemy) {
        for (TextureRegion region : enemy.getAnimation().getKeyFrames()) {
            region.flip(true, false);
        }
    }

    private void renderMob(Enemy enemy) {
        enemy.getSprite().setPosition(enemy.getX(), enemy.getY());
        enemy.getSprite().draw(Main.getBatch());
    }

    private void updateExplosionAnimation(float delta , Enemy enemy){
        enemy.setExplosionTime(enemy.getExplosionTime() + delta);

        TextureRegion currentFrame = enemy.getExplosionAnimation().getKeyFrame(enemy.getExplosionTime(), false);

        enemy.getSprite().setRegion(currentFrame);
        enemy.getSprite().setPosition(enemy.getX(), enemy.getY());
        enemy.getSprite().draw(Main.getBatch());

        if (enemy.getExplosionAnimation().isAnimationFinished(enemy.getExplosionTime())) {
            // Mark for deletion
            enemy.setDead(true);
            App.getCurrentGame().getDeletedEnemies().add(enemy);
            App.getCurrentGame().getPlayer().setKills(App.getCurrentGame().getPlayer().getKills() + 1);
            // TODO : SFX
            Coin coin = new Coin(enemy.getX() , enemy.getY());
            App.getCurrentGame().getCoins().add(coin);
        }
    }

    public void removeNow(Enemy enemy) {
        App.getCurrentGame().getEnemies().remove(enemy);
    }
}
