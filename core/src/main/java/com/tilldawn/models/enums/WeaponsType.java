package com.tilldawn.models.enums;

public enum WeaponsType {
    DualSMG("Weapons/SMG/T_DualSMGs_Icon.png" , 8 , 1 , 2 , 24 , 6),
    ShotGun("Weapons/Shotgun/T_Shotgun_SS_0.png" , 10 , 4 , 1 , 2 , 2),
    Revolver("Weapons/Revolver/RevolverStill.png" , 20 , 1 , 1 , 5 , 4);
    private final String texturePath;
    private final int damage;
    private final int projectile;
    private final int reloadTime;
    private final int maxAmmo;
    private final int fireRate;

    WeaponsType(String texturePath, int damage, int projectile, int reloadTime, int maxAmmo , int fireRate) {
        this.texturePath = texturePath;
        this.damage = damage;
        this.projectile = projectile;
        this.reloadTime = reloadTime;
        this.maxAmmo = maxAmmo;
        this.fireRate = fireRate;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public int getDamage() {
        return damage;
    }

    public int getProjectile() {
        return projectile;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public int getFireRate() {
        return fireRate;
    }
}
