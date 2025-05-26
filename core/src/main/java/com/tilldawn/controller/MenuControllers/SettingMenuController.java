package com.tilldawn.controller.MenuControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Musics;
import com.tilldawn.views.Menus.MainMenuView;
import com.tilldawn.views.Menus.SettingMenuView;

public class SettingMenuController {

    private SettingMenuView view;

    public void setView(SettingMenuView view) {
        this.view = view;
    }

    private String currentRebindingAction = null;

    public void handleButtons() {
        if (view != null) {
            // Handle music Volume
            float newVolume = view.getMusicSlider().getValue();
            if (newVolume != App.getMusicVolume()) {
                App.setMusicVolume(newVolume);
            }

            // Handle music selection
            String selectedMusicName = view.getMusicBox().getSelected();
            String selectedMusicPath = Musics.getMusicByName(selectedMusicName).getMusicPath();
            if (!selectedMusicPath.equals(App.getCurrentMusicPath())) {
                App.playMusic(selectedMusicPath);
            }

            // Handle Music Volume
            float newMusicVolume = view.getMusicSlider().getValue();
            if (newMusicVolume != App.getMusicVolume()) {
                App.setMusicVolume(newMusicVolume);
            }
            // Handle SFX toggle
            if (view.getSfxCheckbox().isChecked() != App.isSFX()) {
                App.changeSFX();
            }

            // Handle Black&White toggle
            if (view.getBlackWhiteCheckbox().isChecked() != App.isBlackWhite()) {
                App.changeBlackWhite();
            }

            // Handle AutoReload toggle
            if (view.getAutoReloadCheckbox().isChecked() != App.isAutoReload()) {
                App.changeAutoReload();
            }


            if (currentRebindingAction != null) {
                checkForKeyPress();
            }
        }
    }

    public void handleUpButton() {
        view.getUpButton().setText("?");
        startRebinding("UP");
    }

    public void handleDownButton(){
        view.getDownButton().setText("?");
        startRebinding("DOWN");
    }

    public void handleLeftButton() {
        view.getLeftButton().setText("?");
        startRebinding("LEFT");
    }

    public void handleRightButton() {
        view.getRightButton().setText("?");
        startRebinding("RIGHT");
    }

    public void handleReloadButton() {
        view.getReloadButton().setText("?");
        startRebinding("RELOAD");
    }

    public void handleShootButton(){
        view.getShootButton().setText("?");
        startRebinding("SHOOT");
    }

    public void handleBackButton() {
        navigateMainMenu();
    }

    private void navigateMainMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }


    private void startRebinding(String action) {
        currentRebindingAction = action;
    }

    private void checkForKeyPress() {
        // Check all possible keys
        for (int i = 0; i < 256; i++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                if(!checkDuplicateRebinding(i)){
                    return;
                }
                finishRebinding(i);
                return;
            }
        }
        // Check mouse keys
        for (int button : new int[] {Input.Buttons.LEFT, Input.Buttons.RIGHT, Input.Buttons.MIDDLE}) {
            if (Gdx.input.isButtonJustPressed(button)) {
                if (!checkDuplicateRebinding(button)) {
                    return;
                }
                int virtualKeyCode = button + 1000;
                finishRebinding(virtualKeyCode);
                return;
            }
        }
    }

    private void finishRebinding(int keyCode) {
        App.setKey(currentRebindingAction, keyCode);

        String keyName = getInputName(keyCode);
        if(currentRebindingAction.equals("UP")) {
            view.getUpButton().setText(keyName);
        }else if(currentRebindingAction.equals("DOWN")) {
            view.getDownButton().setText(keyName);
        }else if(currentRebindingAction.equals("LEFT")) {
            view.getLeftButton().setText(keyName);
        }else if(currentRebindingAction.equals("RIGHT")) {
            view.getRightButton().setText(keyName);
        }else if(currentRebindingAction.equals("RELOAD")) {
            view.getReloadButton().setText(keyName);
        } else if(currentRebindingAction.equals("SHOOT")) {
            view.getShootButton().setText(keyName);
        }
        currentRebindingAction = null;
    }

    private boolean checkDuplicateRebinding(int keyCode) {
        if(!currentRebindingAction.equals("UP")) {
            if(App.getUp() == keyCode){
                return false;
            }
        }
        if(!currentRebindingAction.equals("DOWN")) {
            if(App.getDown() == keyCode){
                return false;
            }
        }
        if(!currentRebindingAction.equals("LEFT")) {
            if(App.getLeft() == keyCode){
                return false;
            }
        }
        if(!currentRebindingAction.equals("RIGHT")) {
            if(App.getRight() == keyCode){
                return false;
            }
        }
        if(!currentRebindingAction.equals("RELOAD")) {
            if(App.getReload() == keyCode){
                return false;
            }
        }
        if(!currentRebindingAction.equals("SHOOT")){
            if(App.getShoot() == keyCode){
                return false;
            }
        }
        if(keyCode == App.getCheatTime()
            || keyCode == App.getCheatAddDamage()
            || keyCode == App.getCheatAddHealth()
            || keyCode == App.getCheatAddLevel()
            || keyCode == App.getCheatSpawnBoss()){
            return false;
        }
        return true;
    }

    private String getInputName(int code) {
        if (code >= 1000) {
            // Mouse key
            int mouseButton = code - 1000;
            switch (mouseButton) {
                case Input.Buttons.LEFT: return "MOUSE LEFT";
                case Input.Buttons.RIGHT: return "MOUSE RIGHT";
                case Input.Buttons.MIDDLE: return "MOUSE MIDDLE";
                default: return "MOUSE " + mouseButton;
            }
        } else {
            // Keyboard
            return Input.Keys.toString(code);
        }
    }
}
