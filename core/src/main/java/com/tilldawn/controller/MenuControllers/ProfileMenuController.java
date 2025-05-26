package com.tilldawn.controller.MenuControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.User;
import com.tilldawn.models.enums.AvatarType;
import com.tilldawn.models.enums.Language;
import com.tilldawn.views.Menus.MainMenuView;
import com.tilldawn.views.Menus.ProfileMenuView;
import com.tilldawn.views.Menus.StartMenuView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ProfileMenuController {
    private ProfileMenuView view;

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void handleDeleteButton() {
        User user = App.getCurrentUser();
        App.getUsers().remove(user);
        App.setCurrentUser(null);
        navigateToStartMenu();
    }

    public void handleAvatarChangeButton(){
        String newAvatarName = view.getImageSelectBox().getSelected();
        Texture newAvatarTexture = new Texture(AvatarType.getAvatarByName(newAvatarName).getTexturePath());
        String newAvatarPath = AvatarType.getAvatarByName(newAvatarName).getTexturePath();
        String currentAvatarPath = App.getCurrentUser().getAvatarPath();

        App.getCurrentUser().setAvatar(newAvatarTexture);
        App.getCurrentUser().setAvatarPath(newAvatarPath);
        view.setCurrentAvatar(newAvatarTexture);
        if (!currentAvatarPath.equals(newAvatarPath)) {
            App.getCurrentUser().setAvatar(newAvatarTexture);
            App.getCurrentUser().setAvatarPath(newAvatarPath);
            view.setCurrentAvatar(newAvatarTexture);
        } else {
            view.setErrorLabel("");
        }
    }

    public void handleBackButton() {
        navigateMainMenu();
    }

    public void handleChangeButton() {
        String newName = view.getUsernameField().getText();
        String newPassword = view.getPasswordField().getText();
        if (newName.isEmpty() && newPassword.isEmpty()) {
            view.setErrorLabel(Language.NoChangesApplied.getLanguage());
            return;
        }
        if (!newName.isEmpty()) {
            if (!isUsernameUnique(newName)) {
                view.setErrorLabel(Language.UsernameIsAlreadyTaken.getLanguage());
                return;
            } else {
                App.getCurrentUser().setUsername(newName);
            }
        }
        if (!newPassword.isEmpty()) {
            if (!isPasswordValid(newPassword)) {
                view.setErrorLabel(Language.PasswordIsInvalid.getLanguage());
                return;
            } else {
                App.getCurrentUser().setPassword(newPassword);
            }
        }
        view.setErrorLabel(Language.ChangesApplied.getLanguage());
    }

    public void handleUploadButton() {
        openDesktopFileChooser();
    }

    private void openDesktopFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select your avatar");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG, JPG", "png", "jpg"));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            File selectedFile = fileChooser.getSelectedFile();
            handleSelectedFile(new FileHandle(selectedFile) , filePath);
        }
    }

    private void handleSelectedFile(FileHandle file , String filePath) {
        try {
            Texture newAvatar = new Texture(file);
            App.getCurrentUser().setAvatar(newAvatar);
            App.getCurrentUser().setAvatarPath(filePath);
            view.setCurrentAvatar(new Texture(filePath));
            view.setErrorLabel("");
        } catch (Exception e) {
            view.setErrorLabel(Language.UploadFailed.getLanguage());
        }
    }

    private void navigateMainMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }

    private boolean isUsernameUnique(String username) {
        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
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

    private void navigateToStartMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new StartMenuView(new StartMenuController(), GameAssetManager.getInstance().getSkin()));
    }
}
