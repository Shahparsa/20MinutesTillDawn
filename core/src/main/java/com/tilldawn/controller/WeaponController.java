package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
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

    public WeaponController(Weapon weapon, Player player) {
        this.weapon = weapon;
        this.player = player;
        this.sprite = weapon.getSprite();
        sprite.setSize(sprite.getWidth() * 1.5f, sprite.getHeight() * 1.5f);
    }

    public void update(float delta, Camera camera) {
        if (isReloading) {
            reloadTimer -= delta;
        }
        if (timeLastShot > 0) {
            timeLastShot -= delta;
        }
        if (reloadTimer <= 0 && isReloading) {
            weapon.reloadAmmo();
            isReloading = false;
        }
        if (weapon.getAmmo() == 0) {
            if (App.isAutoReload()) {
                reload();
            }
        }

        handlePlayerInputs(camera);
        updateWeaponPosition(camera);
        sprite.setPosition(player.getX() + 20, player.getY() + 20);
        sprite.setSize(40, 40);
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


    private void handlePlayerInputs(Camera camera) {
        if (App.getShoot() >= 1000) {
            if (Gdx.input.isButtonPressed(App.getRealShoot())) {
                shoot(player, camera);
            }
        } else {
            if (Gdx.input.isKeyPressed(App.getRealShoot())) {
                shoot(player, camera);
            }
        }
        if (Gdx.input.isKeyPressed(App.getReload())) {
            reload();
        }
    }


    public void reload() {
        if (!isReloading) {
            reloadTimer = weapon.getReloadTime();
            isReloading = true;
        }
    }

    public void shoot(Player player, Camera camera) {
        if (!isReloading && weapon.getAmmo() != 0 && timeLastShot <= 0) {
            Vector3 mouseScreenPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            Vector3 mouseWorldPos = camera.unproject(mouseScreenPos);

            float deltaX = mouseWorldPos.x - player.getX();
            float deltaY = mouseWorldPos.y - player.getY();
            float length = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            float dirX = deltaX / length;
            float dirY = deltaY / length;

            int projectileCount = weapon.getProjectile();
            float spreadAngle = 15f;

            if (projectileCount == 1) {
                Bullet bullet = new Bullet(player.getX(), player.getY(), dirX, dirY);
                App.getCurrentGame().getBullets().add(bullet);
            } else {
                float angleStep = spreadAngle / (projectileCount - 1);
                float startAngle = -spreadAngle / 2f;

                for (int i = 0; i < projectileCount; i++) {
                    float currentAngle = startAngle + i * angleStep;
                    float angleRad = (float) Math.toRadians(currentAngle);

                    float newDirX = (float) (dirX * Math.cos(angleRad) - dirY * Math.sin(angleRad));
                    float newDirY = (float) (dirX * Math.sin(angleRad) + dirY * Math.cos(angleRad));

                    Bullet bullet = new Bullet(player.getX(), player.getY(), newDirX, newDirY);
                    App.getCurrentGame().getBullets().add(bullet);
                }
            }
            GameAssetManager.getInstance().getShootSFX().play();
            weapon.useAmmo();
            timeLastShot = 1f / weapon.getFireRate();
        }
    }
}
