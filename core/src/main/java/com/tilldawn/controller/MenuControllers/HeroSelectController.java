package com.tilldawn.controller.MenuControllers;

import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.GameAssetManager;
import com.tilldawn.models.enums.Heroes.HeroesType;
import com.tilldawn.views.Menus.MainMenuView;
import com.tilldawn.views.Menus.HeroSelectView;
import com.tilldawn.views.Menus.PreGameMenuView;

public class HeroSelectController {

    private HeroSelectView view;

    public void setView(HeroSelectView view) {
        this.view = view;
    }


    public void selectHero(int index) {
        if(view != null) {
            if(index == 0){//Hina
                App.setHero(HeroesType.Hina);
            }else if(index == 1){//Dasher
                App.setHero(HeroesType.Dasher);
            }else if(index == 2){//Diamond
                App.setHero(HeroesType.Diamond);
            }else if(index == 3){//Lilith
                App.setHero(HeroesType.Lilith);
            }else if(index == 4){//Scarlet
                App.setHero(HeroesType.Scarlet);
            }else if(index == 5){//Shane
                App.setHero(HeroesType.Shana);
            }
        }
        navigatePreGameMenu();
    }

    public void navigateMainMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getInstance().getSkin()));
    }

    private void navigatePreGameMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new PreGameMenuView(new PreGameController(),
            GameAssetManager.getInstance().getSkin()));
    }

}
