package io.github.nevemlaci;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String cwd;
        try {
            cwd = MainMenuUtils.loadMainMenu("mainMenu.data");
        } catch (IOException | ClassNotFoundException e) {
            cwd = null;
        }

        MainMenu mainMenu = new MainMenu(cwd);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                MainMenuUtils.saveMainMenu(mainMenu, "mainMenu.data");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

    }
}