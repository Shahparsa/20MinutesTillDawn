package com.tilldawn.views.Menus;

import com.badlogic.gdx.Screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.tilldawn.Main;
import com.tilldawn.controller.MenuControllers.HeroSelectController;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.AvatarType;
import com.tilldawn.models.enums.Language;


public class HeroSelectView implements Screen {
    private final HeroSelectController controller;
    private Stage stage;
    private Table table;
    private final Skin skin;
    private final Texture[] heroTextures = new Texture[6];
    private final Image[] heroImages = new Image[6];
    private final Label titleLabel;
    private final TextButton backButton;
    private final String[] heroNames = {"Hina", "Dasher", "Diamond", "Lilith", "Scarlet", "Shane"};
    private final String[] heroDescriptions = {
        Language.Speed.getLanguage() + ":4\n" + Language.HP.getLanguage() + ":5",
        Language.Speed.getLanguage() + ":10\n" + Language.HP.getLanguage() + ":2",
        Language.Speed.getLanguage() + ":1\n" + Language.HP.getLanguage() + ":7",
        Language.Speed.getLanguage() + ":3\n" + Language.HP.getLanguage() + ":5",
        Language.Speed.getLanguage() + ":5\n" + Language.HP.getLanguage() + ":3",
        Language.Speed.getLanguage() + ":4\n" + Language.HP.getLanguage() + ":4"
    };
    public HeroSelectView(HeroSelectController controller , Skin skin) {
        this.controller = controller;
        titleLabel = new Label(Language.SelectYouHero.getLanguage(), skin);
        heroTextures[0] = new Texture(AvatarType.Hina.getTexturePath());
        heroTextures[1] = new Texture(AvatarType.Dasher.getTexturePath());
        heroTextures[2] = new Texture(AvatarType.Diamond.getTexturePath());
        heroTextures[3] = new Texture(AvatarType.Lilith.getTexturePath());
        heroTextures[4] = new Texture(AvatarType.Scarlet.getTexturePath());
        heroTextures[5] = new Texture(AvatarType.Shane.getTexturePath());
        for(int i = 0; i < 6; i++){
            heroImages[i] = new Image(heroTextures[i]);
        }
        this.table = new Table();
        this.skin = skin;
        this.backButton = new TextButton(Language.Back.getLanguage(), skin);
        addListener();
        controller.setView(this);
    }
    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = GameAssetManager.getInstance().tableMenu(table , stage);
        table.setFillParent(true);
        titleLabel.setBounds(712 , 850 , 300 , 200);
        titleLabel.setFontScale(2f);
        stage.addActor(table);
        stage.addActor(titleLabel);

        for (int i = 0; i < 6; i++) {
            final int selectedIndex = i;

            Table heroTable = new Table();
            heroTable.setBounds(500 + (i % 3) * 350, (i < 3) ? 600 : 300, 200, 250);

            heroImages[i].setSize(heroTextures[i].getWidth(), heroTextures[i].getHeight());
            heroImages[i].setTouchable(Touchable.enabled);

            heroImages[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(App.isSFX()){
                        GameAssetManager.getInstance().getButtonSFX().play();
                    }
                    controller.selectHero(selectedIndex);
                }
            });

            Label nameLabel = new Label(heroNames[i], skin);
            nameLabel.setFontScale(1f);
            Label descLabel = new Label(heroDescriptions[i], skin);
            descLabel.setFontScale(0.9f);
            descLabel.setWrap(true);
            descLabel.setWidth(300f);

            heroTable.add(heroImages[i]).size(heroTextures[i].getWidth() / 2f, heroTextures[i].getHeight() / 2f);
            heroTable.row();
            heroTable.add(nameLabel).padTop(5).row();
            heroTable.add(descLabel).width(160).padTop(5).row();
            backButton.setBounds(stage.getWidth()/2 - 100, 150 , 200 , 100);
            stage.addActor(backButton);
            stage.addActor(heroTable);
        }
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
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        for (Texture texture : heroTextures) {
            texture.dispose();
        }
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

