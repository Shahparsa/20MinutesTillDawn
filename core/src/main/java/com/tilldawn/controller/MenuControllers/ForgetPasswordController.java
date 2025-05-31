package com.tilldawn.controller.MenuControllers;

import com.tilldawn.Main;
import com.tilldawn.database.DatabaseHelper;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.User;
import com.tilldawn.models.enums.Language;
import com.tilldawn.models.enums.SecurityQuestionType;
import com.tilldawn.views.Menus.ForgetPasswordMenuView;
import com.tilldawn.views.Menus.StartMenuView;
import com.tilldawn.views.Menus.MainMenuView;

public class ForgetPasswordController {
    private ForgetPasswordMenuView view;

    public void setView(ForgetPasswordMenuView view) {
        this.view = view;
    }

    public void handleBackButtonAction() {
        navigateToGameMenu();
    }

    public void handleChangePasswordAction() {
        String username = view.getUsernameField().getText();
        String newPassword = view.getNewPasswordField().getText();
        String securityQuestion = view.getSecurityQuestion().getSelected();
        String answer = view.getAnswerField().getText();
        User user = findUserWithUsername(username);
        if(user == null) {
            view.setErrorMessage(Language.UserNotFound.getLanguage());
            return;
        }

        SecurityQuestionType questionType = SecurityQuestionType.getSecurityFromQuestion(securityQuestion);
        if(!user.getSecurityQuestion().getType().equals(questionType)) {
            view.setErrorMessage(Language.IncorrectSecurityQuestion.getLanguage());
            return;
        }

        if(!user.getSecurityQuestion().getAnswer().equals(answer)) {
            view.setErrorMessage("Incorrect answer to security question");
            return;
        }

        DatabaseHelper.changePassword(username, newPassword);
        user.setPassword(newPassword);
        App.setCurrentUser(user);
        navigateToMainMenu();
    }

    private void navigateToGameMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new StartMenuView(new StartMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }

    private void navigateToMainMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }

    private User findUserWithUsername(String username) {
        User user = DatabaseHelper.getUserByUsername(username);
        return user;
    }

}
