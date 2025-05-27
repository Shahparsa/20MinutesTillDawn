package com.tilldawn.models.enums;

import com.tilldawn.models.App;

public enum Abilities {
    VITALITY("+1 Max Hp", "+1 Maximale HP"),
    DAMAGER("+25% Damage For 10s", "+25% Schaden für 10s"),
    PROCREASE("+1 Projectile", "+1 Projektil"),
    AMOCREASE("+5 Max Ammo", "+5 Maximale Munition"),
    SPEEDY("2x Speed For 10s", "2x Geschwindigkeit für 10s");

    private final String english;
    private final String german;

    Abilities(String english, String german) {
        this.english = english;
        this.german = german;
    }

    public String getLanguage() {
        if(App.getLanguage().equals("English")) {
            return english;
        }else {
            return german;
        }
    }
}
