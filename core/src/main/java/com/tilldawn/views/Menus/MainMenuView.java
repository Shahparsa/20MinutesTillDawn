package com.tilldawn.views.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.MenuControllers.MainMenuController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.SecurityQuestion;
import com.tilldawn.models.User;
import com.tilldawn.models.enums.Language;
import com.tilldawn.models.enums.SecurityQuestionType;


public class MainMenuView implements Screen {
    private final MainMenuController controller;
    private Stage stage;
    private Table table;
    private final Label errorMessage;
    private final TextButton settingsButton;
    private final TextButton profileButton;
    private final TextButton preGameButton;
    private final TextButton scoreBoardButton;
    private final TextButton hintButton;
    private final TextButton loadGameButton;
    private final TextButton exitButton;
    private final Image avatarLabel;
    private final Label usernameLabel;
    private final Label scoreLabel;
    private final Label gameTitle;
    private final Image logoImage;

    static {
        App.addUsers(new User("Ali", "Ali123@",
            new SecurityQuestion(SecurityQuestionType.Q1, "y"), null, null, 120, 10,
            60));
        App.addUsers(new User("Abbas", "Ali123@",
            new SecurityQuestion(SecurityQuestionType.Q1, "y"), null, null, 190, 5,
            90));
    }

    public MainMenuView(MainMenuController controller , Skin skin) {
        this.controller = controller;
        settingsButton = new TextButton(Language.Setting.getLanguage(), skin);
        profileButton = new TextButton(Language.Profile.getLanguage(), skin);
        preGameButton = new TextButton(Language.PreGame.getLanguage(), skin);
        scoreBoardButton = new TextButton(Language.ScoreBoard.getLanguage(), skin);
        hintButton = new TextButton(Language.Hint.getLanguage(), skin);
        loadGameButton = new TextButton(Language.loadGame.getLanguage(), skin);
        exitButton = new TextButton(Language.Exit.getLanguage(), skin);
        errorMessage = new Label("", skin);
        avatarLabel =  new Image(App.getCurrentUser().getAvatar());
        avatarLabel.setSize(avatarLabel.getWidth(), avatarLabel.getHeight());
        usernameLabel = new Label(App.getCurrentUser().getUsername(), skin);
        scoreLabel = new Label(App.getCurrentUser().getScoreString(), skin);
        logoImage = GameAssetManager.getInstance().Logo();
        gameTitle = new Label(Language.MainMenu.getLanguage(), skin);
        addListener();
        this.table = new Table();
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = GameAssetManager.getInstance().tableMenu(table , stage);

        table.setFillParent(true);
        table.center();
        Label fake = new Label("" , gameTitle.getStyle());
        table.add(fake).padBottom(20);
        table.row();
        table.add(errorMessage).padBottom(20);
        table.row();
        Table table1 = new Table();
        table1.add(preGameButton).width(300).padLeft(20);
        table1.add(loadGameButton).width(300).padRight(20);
        table.add(table1);
        table.row();
        Table table2 = new Table();
        table2.add(settingsButton).width(300).padLeft(20);
        table2.add(profileButton).width(300).padRight(20);
        table.add(table2);
        table.row();
        Table table3 = new Table();
        table3.add(scoreBoardButton).width(300).padBottom(20);
        table3.add(hintButton).width(300).padBottom(20);
        table.add(table3);
        table.row();
        table.add(exitButton).width(300).padBottom(20);

        Table tableRight = new Table();
        tableRight.defaults().width(200).padBottom(15);
        tableRight.add(avatarLabel).size(250, 250);
        tableRight.row();
        tableRight.add(usernameLabel);
        tableRight.row();
        tableRight.add(scoreLabel);
        tableRight.row();

        tableRight.setPosition(
            stage.getWidth() - 250,
            stage.getHeight() - 200
        );

        logoImage.setSize(logoImage.getWidth() * 2, logoImage.getHeight() * 2);

        logoImage.setPosition(
            stage.getWidth()/2 - logoImage.getWidth()/2,
            stage.getHeight() - 290
        );


        stage.addActor(table);
        stage.addActor(tableRight);
        stage.addActor(logoImage);
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
        stage.dispose();
    }

    public Label getErrorMessage() {
        return errorMessage;
    }

    public TextButton getSettingsButton() {
        return settingsButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getPreGameButton() {
        return preGameButton;
    }

    public TextButton getScoreBoardButton() {
        return scoreBoardButton;
    }

    public TextButton getHintButton() {
        return hintButton;
    }

    public TextButton getLoadGameButton() {
        return loadGameButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    private void addListener() {
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleExitButton();
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleSettingsButton();
            }
        });

        profileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleProfileButton();
            }
        });

        preGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handlePreGameButton();
            }
        });

        scoreBoardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleScoreBoardButton();
            }
        });

        hintButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleHintButton();
            }
        });

        loadGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleLoadGameButton();
            }
        });


    }
}
