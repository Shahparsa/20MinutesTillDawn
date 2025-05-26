package com.tilldawn.views.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Main;
import com.tilldawn.controller.MenuControllers.SettingMenuController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Language;
import com.tilldawn.models.enums.Musics;

public class SettingMenuView implements Screen {
    private final SettingMenuController controller;
    private Stage stage;
    private Table table;
    private final Label titleLabel;
    private final Slider musicSlider;
    private final SelectBox<String> musicBox;
    private final CheckBox sfxCheckbox;
    private final TextButton upButton;
    private final TextButton downButton;
    private final TextButton leftButton;
    private final TextButton rightButton;
    private final TextButton reloadButton;
    private final TextButton shootButton;
    private final Label upLabel;
    private final Label downLabel;
    private final Label leftLabel;
    private final Label rightLabel;
    private final Label reloadLabel;
    private final Label shootLabel;
    private final CheckBox autoReloadCheckbox;
    private final TextButton backButton;
    private final CheckBox blackWhiteCheckbox;
    private final Label musicVolumeLabel;
    private final Label musicLabel;

    public SettingMenuView(SettingMenuController controller , Skin skin) {
        this.controller = controller;
        this.titleLabel = new Label(Language.SettingsMenu.getLanguage(), skin);
        titleLabel.setColor(Color.RED);
        titleLabel.setFontScale(2.5f);
        this.musicSlider = new Slider(0f, 1f, 0.01f, false, skin);
        musicSlider.setValue(App.getMusicVolume());
        this.musicBox = new SelectBox<>(skin);
        musicBox.setItems(Musics.Music1.getName(), Musics.Music2.getName(), Musics.Music3.getName());
        this.sfxCheckbox = new CheckBox("SFX" , skin);
        sfxCheckbox.setChecked(App.isSFX());
        this.upButton = new TextButton(App.getKeyName(App.getUp()) , skin);
        this.downButton = new TextButton(App.getKeyName(App.getDown()) , skin);
        this.leftButton = new TextButton(App.getKeyName(App.getLeft()) , skin);
        this.rightButton = new TextButton(App.getKeyName(App.getRight()) , skin);
        this.reloadButton = new TextButton(App.getKeyName(App.getReload()) , skin);
        this.shootButton = new TextButton(App.getKeyName(App.getShoot()) , skin);
        this.upLabel = new Label(Language.Up.getLanguage(), skin);
        this.downLabel = new Label(Language.Down.getLanguage(), skin);
        this.leftLabel = new Label(Language.Left.getLanguage(), skin);
        this.rightLabel = new Label(Language.Right.getLanguage(), skin);
        this.reloadLabel = new Label(Language.Reload.getLanguage(), skin);
        this.shootLabel = new Label(Language.Shoot.getLanguage() , skin);
        this.autoReloadCheckbox = new CheckBox(Language.AutoReload.getLanguage(), skin);
        autoReloadCheckbox.setChecked(App.isAutoReload());
        this.backButton = new TextButton(Language.Back.getLanguage(), skin);
        this.blackWhiteCheckbox = new CheckBox(Language.BlackAndWhite.getLanguage(), skin);
        blackWhiteCheckbox.setChecked(App.isBlackWhite());
        this.table = new Table();
        this.musicVolumeLabel = new Label(Language.Volume.getLanguage() ,skin);
        this.musicLabel = new Label(Language.Music.getLanguage() ,skin);
        addListener();
        controller.setView(this);
    }

    @Override
    public void show() {
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = GameAssetManager.getInstance().tableMenu(table , stage);

        table.setFillParent(true);
        table.center();
        table.add(titleLabel);
        table.row();
        Table musicSoundTable = new Table();
        musicSoundTable.add(musicVolumeLabel).width(300).padRight(20);
        musicSoundTable.add(musicSlider).width(300).padLeft(20);
        table.add(musicSoundTable);
        table.row();
        table.row();
        Table musicTable = new Table();
        musicTable.add(musicLabel).width(300).padRight(20);
        musicTable.add(musicBox).width(300).padLeft(20);
        table.add(musicTable);
        table.row();
        Table controlsTable = new Table();
        controlsTable.add(upButton);
        controlsTable.add(upLabel).width(100);
        controlsTable.add(downButton);
        controlsTable.add(downLabel).width(100);
        controlsTable.add(leftButton);
        controlsTable.add(leftLabel).width(100);
        controlsTable.row();
        controlsTable.add(rightButton);
        controlsTable.add(rightLabel).width(100);
        controlsTable.add(reloadButton);
        controlsTable.add(reloadLabel).width(100);
        controlsTable.add(shootButton);
        controlsTable.add(shootLabel).width(100);;
        controlsTable.row();
        table.add(controlsTable);
        table.row();
        Table checkBoxes = new Table();
        checkBoxes.add(sfxCheckbox).width(100);
        checkBoxes.add(blackWhiteCheckbox).width(700);
        checkBoxes.add(autoReloadCheckbox).width(500);
        table.add(checkBoxes);
        table.row();
        table.add(backButton).width(300);
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

    public Slider getMusicSlider() {
        return musicSlider;
    }

    public SelectBox<String> getMusicBox() {
        return musicBox;
    }

    public CheckBox getSfxCheckbox() {
        return sfxCheckbox;
    }

    public TextButton getUpButton() {
        return upButton;
    }

    public TextButton getDownButton() {
        return downButton;
    }

    public TextButton getLeftButton() {
        return leftButton;
    }

    public TextButton getRightButton() {
        return rightButton;
    }

    public TextButton getReloadButton() {
        return reloadButton;
    }

    public TextButton getShootButton(){
        return shootButton;
    }

    public CheckBox getAutoReloadCheckbox() {
        return autoReloadCheckbox;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public CheckBox getBlackWhiteCheckbox() {
        return blackWhiteCheckbox;
    }

    public void addListener(){
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleBackButton();
            }
        });

        upButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleUpButton();
            }
        });

        downButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleDownButton();
            }
        });

        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleLeftButton();
            }
        });

        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleRightButton();
            }
        });

        reloadButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleReloadButton();
            }
        });

        shootButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleShootButton();
            }
        });
    }
}
