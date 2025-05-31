package com.tilldawn.controller.MenuControllers;

import com.tilldawn.Main;
import com.tilldawn.database.DatabaseHelper;
import com.tilldawn.models.*;
import com.tilldawn.models.enums.Language;
import com.tilldawn.models.enums.SecurityQuestionType;
import com.tilldawn.views.Menus.StartMenuView;
import com.tilldawn.views.Menus.MainMenuView;
import com.tilldawn.views.Menus.RegisterMenuView;

public class RegisterMenuController {
    private RegisterMenuView view;

    public void setView(RegisterMenuView view) {
        this.view = view;
    }


    public void handleRegisterAction() {
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();
        String securityQuestion = view.getSecurityQuestion().getSelected();
        String answer = view.getAnswerField().getText();

        if (!isUsernameUnique(username)) {
            view.setErrorMessage(Language.UsernameIsAlreadyTaken.getLanguage());
            return;
        }

        if (!isPasswordValid(password)) {
            view.setErrorMessage(Language.PasswordIsInvalid.getLanguage());
            return;
        }

        if (answer.isEmpty()) {
            view.setErrorMessage(Language.NoAnswer.getLanguage());
            return;
        }

        SecurityQuestionType questionType = SecurityQuestionType.getSecurityFromQuestion(securityQuestion);
        SecurityQuestion securityQuestion1 = new SecurityQuestion(questionType, answer);
        User user = new User(username, password, securityQuestion1);
        DatabaseHelper.registerUser(user);
        App.setCurrentUser(user);
        navigateToMainMenu();
    }

    public void handleSkipAction() {
        User user = new User("Guest", "Guest",
            new SecurityQuestion(SecurityQuestionType.Q1, "?"));
        App.setCurrentUser(user);
        navigateToMainMenu();
    }

    public void handleBackAction() {
        navigateToGameMenu();
    }

    private void navigateToMainMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }

    private void navigateToGameMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new StartMenuView(new StartMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }

    private boolean isUsernameUnique(String username) {
        User user = DatabaseHelper.getUserByUsername(username);
        if (user == null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false;
        }
        if (!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[_()*&%$#@])[A-Za-z0-9_()*&%$#@]+")) {
            return false;
        }
        return true;
    }
}
