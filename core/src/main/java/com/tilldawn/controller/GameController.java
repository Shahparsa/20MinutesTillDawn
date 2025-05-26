package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.Enemies.Enemy;
import com.tilldawn.models.Game;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.Player;
import com.tilldawn.models.Weapon.Bullet;
import com.tilldawn.models.enums.Abilities;
import com.tilldawn.views.GameView;

public class GameController {
    private GameView view;
    private Player player;
    private WeaponController weaponController;
    private PlayerController playerController;
    private BulletController bulletController;
    private MobController mobController;

    // Shade
    private ShaderProgram lightShader;
    private float lightRadius = 150f;

    public GameController() {
        player = App.getCurrentGame().getPlayer();
        playerController = new PlayerController(App.getCurrentGame().getPlayer());
        weaponController = new WeaponController(App.getCurrentGame().getWeapon() , player);
        bulletController = new BulletController();
        mobController = new MobController();
    }


    public void setView(GameView view) {
        this.view = view;
    }


    public void updateGame(float delta, Camera camera) {
        view.getHpLabel().setText("Hp : " + player.getHp() + "/" + player.getMaxHp());
        view.getAmmoLabel().setText("Ammo : " + App.getCurrentGame().getWeapon().getAmmo() + "/" + App.getCurrentGame().getWeapon().getMaxAmmo());

        updateBackGround();

        // Time
        App.getCurrentGame().setRealTime(App.getCurrentGame().getRealTime() + delta);
        App.getCurrentGame().setTentacleMonsterTime(App.getCurrentGame().getTentacleMonsterTime() + delta);
        App.getCurrentGame().setEyeBatMonsterTime(App.getCurrentGame().getEyeBatMonsterTime() + delta);

        playerController.update(delta);
        weaponController.update(delta , camera);
        bulletUpdater(delta);
        deleteBullet();

        // Mob
        for(Enemy enemy : App.getCurrentGame().getEnemies()){
            mobController.update(delta , enemy);
        }
        if(App.getCurrentGame().getTentacleMonsterTime() > 3){
            for(int i = 0 ; i <= (int) App.getCurrentGame().getRealTime() / 30 ; i++){
                mobController.createTentacleMonster();
            }
            App.getCurrentGame().setTentacleMonsterTime(0);
        }
        if(App.getCurrentGame().getRealTime() >= App.getCurrentGame().getFullTime() / 4){
            if(App.getCurrentGame().getEyeBatMonsterTime() > 10){
                float amount = 4*App.getCurrentGame().getRealTime() - App.getCurrentGame().getFullTime() + 30;
                for(int i = 0 ; i <= (int) amount / 30 ; i++){
                    mobController.createEyeBatMonster();
                }
                App.getCurrentGame().setEyeBatMonsterTime(0);
            }
        }

    }

    private void bulletUpdater(float delta) {
        for(Bullet bullet : App.getCurrentGame().getBullets()) {
            bulletController.update(bullet, delta);
        }
    }

    private void deleteBullet() {
        for(Bullet bullet : App.getCurrentGame().getDeletedBullets()) {
            bulletController.removeNow(bullet);
        }
        App.getCurrentGame().resetDeletedBullets();
    }



    private void updateBackGround(){
//        float backgroundY = Gdx.graphics.getHeight() / 2f ;
//        float backgroundX = Gdx.graphics.getWidth() / 2f ;
        float backgroundX = GameAssetManager.getInstance().getBackgroundTexture().getWidth()/2f;
        float backgroundY = GameAssetManager.getInstance().getBackgroundTexture().getHeight()/2f;
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

}
