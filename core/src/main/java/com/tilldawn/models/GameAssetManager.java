package com.tilldawn.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameAssetManager {
    private static GameAssetManager instance;
    private final Skin skin = new Skin(Gdx.files.internal("Skin/pixthulhu-ui.json"));
    // SFXs
    private final Sound menuSFX = Gdx.audio.newSound(Gdx.files.internal("SFXs/UI Click 36.wav"));
    private final Sound levelUpSFx = Gdx.audio.newSound(Gdx.files.internal("SFXs/Special & Powerup.wav"));
    private final Sound loseSFX = Gdx.audio.newSound(Gdx.files.internal("SFXs/You Lose.wav"));
    private final Sound winSFX = Gdx.audio.newSound(Gdx.files.internal("SFXs/You Win.wav"));
    private final Sound shootSFX = Gdx.audio.newSound(Gdx.files.internal("SFXs/single_shot.wav"));
    private final Sound footStepSFX = Gdx.audio.newSound(Gdx.files.internal("SFXs/Footsteps.wav"));
    private final Sound deathSFX = Gdx.audio.newSound(Gdx.files.internal("SFXs/Death.wav"));
    private final Sound hitSFX = Gdx.audio.newSound(Gdx.files.internal("SFXs/Blood_Splash.wav"));

    //Size -1900 <= x <= 1850 & -1350 <= y <= 1300
    private final float left= -1900;
    private final float right = 1850;
    private final float top = 1300;
    private final float bottom = -1350;

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final String bullet = "Weapons/bullet.png";
    private final Texture backgroundTexture = new Texture("background.png");
    private static final ShaderProgram lightShader;
    private static Animation<TextureRegion> explosionAnimation;

    static {
        String vertexShader = "attribute vec4 a_position;\n" +
            "attribute vec2 a_texCoord0;\n" +
            "uniform mat4 u_projTrans;\n" +
            "varying vec2 v_texCoord0;\n" +
            "void main() {\n" +
            "   gl_Position = u_projTrans * a_position;\n" +
            "   v_texCoord0 = a_texCoord0;\n" +
            "}";

        String fragmentShader = "#ifdef GL_ES\n" +
            "precision mediump float;\n" +
            "#endif\n" +
            "varying vec2 v_texCoord0;\n" +
            "uniform sampler2D u_texture;\n" +
            "uniform vec2 u_lightPos;\n" +
            "uniform float u_lightRadius;\n" +
            "uniform mat4 u_projTrans;\n" +
            "void main() {\n" +
            "   vec4 color = texture2D(u_texture, v_texCoord0);\n" +
            "   vec2 screenPos = (u_projTrans * vec4(v_texCoord0, 0, 1)).xy;\n" +
            "   float dist = distance(screenPos, u_lightPos);\n" +
            "   float light = 1.0 - smoothstep(0.0, u_lightRadius, dist);\n" +
            "   gl_FragColor = color * (0.4 + light * 0.6);\n" +
            "}";

        lightShader = new ShaderProgram(vertexShader, fragmentShader);
        Texture frame1 = new Texture(Gdx.files.internal("Enemies/Explosion/ExplosionFX_0.png"));
        Texture frame2 = new Texture(Gdx.files.internal("Enemies/Explosion/ExplosionFX_1.png"));
        Texture frame3 = new Texture(Gdx.files.internal("Enemies/Explosion/ExplosionFX_2.png"));
        Texture frame4 = new Texture(Gdx.files.internal("Enemies/Explosion/ExplosionFX_3.png"));
        Texture frame5 = new Texture(Gdx.files.internal("Enemies/Explosion/ExplosionFX_4.png"));
        Texture frame6 = new Texture(Gdx.files.internal("Enemies/Explosion/ExplosionFX_5.png"));
        TextureRegion[] frames = new TextureRegion[] {
            new TextureRegion(frame1),
            new TextureRegion(frame2),
            new TextureRegion(frame3),
            new TextureRegion(frame4),
            new TextureRegion(frame5),
            new TextureRegion(frame6)
        };
        explosionAnimation = new Animation<>(0.1f, frames);

    }

    public Table tableMenu(Table table, Stage stage) {
        Texture leave = new Texture(Gdx.files.internal("MenuThings/T_TitleLeaves.png"));
        Image leaveImage = new Image(leave);
        float imageRatio = leaveImage.getWidth() / leaveImage.getHeight();
        float fitHeight = stage.getHeight();
        float fitWidth = fitHeight * imageRatio;
        // left side leaves
        leaveImage.setPosition(0, 0);
        leaveImage.setSize(fitWidth, fitHeight);
        table.addActor(leaveImage);

        // right side leaves
        TextureRegion region = new TextureRegion(leave);
        region.flip(true, false);
        Image image = new Image(region);
        image.setPosition(stage.getWidth() - fitWidth, 0);
        image.setSize(fitWidth, fitHeight);
        table.addActor(image);
        return table;
    }

    public Image Logo() {
        Texture logo = new Texture(Gdx.files.internal("MenuThings/T_20Logo.png"));
        return new Image(logo);
    }

    public static GameAssetManager getInstance() {
        if (instance == null) {
            instance = new GameAssetManager();
        }
        return instance;
    }

    public Skin getSkin() {
        return skin;
    }

    public String getBullet() {
        return bullet;
    }

    public Sound getButtonSFX() {
        return menuSFX;
    }

    public Sound getDeathSFX() {
        return deathSFX;
    }

    public Sound getFootStepSFX() {
        return footStepSFX;
    }

    public Sound getShootSFX() {
        return shootSFX;
    }

    public Sound getWinSFX() {
        return winSFX;
    }

    public Sound getLoseSFX() {
        return loseSFX;
    }

    public Sound getLevelUpSFx() {
        return levelUpSFx;
    }

    public Sound getHitSFX() {
        return hitSFX;
    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public ShaderProgram getLightShader() {
        return lightShader;
    }

    public static Animation<TextureRegion> getExplosionAnimation() {
        return explosionAnimation;
    }

    public float getRight() {
        return right;
    }

    public float getLeft() {
        return left;
    }

    public float getTop() {
        return top;
    }

    public float getBottom() {
        return bottom;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }
}
