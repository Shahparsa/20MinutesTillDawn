package com.tilldawn.views.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Main;
import com.tilldawn.controller.MenuControllers.MainMenuController;
import com.tilldawn.controller.MenuControllers.PreGameController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Language;
import org.w3c.dom.Text;

public class PreGameMenuView implements Screen {
    private final PreGameController controller;
    private Stage stage;
    private Table table;
    private final Label titleLabel;
    private final Label timeLabel;
    private final Label weaponLabel;
    private final SelectBox<String> weaponSelectBox;
    private final SelectBox<String> timeSelectBox;
    private final TextButton startButton;
    private final TextButton backButton;
    private final Animation<TextureRegion> heroAnimation;
    private float stateTime;
    private final Texture[] heroTextures;
    private final TextureRegion[] heroRegions;

    public PreGameMenuView(PreGameController preGameController, Skin skin) {
        this.controller = preGameController;
        this.table = new Table();
        this.titleLabel = new Label(Language.SelectTimeAndWeapon.getLanguage(), skin);
        this.titleLabel.setFontScale(2f);
        this.weaponLabel = new Label(Language.Weapon.getLanguage(), skin);
        this.timeLabel = new Label(Language.Time.getLanguage(), skin);
        this.weaponSelectBox = new SelectBox<>(skin);
        weaponSelectBox.setItems(
            Language.Shotgun.getLanguage(),
            Language.DualSMG.getLanguage(),
            Language.Revolver.getLanguage()
        );
        this.timeSelectBox = new SelectBox<>(skin);
        timeSelectBox.setItems(
            "2",
            "5",
            "10",
            "20"
        );
        this.startButton = new TextButton(Language.Start.getLanguage(), skin);
        this.backButton = new TextButton(Language.Back.getLanguage(), skin);
        this.heroTextures = new Texture[]{
            new Texture(App.getHero().getIdle().getAnimationAddresses().get(0)),
            new Texture(App.getHero().getIdle().getAnimationAddresses().get(1)),
            new Texture(App.getHero().getIdle().getAnimationAddresses().get(2)),
            new Texture(App.getHero().getIdle().getAnimationAddresses().get(3)),
            new Texture(App.getHero().getIdle().getAnimationAddresses().get(4)),
        };

        this.heroRegions = new TextureRegion[]{
            new TextureRegion(heroTextures[0]),
            new TextureRegion(heroTextures[1]),
            new TextureRegion(heroTextures[2]),
            new TextureRegion(heroTextures[3]),
            new TextureRegion(heroTextures[4])
        };

        this.heroAnimation = new Animation<>(0.1f, heroRegions);
        addListeners();
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = GameAssetManager.getInstance().tableMenu(table , stage);
        stateTime = 0f;

        table.setFillParent(true);
        table.center();
        table.add(titleLabel);
        table.row();
        Table timeTable = new Table();
        timeTable.add(timeLabel).padLeft(20);
        timeTable.add(timeSelectBox).padLeft(20);
        table.add(timeTable);
        table.row();
        Table weaponTable = new Table();
        weaponTable.add(weaponLabel).padLeft(20);
        weaponTable.add(weaponSelectBox).padLeft(20);
        table.add(weaponTable);
        table.row();
        Table buttonTable = new Table();
        buttonTable.add(backButton).padLeft(20);
        buttonTable.add(startButton).padLeft(20);
        table.add(buttonTable);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        stateTime += delta;

        TextureRegion currentFrame = heroAnimation.getKeyFrame(stateTime, true);
        Main.getBatch().begin();
        Main.getBatch().draw(currentFrame, 450, 450, heroTextures[0].getWidth() * 7, heroTextures[0].getHeight() * 7);
        Main.getBatch().end();
        stage.act(delta);
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
        if (stage != null) {
            stage.dispose();
        }
        for (Texture texture : heroTextures) {
            texture.dispose();
        }
    }

    public SelectBox<String> getWeaponSelectBox() {
        return weaponSelectBox;
    }

    public SelectBox<String> getTimeSelectBox() {
        return timeSelectBox;
    }

    public TextButton getStartButton() {
        return startButton;
    }

    public TextButton getBackButton() {
        return backButton;
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
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleStartButton();
            }
        });
    }
}
