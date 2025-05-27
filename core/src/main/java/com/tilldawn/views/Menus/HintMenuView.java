package com.tilldawn.views.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Main;
import com.tilldawn.controller.MenuControllers.HintMenuController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Language;

public class HintMenuView implements Screen {
    private final HintMenuController controller;
    private Stage stage;
    private Table table;
    private Label titleLabel;
    private TextButton backButton;
    private final String[] heroNames = {"Hina", "Dasher", "Diamond", "Lilith", "Scarlet", "Shane"};
    private final String[] heroDescriptions = {
        Language.Speed.getLanguage() + ":4\n" + Language.HP.getLanguage() + ":5",
        Language.Speed.getLanguage() + ":10\n" + Language.HP.getLanguage() + ":2",
        Language.Speed.getLanguage() + ":1\n" + Language.HP.getLanguage() + ":7",
        Language.Speed.getLanguage() + ":3\n" + Language.HP.getLanguage() + ":5",
        Language.Speed.getLanguage() + ":5\n" + Language.HP.getLanguage() + ":3",
        Language.Speed.getLanguage() + ":4\n" + Language.HP.getLanguage() + ":4"
    };
    private final String[] cheats = {
        Language.CheatTime.getLanguage(),
        Language.CheatLevel.getLanguage(),
        Language.CheatHealth.getLanguage(),
        Language.CheatBoss.getLanguage(),
        Language.CheatDamage.getLanguage(),
    };

    private final String[] cheatDescriptions = {
        Language.CheatTimeDescription.getLanguage(),
        Language.CheatLevelDescription.getLanguage(),
        Language.CheatHealthDescription.getLanguage(),
        Language.CheatBossDescription.getLanguage(),
        Language.CheatDamageDescription.getLanguage(),
    };

    private final String[] Abilities = {
        Language.Vitality.getLanguage(),
        Language.Damager.getLanguage(),
        Language.ProCrease.getLanguage(),
        Language.AmoCrease.getLanguage(),
        Language.Speed.getLanguage()
    };

    private final String[] AbilitiesDescriptions = {
        com.tilldawn.models.enums.Abilities.VITALITY.getLanguage(),
        com.tilldawn.models.enums.Abilities.DAMAGER.getLanguage(),
        com.tilldawn.models.enums.Abilities.PROCREASE.getLanguage(),
        com.tilldawn.models.enums.Abilities.AMOCREASE.getLanguage(),
        com.tilldawn.models.enums.Abilities.SPEEDY.getLanguage()
    };


