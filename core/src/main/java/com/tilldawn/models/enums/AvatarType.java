package com.tilldawn.models.enums;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public enum AvatarType {
    Abby("Avatars/T_Abby_Portrait.png" , "Abby"),
    Dasher("Avatars/T_Dasher_Portrait.png" , "Dasher"),
    Diamond("Avatars/T_Diamond_Portrait.png" , "Diamond"),
    Hastur("Avatars/T_Hastur_Portrait.png" , "Hastur"),
    Hina("Avatars/T_Hina_Portrait.png", "Hina"),
    Lilith("Avatars/T_Lilith_Portrait.png" , "Lilith"),
    Luna("Avatars/T_Luna_Portrait.png" , "Luna"),
    Raven("Avatars/T_Raven_Portrait.png", "Raven"),
    Scarlet("Avatars/T_Scarlett_Portrait.png" , "Scarlet"),
    Shane("Avatars/T_Shana_Portrait.png" , "Shane"),
    Spark("Avatars/T_Spark_Portrait.png" , "Spark"),
    Yuki("Avatars/T_Yuki_Portrait.png" , "Yuki"),;

    private final String texturePath;
    private final String name;
    private static final Random RANDOM = new Random();
    private static final AvatarType[] VALUES = values();

    AvatarType(String texturePath , String name) {
        this.texturePath = texturePath;
        this.name = name;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public static AvatarType getAvatarRandom() {
        return VALUES[RANDOM.nextInt(VALUES.length)];
    }

    public String getName() {
        return name;
    }

    public static AvatarType getAvatarByName(String name) {
        for(AvatarType avatar : values()) {
            if(avatar.getName().equalsIgnoreCase(name)) {
                return avatar;
            }
        }
        return null;
    }

    public static String getAvatarNameByPath(String path) {
        for(AvatarType avatar : values()) {
            if(avatar.getTexturePath().equalsIgnoreCase(path)) {
                return avatar.getName();
            }
        }
        return null;
    }
}
