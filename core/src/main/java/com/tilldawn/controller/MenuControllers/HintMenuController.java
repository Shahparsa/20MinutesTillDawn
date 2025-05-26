package com.tilldawn.controller.MenuControllers;

import com.tilldawn.Main;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.views.Menus.HintMenuView;
import com.tilldawn.views.Menus.MainMenuView;

public class HintMenuController {
    private HintMenuView view;

    public void setView(HintMenuView view) {
        this.view = view;
    }


    public void navigateMainMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }
}
