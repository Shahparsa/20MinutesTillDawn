package com.tilldawn.controller;

import com.tilldawn.models.App;
import com.tilldawn.models.Weapon.Bullet;
import com.tilldawn.models.enums.Abilities;

public class BulletController {

    public void update(Bullet bullet , float delta) {
        bullet.update(delta);
        if(bullet.removeExtra()){
            App.getCurrentGame().getDeletedBullets().add(bullet);
        }
    }

    public void removeNow(Bullet bullet){
        App.getCurrentGame().getBullets().remove(bullet);
    }

}
