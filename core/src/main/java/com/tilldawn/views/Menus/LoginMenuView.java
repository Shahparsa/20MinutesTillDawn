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
import com.tilldawn.controller.MenuControllers.LoginMenuController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Language;


public class LoginMenuView implements Screen {

    private final LoginMenuController controller;
    private Stage stage;
    private Table table;
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextButton loginButton;
    private final TextButton forgetPasswordButton;
    private final TextButton backButton;
    private final Label gameTitle;
    private Label errorMessage;

    public LoginMenuView(LoginMenuController controller , Skin skin) {
        this.controller = controller;
        this.usernameField = new TextField("", skin);
        usernameField.setMessageText(Language.UserName.getLanguage());
        this.passwordField = new TextField("", skin);
        passwordField.setMessageText(Language.Password.getLanguage());
        this.forgetPasswordButton = new TextButton(Language.Forget.getLanguage(), skin);
        this.loginButton = new TextButton(Language.Login.getLanguage(), skin);
        this.backButton = new TextButton(Language.Back.getLanguage(), skin);
        this.gameTitle = new Label(Language.LoginMenu.getLanguage(), skin);
        gameTitle.setFontScale(2.5f);
        gameTitle.setColor(Color.RED);
        this.errorMessage = new Label("", skin);
        errorMessage.setColor(Color.RED);
        this.table = new Table();
        addListeners();
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = GameAssetManager.getInstance().tableMenu(table , stage);

        table.setFillParent(true);
        table.center();
        table.add(gameTitle).padBottom(20);
        table.row();
        table.add(errorMessage).padBottom(20);
        table.row();
        table.add(usernameField).width(300).padBottom(20);
        table.row();
        table.add(passwordField).width(300).padBottom(20);
        table.row();
        table.add(forgetPasswordButton).width(300).padBottom(20);
        table.row();

        Table buttonTable = new Table();
        buttonTable.add(loginButton).width(150).padRight(10);
        buttonTable.add(backButton).width(150).padLeft(10);

        table.add(buttonTable).padBottom(20).row();

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.getBatch().setShader(Main.getBatch().getShader());
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getForgetPasswordButton() {
        return forgetPasswordButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public void setErrorMessage(String message) {
        this.errorMessage.setText(message);
    }

    public void addListeners() {
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleLoginAction();
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleBackButtonAction();
            }
        });

        forgetPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleForgetPasswordButtonAction();
            }
        });
    }
}
