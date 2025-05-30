package com.tilldawn.controller.MenuControllers;

import com.tilldawn.Main;
import com.tilldawn.models.App;
import com.tilldawn.models.enums.Abilities;
import com.tilldawn.views.GameView;
import com.tilldawn.views.Menus.AbilityMenuView;

import java.util.*;


public class AbilityMenuController {
    private AbilityMenuView view;
    private GameView gameView;
    private static Map<Integer , Abilities> abilities;
    private static Random rand = new Random();
    static {
        abilities = new HashMap<Integer, Abilities>();
        abilities.put(0 , Abilities.AMOCREASE);
        abilities.put(1 , Abilities.DAMAGER);
        abilities.put(2 , Abilities.PROCREASE);
        abilities.put(3 , Abilities.VITALITY);
        abilities.put(4 , Abilities.SPEEDY);
    }

    public void setView(AbilityMenuView view , GameView gameView) {
        this.gameView = gameView;
        this.view = view;
    }

    public void handleButton(Abilities abilities){
        if(abilities == Abilities.AMOCREASE){
            App.getCurrentGame().getWeapon().setMaxAmmo(App.getCurrentGame().getWeapon().getMaxAmmo() + 5);
            App.getCurrentGame().getWeapon().setAmmo(App.getCurrentGame().getWeapon().getAmmo() + 5);
            App.getCurrentGame().setAmoCreaseNumber(App.getCurrentGame().getAmoCreaseNumber() + 1);
        }else if(abilities == Abilities.DAMAGER){
            App.getCurrentGame().setDoubleDamageTime(10);
        }else if(abilities == Abilities.PROCREASE){
            App.getCurrentGame().getWeapon().setProjectile(App.getCurrentGame().getWeapon().getProjectile() + 1);
            App.getCurrentGame().setProCreaseNumber(App.getCurrentGame().getProCreaseNumber() + 1);
        }else if(abilities == Abilities.VITALITY){
            App.getCurrentGame().getPlayer().setMaxHp(App.getCurrentGame().getPlayer().getMaxHp() + 1);
            App.getCurrentGame().getPlayer().setHp(App.getCurrentGame().getPlayer().getHp() + 1);
            App.getCurrentGame().setVitalityNumber(App.getCurrentGame().getVitalityNumber() + 1);
        }else if(abilities == Abilities.SPEEDY){
            App.getCurrentGame().setDoubleSpeedTime(10);
        }
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(gameView);
    }

    public List<Abilities> getThreeRandomAbilities() {
        List<Integer> indices = new ArrayList<>(abilities.keySet());
        List<Abilities> result = new ArrayList<>();

        while (result.size() < 3 && !indices.isEmpty()) {
            int randomIndex = rand.nextInt(indices.size());
            result.add(abilities.get(indices.remove(randomIndex)));
        }
        return result;
    }

}
