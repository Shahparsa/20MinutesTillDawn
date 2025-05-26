package com.tilldawn.models.enums;

public enum Musics {
    Music1("Musics/06 The Death Waltz.mp3" , "Music1"),
    Music2("Musics/Oh Hiroshima - Waves.mp3" , "Music2"),
    Music3("Musics/The_Dooos_new_solo_Ethereal.mp3" , "Music3"),;

    private String musicPath;
    private String name;

    Musics(String musicPath, String name) {
        this.musicPath = musicPath;
        this.name = name;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public String getName() {
        return name;
    }

    public static Musics getMusicByName(String musicName) {
        for (Musics m : Musics.values()) {
            if(m.getName().equals(musicName)) {
                return m;
            }
        }
        return null;
    }
}
