package com.tilldawn.views.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.MenuControllers.MainMenuController;
import com.tilldawn.controller.MenuControllers.PauseMenuController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Language;
import com.tilldawn.views.GameView;

public class EndMenuView implements Screen {
    private Stage stage;
    private Table table;
    private GameView gameView;
    private final Label titleLabel;
    private final Label timeLabel;
    private final Label killLabel;
    private final Label nameLabel;
    private final Label pointsLabel;
    private final TextButton backButton;

    public EndMenuView(boolean mode, GameView view, Skin skin) {
        stage = new Stage();
        table = new Table();
        this.gameView = view;
        if (mode) {
            titleLabel = new Label(Language.YouWin.getLanguage(), skin);
            titleLabel.setColor(Color.GREEN);
        } else {
            titleLabel = new Label(Language.YouLose.getLanguage(), skin);
            titleLabel.setColor(Color.RED);
        }
        titleLabel.setFontScale(2.5f);
        nameLabel = new Label(App.getCurrentUser().getUsername(), skin);
        timeLabel = new Label(Language.Time.getLanguage() + " : " + App.getCurrentGame().getRealTime(), skin);
        killLabel = new Label(Language.Kill.getLanguage() + " : " + App.getCurrentGame().getPlayer().getKills(), skin);
        int point = ((int) App.getCurrentGame().getRealTime()) * App.getCurrentGame().getPlayer().getKills();
        pointsLabel = new Label(Language.Score.getLanguage() + " : " + point, skin);
        App.getCurrentUser().setScore(App.getCurrentUser().getScore() + point);
        if (App.getCurrentGame().getRealTime() > App.getCurrentUser().getTimeAlive()) {
            App.getCurrentUser().setTimeAlive(App.getCurrentUser().getTimeAlive());
        }
        backButton = new TextButton(Language.Back.getLanguage(), skin);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = GameAssetManager.getInstance().tableMenu(table, stage);

        table.setFillParent(true);
        table.add(titleLabel).row();
        table.add(nameLabel).row();
        table.add(timeLabel).row();
        table.add(killLabel).row();
        table.add(pointsLabel).row();
        table.add(backButton).row();
        addListeners();
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        if (Gdx.input.isKeyJustPressed(App.getCheatAddHealth())) {
            App.getCurrentGame().getPlayer().setHp(1);
            backToGame();
        }
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

    private void backToGame() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(gameView);
    }

    private void addListeners() {
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController()
                    , GameAssetManager.getInstance().getSkin()));
            }
        });
    }
}
