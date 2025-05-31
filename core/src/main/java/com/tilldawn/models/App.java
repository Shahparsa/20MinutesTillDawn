package com.tilldawn.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.tilldawn.Main;
import com.tilldawn.models.enums.Heroes.HeroesType;

import java.util.ArrayList;

public class App {
    private static User currentUser = null;
    private static ArrayList<User> users = new ArrayList<>();
    private static Game currentGame = null;
    private static String language = "English";
    // Music
    private static Music currentMusic = null;
    private static float musicVolume = 0f;
    private static String currentMusicPath = null;
    // Booleans
    private static boolean isSFX = true;
    private static boolean isAutoReload = true;
    private static boolean isBlackWhite = false;
    // Keybindings
    private static int up = Input.Keys.W;
    private static int down = Input.Keys.S;
    private static int left = Input.Keys.A;
    private static int right = Input.Keys.D;
    private static int reload = Input.Keys.R;
    private static int shoot = Input.Buttons.LEFT + 1000;
    private static boolean isShootMouseButton = true;
    private static int cheatTime = Input.Keys.NUMPAD_1;
    private static int cheatAddLevel = Input.Keys.NUMPAD_2;
    private static int cheatAddHealth = Input.Keys.NUMPAD_3;
    private static int cheatSpawnBoss = Input.Keys.NUMPAD_4;
    private static int cheatAddDamage = Input.Keys.NUMPAD_5;
    private static int pauseMenu = Input.Keys.ESCAPE;
    //Hero selection
    private static HeroesType hero = null;



    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void changeSFX() {
        isSFX = !isSFX;
    }

    public static boolean isSFX() {
        return isSFX;
    }

    public static void changeBlackWhite() {
        isBlackWhite = !isBlackWhite;
        Main.getMain().setBlackAndWhite();
    }

    public static boolean isBlackWhite() {
        return isBlackWhite;
    }

    public static void changeAutoReload() {
        isAutoReload = !isAutoReload;
    }

    public static boolean isAutoReload() {
        return isAutoReload;
    }

    public static void changeLanguage() {
        if (language.equals("English")) {
            language = "German";
        } else {
            language = "English";
        }
    }

    public static String getLanguage() {
        return language;
    }

    public static void setKey(String action, int keyCode) {
        switch (action.toUpperCase()) {
            case "UP":
                up = keyCode;
                break;
            case "DOWN":
                down = keyCode;
                break;
            case "LEFT":
                left = keyCode;
                break;
            case "RIGHT":
                right = keyCode;
                break;
            case "RELOAD":
                reload = keyCode;
                break;
            case "SHOOT":
                shoot = keyCode;
                isShootMouseButton = (keyCode >= Input.Buttons.LEFT && keyCode <= Input.Buttons.MIDDLE);
                break;
        }
    }

    public static String getKeyName(int keyOrButtonCode) {
        if (keyOrButtonCode >= 1000) {
            int mouseButton = keyOrButtonCode - 1000;
            switch (mouseButton) {
                case Input.Buttons.LEFT:
                    return "MOUSE LEFT";
                case Input.Buttons.RIGHT:
                    return "MOUSE RIGHT";
                case Input.Buttons.MIDDLE:
                    return "MOUSE MIDDLE";
                default:
                    return "MOUSE " + mouseButton;
            }
        }
        return Input.Keys.toString(keyOrButtonCode);
    }

    public static int getUp() {
        return up;
    }

    public static int getDown() {
        return down;
    }

    public static int getLeft() {
        return left;
    }

    public static int getRight() {
        return right;
    }

    public static int getReload() {
        return reload;
    }

    public static int getShoot() {
        return shoot;
    }

    public static int getRealShoot() {
        if (isShootMouseButton) {
            return shoot - 1000;
        } else {
            return shoot;
        }
    }

    public static int getCheatAddDamage() {
        return cheatAddDamage;
    }

    public static int getCheatSpawnBoss() {
        return cheatSpawnBoss;
    }

    public static int getCheatAddHealth() {
        return cheatAddHealth;
    }

    public static int getCheatAddLevel() {
        return cheatAddLevel;
    }

    public static int getCheatTime() {
        return cheatTime;
    }

    public static void setMusicVolume(float musicVolume) {
        musicVolume = Math.max(0, Math.min(1, musicVolume));

        if (currentMusic != null) {
            currentMusic.setVolume(musicVolume);
        }
    }

    public static float getMusicVolume() {
        return musicVolume;
    }


    public static String getCurrentMusicPath() {
        return currentMusicPath;
    }

    public static void setCurrentMusicPath(String currentMusicPath) {
        App.currentMusicPath = currentMusicPath;
    }

    public static void playMusic(String musicPath) {
        if (currentMusic != null) {
            currentMusic.stop();
        }
        currentMusicPath = musicPath;
        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(musicPath));
        currentMusic.setLooping(true);
        currentMusic.setVolume(musicVolume);
        currentMusic.play();
    }

    public static HeroesType getHero() {
        return hero;
    }

    public static void setHero(HeroesType hero) {
        App.hero = hero;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }

    public static int getPauseMenu() {
        return pauseMenu;
    }

    public static float getDistance(float x, float y) {
        Player player = App.getCurrentGame().getPlayer();
        float deltax = x - player.getX();
        float deltay = y - player.getY();
        if (deltay < 0) {
            deltay *= -1;
        }
        if (deltax < 0) {
            deltax *= -1;
        }
        return deltax + deltay;
    }

    public static void setUsers(ArrayList<User> newUsers) {
        users = newUsers;
    }
}
