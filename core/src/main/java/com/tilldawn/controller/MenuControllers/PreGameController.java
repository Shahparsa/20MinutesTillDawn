package com.tilldawn.controller.MenuControllers;

import com.tilldawn.Main;
import com.tilldawn.controller.GameController;
import com.tilldawn.models.*;
import com.tilldawn.models.Weapon.Weapon;
import com.tilldawn.models.enums.Language;
import com.tilldawn.models.enums.WeaponsType;
import com.tilldawn.views.GameView;
import com.tilldawn.views.Menus.HeroSelectView;
import com.tilldawn.views.Menus.PreGameMenuView;

public class PreGameController {
    private PreGameMenuView view;

    public void setView(PreGameMenuView view) {
        this.view = view;
    }

    public void handleBackButton(){
        navigateToHeroSelect();
    }

    public void handleStartButton(){
        String timeString = view.getTimeSelectBox().getSelected();
        String weaponString = view.getWeaponSelectBox().getSelected();

        float time = 0;
        if(timeString.equals("2")){
            time = 2;
        }else if(timeString.equals("5")){
            time = 5;
        }else if(timeString.equals("10")){
            time = 10;
        }else {
            time = 20;
        }
        Weapon weapon;
        if(weaponString.equals(Language.Shotgun.getLanguage())){
            weapon = new Weapon(WeaponsType.ShotGun);
        }else if(weaponString.equals(Language.DualSMG.getLanguage())){
            weapon = new Weapon(WeaponsType.DualSMG);
        }else{
            weapon = new Weapon(WeaponsType.Revolver);
        }
        Hero hero = new Hero(App.getHero());
        Player player = new Player(hero);

        Game game = new Game(time , hero , player , weapon);
        App.setCurrentGame(game);
        // Prepare Game
        navigateToGame();
    }

    private void navigateToHeroSelect(){
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new HeroSelectView(new HeroSelectController(),
            GameAssetManager.getInstance().getSkin()));
    }

    private void navigateToGame(){
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new GameView(new GameController()
            , GameAssetManager.getInstance().getSkin()));
    }

}
