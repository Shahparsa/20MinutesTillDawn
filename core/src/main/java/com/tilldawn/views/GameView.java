package com.tilldawn.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.GameController;
import com.tilldawn.controller.MenuControllers.MainMenuController;
import com.tilldawn.controller.MenuControllers.PauseMenuController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.Player;
import com.tilldawn.models.Weapon.Bullet;
import com.tilldawn.views.Menus.MainMenuView;
import com.tilldawn.views.Menus.PauseMenuView;

public class GameView implements Screen, InputProcessor {
    private final OrthographicCamera camera;
    private final GameController controller;
    private Stage stage;
    private Table table;
    private Table xpTable;

    private Texture glowTexture;
    private Sprite glowSprite;
    private Color glowColor;

    private final Label hpLabel;
    private final Label timeLabel;
    private final Label levelLabel;
    private final Label xpLevelLabel;
    private final Label killLabel;
    private final Label ammoLabel;
    private final Label fakeLabel;
    private final ProgressBar xpLevelProgressBar;

    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        controller.setPlayer(App.getCurrentGame().getPlayer());
        controller.setView(this);

        stage = new Stage(new ScreenViewport());
        camera = new OrthographicCamera(stage.getWidth(), stage.getHeight());
        Gdx.input.setInputProcessor(stage);

        hpLabel = new Label("HP: " + App.getCurrentGame().getPlayer().getHp(), skin);
        timeLabel = new Label("Time: ", skin);
        levelLabel = new Label("Level: " + App.getCurrentGame().getPlayer().getLevel(), skin);
        xpLevelLabel = new Label("XP: " + App.getCurrentGame().getPlayer().getXp(), skin);
        killLabel = new Label("Kill: " + App.getCurrentGame().getPlayer().getKills(), skin);
        ammoLabel = new Label("" + App.getCurrentGame().getWeapon().getAmmo(), skin);
        fakeLabel = new Label("", skin);
        Player player = App.getCurrentGame().getPlayer();
        xpLevelProgressBar = new ProgressBar(0, player.getLevel() * 20, 1, false, skin);
        xpLevelProgressBar.setColor(Color.RED);

        glowTexture = new Texture(Gdx.files.internal("hale.png"));
        glowSprite = new Sprite(new TextureRegion(glowTexture));
        glowColor = new Color(glowSprite.getColor().r, glowSprite.getColor().g, glowSprite.getColor().b, 0.6f);
        glowSprite.setOrigin(glowSprite.getWidth() / 2, glowSprite.getHeight() / 2);

    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(this);
        table = new Table();
        xpTable = new Table();
        table.setFillParent(true);
        table.setVisible(true);
        xpTable.setFillParent(true);
        xpTable.setVisible(true);
        //Camera
        camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);

        table.top().left();
        table.add(fakeLabel);
        table.row();
        table.add(hpLabel);
        table.row();
        table.add(timeLabel);
        table.row();
        table.add(killLabel);
        table.row();
        Image ammo = new Image(Bullet.getTextureImage());
        table.add(ammo).size(69, 85);
        table.add(ammoLabel).padLeft(20);
        table.row();

        xpTable.setFillParent(true);
        xpTable.top();

        Stack progressStack = new Stack();

        levelLabel.setAlignment(Align.center);
        xpLevelLabel.setAlignment(Align.right);

        progressStack.add(xpLevelProgressBar);
        progressStack.add(levelLabel);
        progressStack.add(xpLevelLabel);

        xpTable.add(progressStack).width(Gdx.graphics.getWidth()).height(30).padTop(10);

        stage.addActor(xpTable);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        if(Gdx.input.isKeyJustPressed(App.getPauseMenu())){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new PauseMenuView(new PauseMenuController() , this
                , GameAssetManager.getInstance().getSkin()));
        }
        if(App.getCurrentGame().getPlayer().getHp() <= 0){
            controller.handleEndGame(false);
        }
        if(App.getCurrentGame().getFullTime() * 60 <= App.getCurrentGame().getRealTime()){
            controller.handleEndGame(true);
        }
        float heroX = controller.getPlayer().getX();
        float heroY = controller.getPlayer().getY();
        camera.position.set(heroX, heroY, 0);
        camera.update();

        Main.getBatch().setProjectionMatrix(camera.combined);

        Main.getBatch().begin();

        controller.updateGame(delta, camera);
        controller.checkLevelUp(this);
        if (glowTexture != null) {
            glowSprite.setSize(Gdx.graphics.getWidth() + 100, Gdx.graphics.getHeight() + 100);
            glowSprite.setColor(glowColor);
            glowSprite.setPosition(heroX - Gdx.graphics.getWidth() / 2f, heroY - Gdx.graphics.getHeight() / 2f);
            glowSprite.draw(Main.getBatch());
        }
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

    public Label getHpLabel() {
        return hpLabel;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public Label getLevelLabel() {
        return levelLabel;
    }

    public Label getXpLevelLabel() {
        return xpLevelLabel;
    }

    public Label getKillLabel() {
        return killLabel;
    }

    public Label getAmmoLabel() {
        return ammoLabel;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public ProgressBar getXpLevelProgressBar() {
        return xpLevelProgressBar;
    }


}
