package io.github.nevemlaci;

import java.io.*;

public class MainMenuUtils {
    public static void saveMainMenu(MainMenu mainMenu, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(mainMenu.getWorkingDirectory());
        }
    }

    public static String loadMainMenu(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (String) ois.readObject();
        }
    }
}
