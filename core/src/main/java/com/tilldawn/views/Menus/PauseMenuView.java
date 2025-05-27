package com.tilldawn.views.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.MenuControllers.MainMenuController;
import com.tilldawn.controller.MenuControllers.PauseMenuController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.SecurityQuestion;
import com.tilldawn.models.User;
import com.tilldawn.models.enums.Language;
import com.tilldawn.models.enums.SecurityQuestionType;
import com.tilldawn.views.GameView;

public class PauseMenuView implements Screen {
    private final PauseMenuController controller;
    private Stage stage;
    private Table table;
    private final TextButton resumeButton;
    private final TextButton saveButton;
    private final TextButton giveUpButton;
    private final Label titleLabe;
    private static final String[] cheats = new String[5];
    private final Label[] cheatsLabel = new Label[5];
    private final CheckBox blackAndWhiteCheckBox;
    private final Label numberOfVitalityLabel;
    private final Label numberOfDamagersLabel;
    private final Label numberOfProCreaseLabel;
    private final Label numberOfAmoCreaseLabel;
    private final Label numberOfSpeedyLabel;

    static {
        cheats[0] = Language.CheatTime.getLanguage() + " NUMPAD_1";
        cheats[1] = Language.CheatLevel.getLanguage() + " NUMPAD_2";
        cheats[2] = Language.CheatHealth.getLanguage() + " NUMPAD_3";
        cheats[3] = Language.CheatBoss.getLanguage() + " NUMPAD_4";
        cheats[4] = Language.CheatDamage.getLanguage() + " NUMPAD_5";
    }

    public PauseMenuView(PauseMenuController controller , GameView view ,  Skin skin) {
        this.controller = controller;
        stage = new Stage();
        this.table = new Table();

        titleLabe = new Label(Language.PauseMenu.getLanguage(), skin);
        titleLabe.setFontScale(1.5f);

        resumeButton = new TextButton(Language.Resume.getLanguage(), skin);
        saveButton = new TextButton(Language.SaveAndExit.getLanguage(), skin);
        giveUpButton = new TextButton(Language.GiveUp.getLanguage(), skin);
        for(int i = 0 ; i < 5 ; i++){
            cheatsLabel[i] = new Label(cheats[i] , skin);
        }
        numberOfAmoCreaseLabel = new Label(Language.AmoCrease.getLanguage() + " : "
            + App.getCurrentGame().getAmoCreaseNumber(), skin);
        numberOfVitalityLabel = new Label(Language.Vitality.getLanguage() + " : "
            + App.getCurrentGame().getVitalityNumber(), skin);
        numberOfDamagersLabel = new Label(Language.Damager.getLanguage() + " : "
            + App.getCurrentGame().getDamagerNumber(), skin);
        numberOfProCreaseLabel = new Label(Language.ProCrease.getLanguage() + " : "
            + App.getCurrentGame().getProCreaseNumber(), skin);
        numberOfSpeedyLabel = new Label(Language.Speedy.getLanguage() + " : "
            + App.getCurrentGame().getSpeedyNumber(), skin);
        blackAndWhiteCheckBox = new CheckBox(Language.BlackAndWhite.getLanguage(), skin);
        addListener();
        controller.setView(this , view);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = GameAssetManager.getInstance().tableMenu(table , stage);

        table.setFillParent(true);
        table.center();
        table.add(titleLabe).padBottom(20);
        table.row();
        table.add(resumeButton).padBottom(20);
        table.row();
        table.add(blackAndWhiteCheckBox).padBottom(20);
        table.row();
        table.add(saveButton).padBottom(20);
        table.row();
        table.add(giveUpButton).padBottom(20);
        table.row();

        Table abilityTable = new Table();
        abilityTable.add(numberOfAmoCreaseLabel).padBottom(20).row();
        abilityTable.add(numberOfSpeedyLabel).padBottom(20).row();
        abilityTable.add(numberOfProCreaseLabel).padBottom(20).row();
        abilityTable.add(numberOfDamagersLabel).padBottom(20).row();
        abilityTable.add(numberOfVitalityLabel).padBottom(20).row();

        Table cheatsTable = new Table();
        for(int i = 0 ; i < 5 ; i++){;
            cheatsTable.add(cheatsLabel[i]).padBottom(20).row();
        }

        abilityTable.setPosition(1500 , Gdx.graphics.getHeight() - 200);
        cheatsTable.setPosition(300 , Gdx.graphics.getHeight() - 200);

        stage.addActor(table);
        stage.addActor(abilityTable);
        stage.addActor(cheatsTable);
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
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleResumeButton();
            }
        });
        giveUpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleGiveUpButton(false);
            }
        });
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleSaveAndExitButton();
            }
        });
        blackAndWhiteCheckBox.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(App.isSFX()){
                    GameAssetManager.getInstance().getButtonSFX().play();
                }
                controller.handleBlackAndWhiteButton();
            }
        });
    }
}
