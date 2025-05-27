package com.tilldawn.controller.MenuControllers;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Main;
import com.tilldawn.controller.GameController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Abilities;
import com.tilldawn.views.GameView;
import com.tilldawn.views.Menus.EndMenuView;
import com.tilldawn.views.Menus.MainMenuView;
import com.tilldawn.views.Menus.PauseMenuView;
import com.tilldawn.views.Menus.PreGameMenuView;

public class PauseMenuController {
    private PauseMenuView view;
    private GameView gameView;

    public void setView(PauseMenuView view , GameView gameView) {
        this.gameView = gameView;
        this.view = view;
    }

    public void handleResumeButton(){
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(gameView);
    }

    public void handleSaveAndExitButton(){
        //TODO : Save
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController()
            , GameAssetManager.getInstance().getSkin()));
    }

    public void handleGiveUpButton(boolean mode){
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new EndMenuView(mode , gameView
            , GameAssetManager.getInstance().getSkin()));
    }

    public void handleBlackAndWhiteButton(){
        App.changeBlackWhite();
    }
}
