package com.tilldawn.controller.MenuControllers;

import com.tilldawn.Main;
import com.tilldawn.database.DatabaseHelper;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.User;
import com.tilldawn.models.enums.Language;
import com.tilldawn.views.Menus.MainMenuView;
import com.tilldawn.views.Menus.ScoreBoardMenuView;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreBoardMenuController {
    private ScoreBoardMenuView view;
    private String mode = Language.Score.getLanguage();
    public void setView(ScoreBoardMenuView view) {
        DatabaseHelper.getAllUsers();
        this.view = view;
    }

    public void handleButtons() {
        if (view != null) {

            String selected = view.getOrderingBox().getSelected();

            if (!selected.equals(mode)) {
                if (selected.equals(Language.Score.getLanguage())) {
                    view.setUsers(orderUsersByTime());
                    makeRowLabels(orderUsersByScore());
                } else if (selected.equals(Language.Kill.getLanguage())) {
                    view.setUsers(orderUsersByKill());
                    makeRowLabels(orderUsersByKill());
                } else if (selected.equals(Language.UserName.getLanguage())) {
                    view.setUsers(orderUsersByUsername());
                    makeRowLabels(orderUsersByUsername());
                } else if (selected.equals(Language.Time.getLanguage())) {
                    view.setUsers(orderUsersByTime());
                    makeRowLabels(orderUsersByTime());
                }
                mode = selected;
            }
        }
    }

    public void makeRowLabels(ArrayList<User> users) {

        for(int i = 0; i < 10; i++) {
            if(i < users.size()){
                StringBuilder message = new StringBuilder();
                message.append(i+1).append(".");
                message.append(users.get(i).getUsername()).append(" - ");
                message.append(users.get(i).getScore()).append(" - ");
                message.append(users.get(i).getKills()).append(" - ");
                float time = users.get(i).getTimeAlive();
                int minutes = (int) (time / 60);
                int seconds = (int) (time % 60);
                String formattedTime = String.format("%02d:%02d", minutes, seconds);
                message.append(formattedTime);
                view.setRowLabels(i, message.toString());
            }
        }
    }

    public void handleBackButton() {
        navigateMainMenu();
    }

    private void navigateMainMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }

    public ArrayList<User> orderUsersByUsername() {
        ArrayList<User> ordered = new ArrayList<>(App.getUsers());
        ordered.sort(Comparator.comparing((User u) -> u.getUsername().toLowerCase()));
        return ordered;
    }

    public ArrayList<User> orderUsersByKill() {
        ArrayList<User> ordered = new ArrayList<>(App.getUsers());
        ordered.sort(Comparator.comparingInt(User::getKills).reversed());
        return ordered;
    }

    public ArrayList<User> orderUsersByScore() {
        ArrayList<User> ordered = new ArrayList<>(App.getUsers());
        ordered.sort(Comparator.comparingInt(User::getScore).reversed());
        return ordered;
    }

    public ArrayList<User> orderUsersByTime() {
        ArrayList<User> ordered = new ArrayList<>(App.getUsers());
        ordered.sort(Comparator.comparingDouble(User::getTimeAlive).reversed());
        return ordered;
    }
}
