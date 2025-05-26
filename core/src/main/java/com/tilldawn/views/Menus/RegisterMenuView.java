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
import com.tilldawn.controller.MenuControllers.RegisterMenuController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Language;
import com.tilldawn.models.enums.SecurityQuestionType;


public class RegisterMenuView implements Screen {
    private final RegisterMenuController controller;
    private Stage stage;
    private Table table;
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextButton registerButton;
    private final TextButton backButton;
    private final Label gameTitle;
    private final Label errorMessage;
    private final TextButton skipButton;
    private final SelectBox<String> securityQuestion;
    private final TextField answerField;

    public RegisterMenuView(RegisterMenuController controller , Skin skin) {
        this.controller = controller;
        this.registerButton = new TextButton(Language.Register.getLanguage(), skin);
        this.usernameField = new TextField("", skin);
        this.usernameField.setMessageText(Language.UserName.getLanguage());
        this.passwordField = new TextField("", skin);
        this.passwordField.setMessageText(Language.Password.getLanguage());
        this.backButton = new TextButton(Language.Back.getLanguage(), skin);
        this.skipButton = new TextButton(Language.Skip.getLanguage(), skin);
        this.gameTitle = new Label(Language.RegisterMenu.getLanguage(), skin);
        gameTitle.setFontScale(2.5f);
        gameTitle.setColor(Color.RED);
        this.errorMessage = new Label("", skin);
        errorMessage.setColor(Color.RED);
        this.securityQuestion = new SelectBox<>(skin);
        securityQuestion.setItems(SecurityQuestionType.Q1.getQuestion(),
            SecurityQuestionType.Q2.getQuestion(),
            SecurityQuestionType.Q3.getQuestion());
        this.answerField = new TextField("", skin);
        this.answerField.setMessageText(Language.Answer.getLanguage());
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
        table.add(securityQuestion).width(700).padBottom(20);
        table.row();
        table.add(answerField).width(300).padBottom(20);
        table.row();
        table.add(registerButton).width(300).padBottom(15);
        table.row();

        Table buttonTable = new Table();
        buttonTable.add(skipButton).width(150).padRight(10);
        buttonTable.add(backButton).width(150).padLeft(10);

        table.add(buttonTable).padBottom(20);
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

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() { return passwordField; }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextButton getSkipButton() {
        return skipButton;
    }

    public void setErrorMessage(String message) {
        this.errorMessage.setText(message);
    }

    public SelectBox<String> getSecurityQuestion() {
        return securityQuestion;
    }

    public TextField getAnswerField() {
        return answerField;
    }

    public void addListeners(){
        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleRegisterAction();
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleBackAction();
            }
        });

        skipButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleSkipAction();
            }
        });
    }
}
