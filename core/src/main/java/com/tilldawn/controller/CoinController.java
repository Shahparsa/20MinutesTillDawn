package com.tilldawn.controller;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.Coin;
import com.tilldawn.models.Game;
import com.tilldawn.models.Player;

public class CoinController {
    private Player player;
    private Game game;

    public CoinController() {
        this.player = App.getCurrentGame().getPlayer();
        this.game = App.getCurrentGame();
    }

    public void removeNow(Coin coin) {
        App.getCurrentGame().getCoins().remove(coin);
    }

    public void checkCollide(Coin coin) {
        if(coin.getCollisionRect().collidesWith(player.getCollisionRect())) {
            if(player.addXp(3)){
                //TODO : Animation
            }
            App.getCurrentGame().getDeletedCoins().add(coin);
        }
    }

    public void render(Coin coin){
        Sprite sprite = Coin.getSprite();
        sprite.setPosition(coin.getX(), coin.getY());
        sprite.draw(Main.getBatch());
    }
}
