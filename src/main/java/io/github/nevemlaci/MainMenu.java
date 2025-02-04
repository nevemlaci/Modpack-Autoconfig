package io.github.nevemlaci;

import javax.swing.*;
import java.io.Serializable;

import static io.github.nevemlaci.NormalLookingComponentFactory.NormalLookingComponent;

public class MainMenu extends JFrame implements Serializable {
    private String workingDirectory = null;


    public MainMenu(String workingDirectory) {
        this.workingDirectory = workingDirectory;
        Run();
    }

    public void Run(){
        setTitle("Modpack Autoconfig");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JButton newGregtechElement = NormalLookingComponent(new JButton("New Gregtech Material..."));

        add(newGregtechElement);
        newGregtechElement.addActionListener(e -> new GregtechMaterialCreator.Window(this));

        JButton newGregtechMixerRecipe = NormalLookingComponent(new JButton("New Simple Gregtech Recipe..."));
        add(newGregtechMixerRecipe);
        newGregtechMixerRecipe.addActionListener(e -> new GregtechSimpleRecipeCreator.Window(this));

        JFileChooser workingDirectoryChooser = new JFileChooser(workingDirectory);
        workingDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        workingDirectoryChooser.setDialogTitle("Select Working Directory");
        workingDirectoryChooser.setApproveButtonText("Select");
        workingDirectoryChooser.showOpenDialog(this);

        workingDirectory = workingDirectoryChooser.getSelectedFile().getAbsolutePath();

        setVisible(true);
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }
}
