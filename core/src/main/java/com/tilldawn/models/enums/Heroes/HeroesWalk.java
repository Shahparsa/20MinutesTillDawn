package com.tilldawn.models.enums.Heroes;

import java.util.ArrayList;

public enum HeroesWalk {
    Dasher(DasherRun()),
    Diamond(DiamondRun()),
    Hina(HinaRun()),
    Lilith(LilithRun()),
    Scarlet(ScarletRun()),
    Shana(ShanaRun());

    private final ArrayList<String> animationAddresses;

    HeroesWalk(ArrayList<String> animationAddresses) {
        this.animationAddresses = animationAddresses;
    }

    private static ArrayList<String> DasherRun(){
        ArrayList<String> dasherRun = new ArrayList<>();
        dasherRun.add("Heroes/Dasher/Run_0.png");
        dasherRun.add("Heroes/Dasher/Run_1.png");
        dasherRun.add("Heroes/Dasher/Run_2.png");
        dasherRun.add("Heroes/Dasher/Run_3.png");
        return dasherRun;
    }

    private static ArrayList<String> DiamondRun(){
        ArrayList<String> diamondRun = new ArrayList<>();
        diamondRun.add("Heroes/Diamond/Run_0.png");
        diamondRun.add("Heroes/Diamond/Run_1.png");
        diamondRun.add("Heroes/Diamond/Run_2.png");
        diamondRun.add("Heroes/Diamond/Run_3.png");
        return diamondRun;
    }

    private static ArrayList<String> HinaRun(){
        ArrayList<String> hinaRun = new ArrayList<>();
        hinaRun.add("Heroes/Hina/Run_0.png");
        hinaRun.add("Heroes/Hina/Run_1.png");
        hinaRun.add("Heroes/Hina/Run_2.png");
        hinaRun.add("Heroes/Hina/Run_3.png");
        return hinaRun;
    }

    private static ArrayList<String> LilithRun(){
        ArrayList<String> lilithRun = new ArrayList<>();
        lilithRun.add("Heroes/Lilith/Run_0.png");
        lilithRun.add("Heroes/Lilith/Run_1.png");
        lilithRun.add("Heroes/Lilith/Run_2.png");
        lilithRun.add("Heroes/Lilith/Run_3.png");
        return lilithRun;
    }

    private static ArrayList<String> ScarletRun(){
        ArrayList<String> scarletRun = new ArrayList<>();
        scarletRun.add("Heroes/Scarlet/Run_0.png");
        scarletRun.add("Heroes/Scarlet/Run_1.png");
        scarletRun.add("Heroes/Scarlet/Run_2.png");
        scarletRun.add("Heroes/Scarlet/Run_3.png");
        return scarletRun;
    }

    private static ArrayList<String> ShanaRun(){
        ArrayList<String> shanaRun = new ArrayList<>();
        shanaRun.add("Heroes/Shana/Run_0.png");
        shanaRun.add("Heroes/Shana/Run_1.png");
        shanaRun.add("Heroes/Shana/Run_2.png");
        shanaRun.add("Heroes/Shana/Run_3.png");
        return shanaRun;
    }

    public ArrayList<String> getAnimationAddresses() {
        return animationAddresses;
    }
}
