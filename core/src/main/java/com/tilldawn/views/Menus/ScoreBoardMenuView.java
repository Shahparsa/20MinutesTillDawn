package com.tilldawn.views.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Main;
import com.tilldawn.controller.MenuControllers.ScoreBoardMenuController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.User;
import com.tilldawn.models.enums.Language;

import java.util.ArrayList;


public class ScoreBoardMenuView implements Screen {
    private final ScoreBoardMenuController controller;
    private Stage stage;
    private Table table;
    private final Label[] rowLabels;
    private final Label titleLabel;
    private final SelectBox<String> orderingBox;
    private ArrayList<User> users;
    private final Label orderingLabel;
    private final TextButton backButton;

    public ScoreBoardMenuView(ScoreBoardMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        this.table = new Table();
        this.titleLabel = new Label(Language.ScoreBoardMenu.getLanguage(), skin);
        titleLabel.setColor(Color.RED);
        titleLabel.setFontScale(2.5f);
        this.users = controller.orderUsersByScore();
        this.orderingBox = new SelectBox<String>(skin);
        orderingBox.setItems(Language.Score.getLanguage(),
            Language.Kill.getLanguage(),
            Language.UserName.getLanguage(),
            Language.Time.getLanguage());
        this.rowLabels = new Label[10];
        for (int i = 0; i < 10; i++) {
            rowLabels[i] = new Label(i + 1 + ".", skin);
            if (i < 3) {
                rowLabels[i].setColor(Color.GOLD);
            }
            if (i < users.size()) {
                StringBuilder message = new StringBuilder();
                message.append(i + 1).append(".");
                message.append(users.get(i).getUsername()).append(" - ");
                message.append(users.get(i).getScore()).append(" - ");
                message.append(users.get(i).getKills()).append(" - ");
                float time = users.get(i).getTimeAlive();
                int minutes = (int) (time / 60);
                int seconds = (int) (time % 60);
                String formattedTime = String.format("%02d:%02d", minutes, seconds);
                message.append(formattedTime);
                rowLabels[i].setText(message.toString());
                if (users.get(i).getUsername().equals(App.getCurrentUser().getUsername())) {
                    rowLabels[i].setColor(Color.GREEN);
                }
            }
        }
        this.orderingLabel = new Label(Language.OrderingBy.getLanguage(), skin);
        this.backButton = new TextButton(Language.Back.getLanguage(), skin);
        addListeners();
    }

    @Override
    public void show() {
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = GameAssetManager.getInstance().tableMenu(table, stage);

        table.setFillParent(true);
        table.center();
        table.add(titleLabel);
        table.row();

        Table orderingTable = new Table();
        orderingTable.add(orderingLabel);
        orderingTable.add(orderingBox);
        orderingTable.row();
        table.add(orderingTable);
        table.row();
        for (int i = 0; i < 10; i++) {
            table.add(rowLabels[i]);
            table.row();
        }
        table.add(backButton);
        table.row();
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.getBatch().setShader(Main.getBatch().getShader());
        stage.draw();
        controller.handleButtons();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public SelectBox<String> getOrderingBox() {
        return orderingBox;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> newUsers) {
        users = newUsers;
    }

    public Label[] getRowLabels() {
        return rowLabels;
    }

    public void setRowLabels(int i, String message) {
        if (i < 3) {
            rowLabels[i].setColor(Color.GOLD);
        } else {
            rowLabels[i].setColor(Color.WHITE);
        }
        if (users.get(i).getUsername().equals(App.getCurrentUser().getUsername())) {
            rowLabels[i].setColor(Color.GREEN);
        }
        rowLabels[i].setText(message);
    }

    public void addListeners() {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.isSFX()) {
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleBackButton();
            }
        });
    }
}
