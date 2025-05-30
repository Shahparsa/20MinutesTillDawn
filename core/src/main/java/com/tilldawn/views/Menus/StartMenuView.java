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
import com.tilldawn.controller.MenuControllers.StartMenuController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Language;

public class StartMenuView implements Screen {
    private final StartMenuController controller;
    private Stage stage;
    private Table table;
    private final TextButton languageButton;
    private final TextButton loginButton;
    private final TextButton registerButton;
    private final TextButton exitButton;
    private final Label gameTitle;


    public StartMenuView(StartMenuController controller, Skin skin) {
        this.controller = controller;
        this.registerButton = new TextButton(Language.Register.getLanguage(), skin);
        this.loginButton = new TextButton(Language.Login.getLanguage(), skin);
        this.exitButton = new TextButton(Language.Exit.getLanguage(), skin);
        this.gameTitle = new Label(Language.GameMenu.getLanguage(), skin);
        this.languageButton = new TextButton(Language.Language.getLanguage() + ":" + App.getLanguage(), skin);
        gameTitle.setColor(Color.RED);
        gameTitle.setFontScale(2.5f);
        this.table = new Table();
        makeListener();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = GameAssetManager.getInstance().tableMenu(table, stage);


        table.setFillParent(true);
        table.center();
        table.add(gameTitle).padBottom(20);
        table.row();
        table.add(loginButton).padBottom(20);
        table.row();
        table.add(registerButton).padBottom(20);
        table.row();
        table.add(languageButton).padBottom(20);
        table.row();
        table.add(exitButton).padBottom(20);
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

    public TextButton getExitButton() {
        return exitButton;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getLanguageButton() {
        return languageButton;
    }

    private void makeListener() {
        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.isSFX()) {
                    GameAssetManager.getInstance().getButtonSFX().play(1.0f);
                }
                controller.handleRegisterAction();
            }
        });

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.isSFX()) {
                    GameAssetManager.getInstance().getButtonSFX().play(1.0f);
                }
                controller.handleLoginAction();
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.isSFX()) {
                    GameAssetManager.getInstance().getButtonSFX().play(1.0f);
                }
                controller.handleExitAction();
            }
        });

        languageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.isSFX()) {
                    GameAssetManager.getInstance().getButtonSFX().play(1.0f);
                }
                controller.handleLanguageAction();
            }
        });

    }
}
