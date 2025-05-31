package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.tilldawn.Main;
import com.tilldawn.controller.MenuControllers.AbilityMenuController;
import com.tilldawn.controller.MenuControllers.HintMenuController;
import com.tilldawn.models.*;
import com.tilldawn.models.Enemies.Elder;
import com.tilldawn.models.Enemies.Enemy;
import com.tilldawn.models.Weapon.Bullet;
import com.tilldawn.models.enums.Abilities;
import com.tilldawn.views.GameView;
import com.tilldawn.views.Menus.AbilityMenuView;
import com.tilldawn.views.Menus.EndMenuView;
import com.tilldawn.views.Menus.HintMenuView;

public class GameController {
    private GameView view;
    private Player player;

    //Controller
    private WeaponController weaponController;
    private PlayerController playerController;
    private BulletController bulletController;
    private CoinController coinController;
    private MobController mobController;
    private BorderController borderController;

    // Shade
    private ShaderProgram lightShader;
    private float lightRadius = 150f;

    public GameController() {
        player = App.getCurrentGame().getPlayer();
        playerController = new PlayerController(App.getCurrentGame().getPlayer());
        weaponController = new WeaponController(App.getCurrentGame().getWeapon(), player);
        bulletController = new BulletController();
        coinController = new CoinController();
        mobController = new MobController();
        borderController = new BorderController(App.getCurrentGame().getPlayer());
    }


    public void setView(GameView view) {
        this.view = view;
    }


    public void updateGame(float delta, Camera camera) {
        view.getHpLabel().setText("Hp : " + player.getHp() + "/" + player.getMaxHp());
        view.getAmmoLabel().setText(App.getCurrentGame().getWeapon().getAmmo() + "/" + App.getCurrentGame().getWeapon().getMaxAmmo());
        view.getKillLabel().setText("Kill: " + player.getKills());
        view.getLevelLabel().setText("Level: " + player.getLevel());
        view.getXpLevelLabel().setText("XP: " + player.getXp());

        float timeDiff = App.getCurrentGame().getFullTime() * 60 - App.getCurrentGame().getRealTime();
        int minutes = (int) (timeDiff / 60);
        int seconds = (int) (timeDiff % 60);
        String formattedTime = String.format("Time: %02d:%02d", minutes, seconds);
        view.getTimeLabel().setText(formattedTime);

        view.getXpLevelProgressBar().setRange(0, App.getCurrentGame().getPlayer().getLevel() * 20);
        view.getXpLevelProgressBar().setValue(player.getXp());

        updateBackGround();
        if(App.getCurrentGame().isActive()){
            boolean isElderAlive = false;
            for(Enemy enemy : App.getCurrentGame().getEnemies()){
                if(enemy instanceof Elder){
                    isElderAlive = true;
                }
            }
            if(!isElderAlive){
                App.getCurrentGame().setActive(false);
                App.getCurrentGame().setBorder(new Border(40));
            }
        }

        // Time
        App.getCurrentGame().setRealTime(App.getCurrentGame().getRealTime() + delta);
        App.getCurrentGame().setTentacleMonsterTime(App.getCurrentGame().getTentacleMonsterTime() + delta);
        App.getCurrentGame().setEyeBatMonsterTime(App.getCurrentGame().getEyeBatMonsterTime() + delta);

        playerController.update(delta);
        weaponController.update(delta, camera);
        bulletUpdater(delta);
        deleteBullet();

        // Mob
        for (Enemy enemy : App.getCurrentGame().getEnemies()) {
            mobController.update(delta, enemy);
            mobController.checkCollide(enemy);
        }
        deleteEnemies();

        // Spawn
        if (App.getCurrentGame().getTentacleMonsterTime() > 3) {
            for (int i = 0; i <= (int) App.getCurrentGame().getRealTime() / 30; i++) {
                mobController.createTentacleMonster();
            }
            App.getCurrentGame().setTentacleMonsterTime(0);
        }
        if (App.getCurrentGame().getRealTime() >= App.getCurrentGame().getFullTime() * 60 / 4) {
            if (App.getCurrentGame().getEyeBatMonsterTime() > 10) {
                float amount = 4 * App.getCurrentGame().getRealTime() - App.getCurrentGame().getFullTime() * 60 + 30;
                for (int i = 0; i <= (int) amount / 30; i++) {
                    mobController.createEyeBatMonster();
                }
                App.getCurrentGame().setEyeBatMonsterTime(0);
            }
        }
        if(App.getCurrentGame().getRealTime() >= App.getCurrentGame().getFullTime() * 60 / 2
            || Gdx.input.isKeyJustPressed(App.getCheatSpawnBoss())) {
            if(!App.getCurrentGame().isSpawned()){
                mobController.createElderBoss();
                App.getCurrentGame().spawnElder();
                App.getCurrentGame().setActive(true);
            }
        }

        //Coin
        for (Coin coin : App.getCurrentGame().getCoins()) {
            coinController.checkCollide(coin);
            coinController.render(coin);
        }
        deleteCoins();

        // Player's time
        if (App.getCurrentGame().getDoubleDamageTime() > 0) {
            App.getCurrentGame().setDoubleDamageTime(App.getCurrentGame().getDoubleDamageTime() - delta);
        }
        if (App.getCurrentGame().getDoubleSpeedTime() > 0) {
            App.getCurrentGame().setDoubleSpeedTime(App.getCurrentGame().getDoubleSpeedTime() - delta);
        }
        if (App.getCurrentGame().getInvincibleTime() > 0) {
            App.getCurrentGame().setInvincibleTime(App.getCurrentGame().getInvincibleTime() - delta);
        }

        //Aimbot
        if (App.getCurrentGame().isAimBot()) {
            playerController.aimBot(camera);
        }
    }

