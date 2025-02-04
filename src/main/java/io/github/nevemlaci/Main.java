package io.github.nevemlaci;

import java.io.*;
import java.util.HashMap;


public class Main {
    public static void saveNumberOfRecipesWithSameOutput(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("numberOfRecipesWithSameOutput.data"))) {
            oos.writeObject(GregtechRecipe.numberOfRecipesWithSameOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadNumberOfRecipesWithSameOutput(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("numberOfRecipesWithSameOutput.data"))) {
            GregtechRecipe.numberOfRecipesWithSameOutput = (HashMap<String, Integer>)(ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            GregtechRecipe.numberOfRecipesWithSameOutput = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        String cwd;
        try {
            cwd = MainMenuUtils.loadMainMenu("mainMenu.data");
            loadNumberOfRecipesWithSameOutput();

        } catch (IOException | ClassNotFoundException e) {
            cwd = null;
        }

        MainMenu mainMenu = new MainMenu(cwd);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                MainMenuUtils.saveMainMenu(mainMenu, "mainMenu.data");
                saveNumberOfRecipesWithSameOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

    }
}