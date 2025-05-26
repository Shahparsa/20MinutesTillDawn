package com.tilldawn.controller;

import com.tilldawn.models.App;
import com.tilldawn.models.Enemies.Enemy;
import com.tilldawn.models.Enemies.Tree;
import com.tilldawn.models.Weapon.Bullet;
import com.tilldawn.models.enums.Abilities;

public class BulletController {

    public void update(Bullet bullet, float delta) {
        bullet.update(delta);
        if (bullet.removeExtra()) {
            App.getCurrentGame().getDeletedBullets().add(bullet);
        }
        if(!bullet.isAvailable()){
            App.getCurrentGame().getDeletedBullets().add(bullet);
        }
    }

    public void removeNow(Bullet bullet) {
        App.getCurrentGame().getBullets().remove(bullet);
    }

    public void checkCollide(Bullet bullet) {
        if(bullet.isFromPlayer()) {
            for (Enemy enemy : App.getCurrentGame().getEnemies()) {
                if (enemy.getCollisionRect().collidesWith(bullet.getCollision())) {
                    if (bullet.isAvailable()
                        && !enemy.isDead()
                        && !enemy.isDying()) {
                        bullet.useBullet();
                        if(enemy instanceof Tree){
                            continue;
                        }else {
                            enemy.setHp(enemy.getHp() - bullet.getDamage());
                            enemy.setX(enemy.getX() + bullet.getDeltaX() * 5);
                            enemy.setY(enemy.getY() + bullet.getDeltaY() * 5);
                            if (enemy.getHp() <= 0) {
                                enemy.setDying(true);
                            }
                        }
                    }
                }
            }
        }else{
            if(App.getCurrentGame().getPlayer().getCollisionRect().collidesWith(bullet.getCollision())){
                if(bullet.isAvailable()){
                    bullet.useBullet();
                    App.getCurrentGame().getPlayer().setHp(App.getCurrentGame().getPlayer().getHp() - bullet.getDamage());
                    // invincible Time
                }
            }
        }

    }



}
