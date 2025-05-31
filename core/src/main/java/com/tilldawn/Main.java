package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.tilldawn.controller.MenuControllers.StartMenuController;
import com.tilldawn.database.DatabaseHelper;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Musics;
import com.tilldawn.views.Menus.StartMenuView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    private Cursor cursor;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();

        DatabaseHelper.createDatabase();

        setCustomCursorFromTexture();
        App.playMusic(Musics.Music1.getMusicPath());
        getMain().setScreen(new StartMenuView(new StartMenuController(), GameAssetManager.getInstance().getSkin()) {
        });
    }

    private void setCustomCursorFromTexture() {
        try {
            Texture cursorTexture = new Texture(Gdx.files.internal("MenuThings/T_Cursor.png"));

            if (!cursorTexture.getTextureData().isPrepared()) {
                cursorTexture.getTextureData().prepare();
            }
            Pixmap cursorPixmap = cursorTexture.getTextureData().consumePixmap();

            cursor = Gdx.graphics.newCursor(cursorPixmap, 0, 0);
            Gdx.graphics.setCursor(cursor);

            cursorPixmap.dispose();
        } catch (Exception e) {
            Gdx.app.error("Cursor", "Error loading custom cursor", e);
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        }
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }

    public void setBlackAndWhite() {
        if (!App.isBlackWhite())
            batch.setShader(null);
        else {
            String vertexShader = "attribute vec4 a_position;\n" +
                "attribute vec4 a_color;\n" +
                "attribute vec2 a_texCoord0;\n" +
                "uniform mat4 u_projTrans;\n" +
                "varying vec4 v_color;\n" +
                "varying vec2 v_texCoords;\n" +
                "void main() {\n" +
                "    v_color = a_color;\n" +
                "    v_texCoords = a_texCoord0;\n" +
                "    gl_Position = u_projTrans * a_position;\n" +
                "}";

            String fragmentShader = "#ifdef GL_ES\n" +
                "    precision mediump float;\n" +
                "#endif\n" +
                "varying vec4 v_color;\n" +
                "varying vec2 v_texCoords;\n" +
                "uniform sampler2D u_texture;\n" +
                "void main() {\n" +
                "    vec4 c = texture2D(u_texture, v_texCoords) * v_color;\n" +
                "    float gray = (c.r + c.g + c.b) / 3.0;\n" +
                "    gl_FragColor = vec4(gray, gray, gray, c.a);\n" +
                "}";

            ShaderProgram grayscaleShader = new ShaderProgram(vertexShader, fragmentShader);
            batch.setShader(grayscaleShader);
        }
    }

}