    public HintMenuView(HintMenuController controller , Skin skin) {
        this.controller = controller;
        this.table = new Table();
        titleLabel = new Label(Language.HintMenu.getLanguage(), skin);
        backButton = new TextButton(Language.Back.getLanguage(), skin);
        addListener();
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = GameAssetManager.getInstance().tableMenu(table , stage);

        Table heroTable = new Table();
        Label heroesLabel = new Label(Language.Heroes.getLanguage(), GameAssetManager.getInstance().getSkin());
        heroTable.add(heroesLabel).row();
        for(int i = 0 ; i < 6 ; i++){
            Label heroLabel = new Label(heroNames[i], GameAssetManager.getInstance().getSkin());
            Label heroDescriptionLabel = new Label(heroDescriptions[i], GameAssetManager.getInstance().getSkin());
            heroTable.add(heroLabel).pad(5).width(20);
            heroTable.add(heroDescriptionLabel).pad(5).padLeft(100).width(200);
            heroTable.row();
        }

        Table cheatTable = new Table();
        Label cheatsLabel = new Label(Language.Cheats.getLanguage(), GameAssetManager.getInstance().getSkin());
        cheatTable.add(cheatsLabel).row();
        for(int i = 0 ; i < 5 ; i++){
            Label cheatLabel = new Label(cheats[i], GameAssetManager.getInstance().getSkin());
            Label cheatDescriptionLabel = new Label(cheatDescriptions[i], GameAssetManager.getInstance().getSkin());
            cheatTable.add(cheatLabel).pad(5).width(100);
            cheatTable.add(cheatDescriptionLabel).pad(5).padLeft(120).width(200);
            cheatTable.row();
        }
        Table abilitiesTable = new Table();
        Label abilitiesLabel = new Label(Language.Abilities.getLanguage(), GameAssetManager.getInstance().getSkin());
        abilitiesTable.add(abilitiesLabel).row();
        for(int i = 0 ; i < 5 ; i++){
            Label abilityLabel = new Label(Abilities[i], GameAssetManager.getInstance().getSkin());
            Label abilityDescriptionLabel = new Label(AbilitiesDescriptions[i], GameAssetManager.getInstance().getSkin());
            abilitiesTable.add(abilityLabel).pad(5).width(100);
            abilitiesTable.add(abilityDescriptionLabel).pad(5).padLeft(120).width(200);
            abilitiesTable.row();
        }
        Table keysTable = new Table();
        Label keysLabel = new Label(Language.Keys.getLanguage(), GameAssetManager.getInstance().getSkin());
        keysTable.add(keysLabel).row();
        Label upLabel = new Label(Language.Up.getLanguage(), GameAssetManager.getInstance().getSkin());
        Label downLabel = new Label(Language.Down.getLanguage(), GameAssetManager.getInstance().getSkin());
        Label leftLabel = new Label(Language.Left.getLanguage(), GameAssetManager.getInstance().getSkin());
        Label rightLabel = new Label(Language.Right.getLanguage(), GameAssetManager.getInstance().getSkin());
        Label reloadLabel = new Label(Language.Reload.getLanguage(), GameAssetManager.getInstance().getSkin());
        Label shootLabel = new Label(Language.Shoot.getLanguage(), GameAssetManager.getInstance().getSkin());
        Label upKey = new Label(App.getKeyName(App.getUp()) , GameAssetManager.getInstance().getSkin());
        Label downKey = new Label(App.getKeyName(App.getDown()) , GameAssetManager.getInstance().getSkin());
        Label leftKey = new Label(App.getKeyName(App.getLeft()) , GameAssetManager.getInstance().getSkin());
        Label rightKey = new Label(App.getKeyName(App.getRight()) , GameAssetManager.getInstance().getSkin());
        Label reloadKey = new Label(App.getKeyName(App.getReload()) , GameAssetManager.getInstance().getSkin());
        Label shootKey = new Label(App.getKeyName(App.getShoot()) , GameAssetManager.getInstance().getSkin());
        keysTable.add(upLabel).pad(5).width(100);
        keysTable.add(upKey).padLeft(120).width(200);
        keysTable.row();
        keysTable.add(downLabel).pad(5).width(100);
        keysTable.add(downKey).padLeft(120).width(200);
        keysTable.row();
        keysTable.add(leftLabel).pad(5).width(100);
        keysTable.add(leftKey).padLeft(120).width(200);
        keysTable.row();
        keysTable.add(rightLabel).pad(5).width(100);
        keysTable.add(rightKey).padLeft(120).width(200);
        keysTable.row();
        keysTable.add(reloadLabel).pad(5).width(100);
        keysTable.add(reloadKey).padLeft(120).width(200);
        keysTable.row();
        keysTable.add(shootLabel).pad(5).width(100);
        keysTable.add(shootKey).padLeft(120).width(200);
        keysTable.row();

        stage.addActor(table);
        heroTable.setPosition(300 , Gdx.graphics.getHeight() - 300);
        stage.addActor(heroTable);
        cheatTable.setPosition(300 , 300);
        stage.addActor(cheatTable);
        abilitiesTable.setPosition(1500 , Gdx.graphics.getHeight() - 200);
        stage.addActor(abilitiesTable);
        keysTable.setPosition(1500 , 500 );
        stage.addActor(keysTable);
        backButton.setPosition(Gdx.graphics.getWidth()/2f - 100 , 100);
        stage.addActor(backButton);
        titleLabel.setPosition(Gdx.graphics.getWidth()/2f - 200 , 900);
        titleLabel.setFontScale(2.5f);
        stage.addActor(titleLabel);
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

    public TextButton getBackButton() {
        return backButton;
    }

    private void addListener(){
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.navigateMainMenu();
            }
        });
    }
}
