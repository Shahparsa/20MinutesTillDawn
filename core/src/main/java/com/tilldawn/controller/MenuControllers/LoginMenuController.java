package com.tilldawn.controller.MenuControllers;

import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.User;
import com.tilldawn.models.enums.Language;
import com.tilldawn.views.Menus.ForgetPasswordMenuView;
import com.tilldawn.views.Menus.StartMenuView;
import com.tilldawn.views.Menus.LoginMenuView;
import com.tilldawn.views.Menus.MainMenuView;


public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public void handleLoginAction() {
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();
        User user = findUserWithUsername(username);
        if(user == null) {
            view.setErrorMessage(Language.UserNotFound.getLanguage());
            return;
        }
        if(!user.getPassword().equals(password)) {
            view.setErrorMessage(Language.IncorrectPassword.getLanguage());
            return;
        }
        App.setCurrentUser(user);
        navigateToMainMenu();
    }

    public void handleBackButtonAction() {
        navigateToStartMenu();
    }

    public void handleForgetPasswordButtonAction() {
        navigateToForgetPasswordMenu();
    }

    private void navigateToMainMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getInstance().getSkin()));
    }

    private void navigateToStartMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new StartMenuView(new StartMenuController(), GameAssetManager.getInstance().getSkin()));
    }

    private void navigateToForgetPasswordMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ForgetPasswordMenuView(new ForgetPasswordController(), GameAssetManager.getInstance().getSkin()));
    }

    private User findUserWithUsername(String username) {
        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