    private void bulletUpdater(float delta) {
        for (Bullet bullet : App.getCurrentGame().getBullets()) {
            bulletController.update(bullet, delta);
            bulletController.checkCollide(bullet);
        }
    }

    private void deleteBullet() {
        for (Bullet bullet : App.getCurrentGame().getDeletedBullets()) {
            bulletController.removeNow(bullet);
        }
        App.getCurrentGame().resetDeletedBullets();
    }

    private void deleteEnemies() {
        for (Enemy enemy : App.getCurrentGame().getDeletedEnemies()) {
            mobController.removeNow(enemy);
        }
        App.getCurrentGame().resetDeletedEnemies();
    }

    private void deleteCoins() {
        for (Coin coin : App.getCurrentGame().getDeletedCoins()) {
            coinController.removeNow(coin);
        }
        App.getCurrentGame().resetDeletedCoins();
    }

    private void updateBackGround() {
        float backgroundX = GameAssetManager.getInstance().getBackgroundTexture().getWidth() / 2f;
        float backgroundY = GameAssetManager.getInstance().getBackgroundTexture().getHeight() / 2f;
        backgroundX *= -1;
        backgroundY *= -1;
        Main.getBatch().draw(GameAssetManager.getInstance().getBackgroundTexture(), backgroundX, backgroundY);

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void handleEndGame(boolean mode) {
        if (mode) {
            GameAssetManager.getInstance().getWinSFX().play();
        } else {
            GameAssetManager.getInstance().getLoseSFX().play();
        }
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new EndMenuView(mode, view
            , GameAssetManager.getInstance().getSkin()));
    }

    public void checkLevelUp(GameView gameView) {
        Player player = App.getCurrentGame().getPlayer();
        if (player.isLevelUp()) {
            GameAssetManager.getInstance().getLevelUpSFx().play();
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new AbilityMenuView(new AbilityMenuController()
                , gameView, GameAssetManager.getInstance().getSkin()));
            player.setLevelUp(false);
        }
    }

    public void updateBorder(Camera camera , float delta) {
        if(App.getCurrentGame().isActive()){
            borderController.updateBorder(camera , delta);
        }
    }
}
