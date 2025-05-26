package com.tilldawn.views.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Main;
import com.tilldawn.controller.MenuControllers.ProfileMenuController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.AvatarType;
import com.tilldawn.models.enums.Language;
import com.tilldawn.models.enums.Musics;

public class ProfileMenuView implements Screen {
    private final ProfileMenuController controller;
    private Stage stage;
    private Table table;
    private final Label titleLabel;
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextButton avatarChangeButton;
    private final TextButton changeButton;
    private final TextButton backButton;
    private final TextButton deleteButton;
    private final DragAndDrop dragAndDrop;
    private Image currentAvatar;
    private Label errorLabel;
    private final SelectBox<String> imageSelectBox;
    private final TextButton uploadButton;

    public ProfileMenuView(ProfileMenuController controller , Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.titleLabel = new Label(Language.ProfileMenu.getLanguage(), skin);
        titleLabel.setColor(Color.RED);
        titleLabel.setFontScale(2.5f);
        this.usernameField = new TextField("", skin);
        usernameField.setMessageText(Language.UserName.getLanguage());
        this.passwordField = new TextField("", skin);
        passwordField.setMessageText(Language.Password.getLanguage());
        this.changeButton = new TextButton(Language.Change.getLanguage(), skin);
        this.backButton = new TextButton(Language.Back.getLanguage(), skin);
        this.dragAndDrop = new DragAndDrop();
        this.avatarChangeButton = new TextButton(Language.ChangeAvatar.getLanguage(), skin);
        this.errorLabel = new Label("" , skin);
        this.uploadButton = new TextButton(Language.Upload.getLanguage(), skin);
        this.currentAvatar = new Image(App.getCurrentUser().getAvatar());
        currentAvatar.setSize(250 , 250);
        this.imageSelectBox = new SelectBox<String>(skin);
        this.deleteButton = new TextButton(Language.DeleteAccount.getLanguage(), skin);
        imageSelectBox.setItems(AvatarType.Abby.getName(),
            AvatarType.Dasher.getName(),
            AvatarType.Diamond.getName(),
            AvatarType.Hastur.getName(),
            AvatarType.Hina.getName(),
            AvatarType.Lilith.getName(),
            AvatarType.Luna.getName(),
            AvatarType.Raven.getName(),
            AvatarType.Scarlet.getName(),
            AvatarType.Shane.getName(),
            AvatarType.Spark.getName(),
            AvatarType.Yuki.getName());
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
        table.add(titleLabel);
        table.row();
        currentAvatar.setScaling(Scaling.fit);
        table.add(currentAvatar).width(250).height(250);
        table.row();
        table.add(errorLabel).padBottom(20);
        table.row();
        table.add(imageSelectBox);
        table.row();
        table.add(usernameField).width(500);
        table.row();
        table.add(passwordField).width(500);
        table.row();
        Table buttonsTable = new Table();
        buttonsTable.add(changeButton).width(250).padLeft(30);
        buttonsTable.add(avatarChangeButton).width(500).padLeft(30);
        buttonsTable.add(uploadButton).width(250).padRight(30);
        table.add(buttonsTable);
        table.row();
        Table deleteTable = new Table();
        deleteTable.add(deleteButton).width(500).padLeft(30);
        deleteTable.add(backButton).width(500).padRight(30);
        table.add(deleteTable);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.getBatch().setShader(Main.getBatch().getShader());
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

    public TextButton getUploadButton() {
        return uploadButton;
    }

    public SelectBox<String> getImageSelectBox() {
        return imageSelectBox;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public Image getCurrentAvatar() {
        return currentAvatar;
    }

    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextButton getChangeButton() {
        return changeButton;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setCurrentAvatar(Texture currentAvatarPath) {
        this.currentAvatar.setDrawable(new Image(currentAvatarPath).getDrawable());
        this.currentAvatar.setSize(250, 250);
    }

    public void setErrorLabel(String message) {
        this.errorLabel.setText(message);
    }

    public TextButton getAvatarChangeButton() {
        return avatarChangeButton;
    }

    public TextButton getDeleteButton() {
        return deleteButton;
    }

    private void addListeners(){
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleBackButton();
            }
        });

        changeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleChangeButton();
            }
        });

        deleteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleDeleteButton();
            }
        });

        avatarChangeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleAvatarChangeButton();
            }
        });

        uploadButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleUploadButton();
            }
        });

    }
}
