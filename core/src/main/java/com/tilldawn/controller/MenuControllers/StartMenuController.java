package com.tilldawn.controller.MenuControllers;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.views.Menus.StartMenuView;
import com.tilldawn.views.Menus.LoginMenuView;
import com.tilldawn.views.Menus.RegisterMenuView;

public class StartMenuController {
    private StartMenuView view;

    public void setView(StartMenuView view) {
        this.view = view;
    }

    public void handleButtons(){
        view.getRegisterButton().setChecked(false);
        view.getLoginButton().setChecked(false);
        view.getExitButton().setChecked(false);
        view.getLanguageButton().setChecked(false);

        if(view != null){
            if(view.getRegisterButton().isPressed()){
                handleRegisterAction();
            }else if(view.getLoginButton().isPressed()){
                handleLoginAction();
            }else if(view.getExitButton().isPressed()){
                handleExitAction();
            }else if(view.getLanguageButton().isPressed()){
                handleLanguageAction();
            }
        }
    }

    public void handleRegisterAction() {
        navigateToRegisterMenu();
    }

    public void handleLoginAction() {
        navigateToLoginMenu();
    }

    public void handleLanguageAction() {
        App.changeLanguage();
        navigateToGameMenu();
    }

    public void navigateToGameMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new StartMenuView(new StartMenuController() , GameAssetManager.getInstance().getSkin()));
    }
    public void navigateToLoginMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getInstance().getSkin()));
    }

    public void navigateToRegisterMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getInstance().getSkin()));
    }

    public void handleExitAction() {
        Gdx.app.exit();
    }
}
