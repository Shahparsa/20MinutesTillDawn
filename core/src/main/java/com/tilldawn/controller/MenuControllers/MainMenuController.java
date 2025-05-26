package com.tilldawn.controller.MenuControllers;

import com.tilldawn.Main;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.views.Menus.*;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleSettingsButton() {
        navigateSettingMenu();
    }

    public void handleProfileButton() {
        navigateProfileMenu();
    }

    public void handlePreGameButton() {
        navigatePreGameMenu();
    }

    public void handleScoreBoardButton() {
        navigateScoreBoardMenu();
    }

    public void handleHintButton() {
        navigateHintMenu();
    }

    public void handleLoadGameButton() {

    }

    public void handleExitButton() {
        navigateGameMenu();
    }

    private void navigateSettingMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new SettingMenuView(new SettingMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }

    private void navigateProfileMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(),
            GameAssetManager.getInstance().getSkin()));

    }

    private void navigatePreGameMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new HeroSelectView(new HeroSelectController(),
            GameAssetManager.getInstance().getSkin()));

    }

    private void navigateScoreBoardMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ScoreBoardMenuView(new ScoreBoardMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }

    private void navigateHintMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new HintMenuView(new HintMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }

    private void navigateLoadGameMenu() {

    }

    private void navigateGameMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new StartMenuView(new StartMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }
}
