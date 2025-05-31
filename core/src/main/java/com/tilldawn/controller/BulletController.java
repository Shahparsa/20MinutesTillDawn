package com.tilldawn.controller;

import com.tilldawn.models.App;
import com.tilldawn.models.Enemies.Enemy;
import com.tilldawn.models.Enemies.Tree;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.Weapon.Bullet;
import com.tilldawn.models.enums.Abilities;

public class BulletController {

    public void update(Bullet bullet, float delta) {
        bullet.update(delta);
        if (bullet.removeExtra()) {
            App.getCurrentGame().getDeletedBullets().add(bullet);
        }
        if (!bullet.isAvailable()) {
            App.getCurrentGame().getDeletedBullets().add(bullet);
        }
    }

    public void removeNow(Bullet bullet) {
        App.getCurrentGame().getBullets().remove(bullet);
    }

    public void checkCollide(Bullet bullet) {
        if (bullet.isFromPlayer()) {
            for (Enemy enemy : App.getCurrentGame().getEnemies()) {
                if (enemy.getCollisionRect().collidesWith(bullet.getCollision())) {
                    if (bullet.isAvailable()
                        && !enemy.isDead()
                        && !enemy.isDying()) {
                        bullet.useBullet();
                        if (enemy instanceof Tree) {
                            continue;
                        } else {
                            int coefficient = 1;
                            if (App.getCurrentGame().getDoubleDamageTime() > 0) {
                                coefficient = 2;
                            }
                            enemy.setHp(enemy.getHp() - bullet.getDamage() * coefficient);
                            enemy.setX(enemy.getX() + bullet.getDeltaX() * 5);
                            enemy.setY(enemy.getY() + bullet.getDeltaY() * 5);
                            if (enemy.getHp() <= 0) {
                                enemy.setDying(true);
                                GameAssetManager.getInstance().getDeathSFX().play();
                            }
                        }
                    }
                }
            }
        } else {
            if (App.getCurrentGame().getPlayer().getCollisionRect().collidesWith(bullet.getCollision())) {
                if (bullet.isAvailable()) {
                    bullet.useBullet();
                    if (App.getCurrentGame().getInvincibleTime() <= 0) {
                        App.getCurrentGame().getPlayer().setHp(App.getCurrentGame().getPlayer().getHp() - bullet.getDamage());
                        GameAssetManager.getInstance().getHitSFX().play();
                        App.getCurrentGame().setInvincibleTime(1);
                    }
                }
            }
        }

    }


}
