package com.tilldawn.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tilldawn.models.enums.AvatarType;

public class User {
    private String username;
    private String password;
    private SecurityQuestion securityQuestion;
    private String avatarPath;
    private int score;
    private int kills;
    private float timeAlive;

    public User(String username, String password, SecurityQuestion securityQuestion) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.score = 0;
        this.kills = 0;
        this.timeAlive = 0;
        this.avatarPath = AvatarType.getAvatarRandom().getTexturePath();
    }

    public User(String username, String password, SecurityQuestion securityQuestion, String avatarPath
        , int score, int kills, float timeAlive) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.score = score;
        this.avatarPath = avatarPath;
        this.kills = kills;
        this.timeAlive = timeAlive;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(SecurityQuestion securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getScoreString() {
        return String.valueOf(score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public float getTimeAlive() {
        return timeAlive;
    }

    public void setTimeAlive(float timeAlive) {
        this.timeAlive = timeAlive;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
