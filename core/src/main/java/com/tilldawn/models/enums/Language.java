package com.tilldawn.models.enums;

import com.badlogic.gdx.Input;
import com.tilldawn.models.App;

public enum Language {
    // Menus
    GameMenu("Game Menu", "Spiel Menü"),
    LoginMenu("Login Menu", "Anmelde Menü"),
    RegisterMenu("Register Menu", "Registrierungs Menü"),
    ForgetMenu("Forget Menu", "Passwort vergessen"),
    MainMenu("Main Menu", "Haupt Menü"),
    HintMenu("Hint Menu", "Hinweise Menü"),
    ScoreBoardMenu("Score Board Menu", "Anzeigetafel Menü"),
    SettingsMenu("Setting Menu", "Einstellungen Menü"),
    ProfileMenu("Profile Menu", "Profil Menü"),
    PreGameMenu("Pre Game Menu", "Spielvorbereitung"),
    PauseMenu("Pause Menu", "Pausenmenü"),
    YouWin("You Win", "Sie Gewinnen"),
    YouLose("You Lose", "Du Verlierst"),
    ChooseAbilities("Choose Abilities", "Wähle Fähigkeiten"),

    // Fields
    UserName("Username", "Benutzername"),
    Skip("Skip", "Überspringen"),
    Password("Password", "Passwort"),
    Forget("Forget", "Vergessen"),
    Back("Back", "Zurück"),
    Login("Login", "Anmelden"),
    Register("Register", "Registrieren"),
    Exit("Exit", "Beenden"),
    Language("Language", "Sprache"),
    Answer("Answer", "Antwort"),
    Change("Change", "Ändern"),
    NewPassword("New Password", "Neues Passwort"),
    Setting("Setting", "Einstellung"),
    Profile("Profile", "Profil"),
    PreGame("Pregame", "Vor dem Spiel"),
    ScoreBoard("Scoreboard", "Anzeigetafel"),
    Hint("Hint", "Hinweis"),
    loadGame("loadGame", "Spiel laden"),
    AutoReload("AutoReload", "Automatisches Neuladen"),
    Volume("Volume", "Volumen"),
    Music("Music", "Musik"),
    BlackAndWhite("Black&White", "Schwarz&Weiß"),
    Score("Score", "Punktzahl"),
    Kill("Kill", "Tötet"),
    Time("Time", "Zeit"),
    OrderingBy("Ordering by", "Oredring von"),
    Upload("Upload", "Hochladen"),
    ChangeAvatar("Change Avatar", "Avatar ändern"),
    DeleteAccount("Delete Account", "Konto löschen"),
    SelectYouHero("Select Your Hero", "Wählen Sie Ihren Helden aus"),
    SelectTimeAndWeapon("Select Time & Weapon", "Wählen Sie Zeit & Waffe"),
    Speed("Speed", "Geschwindigkeit"),
    HP("HP", "HP"),
    Start("Start", "Start"),
    Weapon("Weapon", "Waffe"),
    Heroes("Heroes", "Helden"),
    Cheats("Cheats", "Cheats"),
    Abilities("Abilities", "Fähigkeiten"),
    Keys("Keys", "Tasten"),
    Resume("Resume", "Fortsetzen"),
    SaveAndExit("Save&Exit", "Speichern&Beenden"),
    GiveUp("GiveUp", "Aufgeben"),
    Level("Level", "Ebene"),


    // KeyBinds
    Up("Up", "Hoch"),
    Down("Down", "Runter"),
    Left("Left", "Links"),
    Right("Right", "Rechts"),
    Reload("Reload", "Neuladen"),
    Shoot("Shoot", "Schießen"),

    //Weapons
    DualSMG("Dual SMG", "Doppelte SMG"),
    Shotgun("Shotgun", "Schrotflinte"),
    Revolver("Revolver", "Revolver"),

    //Cheats
    CheatTime("Cheat Time", "Cheat Zeit"),
    CheatLevel("Cheat Level", "Cheat Level"),
    CheatHealth("Cheat Health", "Cheat Gesundheit"),
    CheatBoss("Cheat Boss", "Cheat Boss"),
    CheatDamage("Cheat Damage", "Cheat Schaden"),
    CheatTimeDescription("-1 Minute", "-1 Minute"),
    CheatLevelDescription("+1 Level", "+1 Level"),
    CheatHealthDescription("+1 Health In\n Case Health = 0", " +1 Gesundheit,\n falls Gesundheit = 0"),
    CheatBossDescription("Spawn Boss", "Boss beschwören"),
    CheatDamageDescription("+1 Damage", "+1 Schaden"),

    //Abilities
    Vitality("Vitality", "Vitalität"),
    Damager("Damager", "Schaden"),
    ProCrease("Procrease", "Prozentsatz"),
    AmoCrease("AmoCrease", "AmoCrease"),
    Speedy("Speedy", "Schnell"),

    // Messages
    UserNotFound("User Not Found!", "Benutzer nicht gefunden!"),
    IncorrectPassword("Incorrect Password!", "Falsches Passwort!"),
    UsernameIsAlreadyTaken("Username Is Already Taken!", "Benutzername bereits vergeben!"),
    PasswordIsInvalid("Password Is Invalid!", "Ungültiges Passwort!"),
    IncorrectSecurityQuestion("Incorrect Security Question", "Falsche Sicherheitsfrage"),
    IncorrectAnswerToSecurityQuestion("Incorrect answer to security question",
        "Falsche Antwort auf die Sicherheitsfrage"),
    NoAnswer("No Answer!", "Keine Antwort!"),
    ChangesApplied("Changes Applied!", "Änderungen übernommen!"),
    UploadFailed("UploadFailed", "Hochladen fehlgeschlagen"),
    NoChangesApplied("No Changes Applied!", "Es wurden keine Änderungen vorgenommen!");

    private final String english;
    private final String german;

    Language(String english, String german) {
        this.english = english;
        this.german = german;
    }


    public String getLanguage() {
        if (App.getLanguage().equals("English")) {
            return english;
        } else {
            return german;
        }
    }
}
