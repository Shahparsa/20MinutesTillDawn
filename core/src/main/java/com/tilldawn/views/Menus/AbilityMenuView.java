package com.tilldawn.views.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
import com.tilldawn.controller.MenuControllers.AbilityMenuController;
import com.tilldawn.controller.MenuControllers.PauseMenuController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Abilities;
import com.tilldawn.models.enums.Language;
import com.tilldawn.views.GameView;

import java.util.*;

public class AbilityMenuView implements Screen {
    private Stage stage;
    private Table table;
    private AbilityMenuController controller;
    private Label titleLabel;
    private Abilities ability1;
    private Abilities ability2;
    private Abilities ability3;
    private TextButton abilityButton1;
    private TextButton abilityButton2;
    private TextButton abilityButton3;

    public AbilityMenuView(AbilityMenuController controller, GameView view, Skin skin) {
        this.controller = controller;
        stage = new Stage();
        this.table = new Table();
        List<Abilities> selectedAbilities;
        selectedAbilities = controller.getThreeRandomAbilities();
        ability1 = selectedAbilities.get(0);
        ability2 = selectedAbilities.get(1);
        ability3 = selectedAbilities.get(2);
        abilityButton1 = new TextButton(ability1.getLanguage(), skin);
        abilityButton2 = new TextButton(ability2.getLanguage(), skin);
        abilityButton3 = new TextButton(ability3.getLanguage(), skin);
        titleLabel = new Label(Language.ChooseAbilities.getLanguage(), skin);
        titleLabel.setFontScale(2.5f);
        controller.setView(this , view);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = GameAssetManager.getInstance().tableMenu(new Table(), stage);
        table.setFillParent(true);

        table.add(titleLabel).pad(20).expandX().center();
        table.row();

        Table abilitiesTable = new Table();
        abilitiesTable.add(abilityButton1).pad(20);
        abilitiesTable.add(abilityButton2).pad(20);
        abilitiesTable.add(abilityButton3).pad(20);
        table.add(abilitiesTable).pad(20).center();

        addListener();
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

    private void addListener() {
        abilityButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleButton(ability1);
            }
        });
        abilityButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleButton(ability2);
            }
        });
        abilityButton3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleButton(ability3);
            }
        });
    }
}
