package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.Player;
import com.tilldawn.models.Weapon.Bullet;
import com.tilldawn.models.Weapon.Weapon;

public class WeaponController {
    private final Player player;
    private final Weapon weapon;
    private final Sprite sprite;
    private float reloadTimer = 0;
    private boolean isReloading = false;
    private float timeLastShot = 0;
    private boolean facingRight = true;

    public WeaponController(Weapon weapon , Player player) {
        this.weapon = weapon;
        this.player = player;
        this.sprite = weapon.getSprite();
        sprite.setSize(sprite.getWidth() * 1.5f, sprite.getHeight() * 1.5f);
    }

    public void update(float delta , Camera camera) {
        if(isReloading){
            reloadTimer -= delta;
        }
        if(timeLastShot > 0){
            timeLastShot -= delta;
        }
        if(reloadTimer <= 0 && isReloading){
            weapon.reloadAmmo();
            isReloading = false;
        }
        if(weapon.getAmmo() == 0){
            if(App.isAutoReload()){
                reload();
            }
        }

        handlePlayerInputs(camera);
        updateWeaponPosition(camera);
        sprite.setPosition(player.getX() + 20, player.getY() + 20);
        sprite.setSize(40 , 40);
        sprite.draw(Main.getBatch());
    }

    private void updateWeaponPosition(Camera camera) {
        float offsetX = facingRight ? 15 : -15;
        sprite.setPosition(player.getX() + offsetX, player.getY() + 10);

        sprite.setFlip(!facingRight, false);
        sprite.setRotation(calculateWeaponRotation(camera));
    }

    private float calculateWeaponRotation(Camera camera) {
        Vector3 mouseScreenPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

        Vector3 mouseWorldPos = camera.unproject(mouseScreenPos);

        float deltaX = mouseWorldPos.x - player.getX();
        float deltaY = mouseWorldPos.y - player.getY();

        return MathUtils.atan2(
            deltaY,
            deltaX
        ) * MathUtils.radiansToDegrees;
    }


    private void handlePlayerInputs(Camera camera){
        if (Gdx.input.isKeyPressed(App.getRealShoot())){
            shoot(player , camera);
        }
        if(Gdx.input.isKeyPressed(App.getReload())){
            reload();
        }
    }


    public void reload() {
        if(!isReloading){
            reloadTimer = weapon.getReloadTime();
            isReloading = true;
        }
    }

    public void shoot(Player player , Camera camera) {
        if(!isReloading && weapon.getAmmo() != 0 && timeLastShot <= 0){
            Vector3 mouseScreenPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

            Vector3 mouseWorldPos = camera.unproject(mouseScreenPos);

            float deltaX = mouseWorldPos.x - player.getX();
            float deltaY = mouseWorldPos.y - player.getY();

            float p = deltaX * deltaX + deltaY * deltaY;
             p = (float) Math.sqrt(p);
            Bullet bullet = new Bullet(player.getX() , player.getY() , deltaX / p , deltaY / p);
            App.getCurrentGame().getBullets().add(bullet);
            weapon.useAmmo();
            timeLastShot = 1f / weapon.getFireRate();
        }
    }
}
