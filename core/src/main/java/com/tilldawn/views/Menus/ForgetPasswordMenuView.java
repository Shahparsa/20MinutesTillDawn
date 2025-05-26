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
import com.tilldawn.controller.MenuControllers.ForgetPasswordController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Language;
import com.tilldawn.models.enums.SecurityQuestionType;

public class ForgetPasswordMenuView implements Screen {
    private final ForgetPasswordController controller;
    private Stage stage;
    private Table table;
    private final TextField usernameField;
    private final TextField newPasswordField;
    private final TextButton changePasswordButton;
    private final TextButton backButton;
    private final Label gameTitle;
    private Label errorMessage;
    private final SelectBox<String> securityQuestion;
    private final TextField answerField;

    public ForgetPasswordMenuView(ForgetPasswordController controller , Skin skin) {
        this.controller = controller;
        this.changePasswordButton = new TextButton(Language.Change.getLanguage(), skin);
        this.backButton = new TextButton(Language.Back.getLanguage(), skin);
        this.newPasswordField = new TextField("", skin);
        this.newPasswordField.setMessageText(Language.NewPassword.getLanguage());
        this.usernameField = new TextField("", skin);
        this.usernameField.setMessageText(Language.UserName.getLanguage());
        this.gameTitle = new Label(Language.ForgetMenu.getLanguage(), skin);
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
        addListener();
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = GameAssetManager.getInstance().tableMenu(table , stage);
        table.setFillParent(true);
        table.center();;
        table.add(gameTitle).padBottom(20);
        table.row();
        table.add(errorMessage).padBottom(20);
        table.row();
        table.add(usernameField).width(300).padBottom(20);
        table.row();
        table.add(newPasswordField).width(300).padBottom(20);
        table.row();
        table.add(securityQuestion).padBottom(20);
        table.row();
        table.add(answerField).width(300).padBottom(20);
        table.row();
        table.add(changePasswordButton).padBottom(20);
        table.row();
        table.add(backButton).padBottom(20);
        table.row();

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

    public TextField getNewPasswordField() {
        return newPasswordField;
    }

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public SelectBox<String> getSecurityQuestion() {
        return securityQuestion;
    }

    public TextField getAnswerField() {
        return answerField;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setErrorMessage(String message) {
        errorMessage.setText(message);
    }

    private void addListener() {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleBackButtonAction();
            }
        });

        changePasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleChangePasswordAction();
            }
        });
    }
}
