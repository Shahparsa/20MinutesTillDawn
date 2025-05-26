package com.tilldawn.models.enums.Heroes;

import java.util.ArrayList;

public enum HeroesIdle {
    Dasher(DasherIdle()),
    Diamond(DiamondIdle()),
    Hina(HinaIdle()),
    Lilith(LilithIdle()),
    Scarlet(ScarletIdle()),
    Shana(ShanaIdle());

    private final ArrayList<String> animationAddresses;

    HeroesIdle(ArrayList<String> animationAddresses) {
        this.animationAddresses = animationAddresses;
    }

    private static ArrayList<String> DasherIdle(){
        ArrayList<String> dasherIdle = new ArrayList<>();
        dasherIdle.add("Heroes/Dasher/Idle_0.png");
        dasherIdle.add("Heroes/Dasher/Idle_1.png");
        dasherIdle.add("Heroes/Dasher/Idle_2.png");
        dasherIdle.add("Heroes/Dasher/Idle_3.png");
        dasherIdle.add("Heroes/Dasher/Idle_4.png");
        dasherIdle.add("Heroes/Dasher/Idle_5.png");
        return dasherIdle;
    }

    private static ArrayList<String> DiamondIdle(){
        ArrayList<String> diamondIdle = new ArrayList<>();
        diamondIdle.add("Heroes/Diamond/Idle_0.png");
        diamondIdle.add("Heroes/Diamond/Idle_1.png");
        diamondIdle.add("Heroes/Diamond/Idle_2.png");
        diamondIdle.add("Heroes/Diamond/Idle_3.png");
        diamondIdle.add("Heroes/Diamond/Idle_4.png");
        diamondIdle.add("Heroes/Diamond/Idle_5.png");
        return diamondIdle;
    }

    private static ArrayList<String> HinaIdle(){
        ArrayList<String> hinaIdle = new ArrayList<>();
        hinaIdle.add("Heroes/Hina/Idle_0.png");
        hinaIdle.add("Heroes/Hina/Idle_1.png");
        hinaIdle.add("Heroes/Hina/Idle_2.png");
        hinaIdle.add("Heroes/Hina/Idle_3.png");
        hinaIdle.add("Heroes/Hina/Idle_4.png");
        hinaIdle.add("Heroes/Hina/Idle_5.png");
        return hinaIdle;
    }

    private static ArrayList<String> LilithIdle(){
        ArrayList<String> lilithIdle = new ArrayList<>();
        lilithIdle.add("Heroes/Lilith/Idle_0.png");
        lilithIdle.add("Heroes/Lilith/Idle_1.png");
        lilithIdle.add("Heroes/Lilith/Idle_2.png");
        lilithIdle.add("Heroes/Lilith/Idle_3.png");
        lilithIdle.add("Heroes/Lilith/Idle_4.png");
        lilithIdle.add("Heroes/Lilith/Idle_5.png");
        return lilithIdle;
    }

    private static ArrayList<String> ScarletIdle(){
        ArrayList<String> scarletIdle = new ArrayList<>();
        scarletIdle.add("Heroes/Scarlet/Idle_0.png");
        scarletIdle.add("Heroes/Scarlet/Idle_1.png");
        scarletIdle.add("Heroes/Scarlet/Idle_2.png");
        scarletIdle.add("Heroes/Scarlet/Idle_3.png");
        scarletIdle.add("Heroes/Scarlet/Idle_4.png");
        scarletIdle.add("Heroes/Scarlet/Idle_5.png");
        return scarletIdle;
    }

    private static ArrayList<String> ShanaIdle(){
        ArrayList<String> shanaIdle = new ArrayList<>();
        shanaIdle.add("Heroes/Shana/Idle_0.png");
        shanaIdle.add("Heroes/Shana/Idle_1.png");
        shanaIdle.add("Heroes/Shana/Idle_2.png");
        shanaIdle.add("Heroes/Shana/Idle_3.png");
        shanaIdle.add("Heroes/Shana/Idle_4.png");
        shanaIdle.add("Heroes/Shana/Idle_5.png");
        return shanaIdle;
    }

    public ArrayList<String> getAnimationAddresses() {
        return animationAddresses;
    }
}
