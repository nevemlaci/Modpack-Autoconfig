package io.github.nevemlaci;

import javax.swing.*;
import java.io.Serializable;

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

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu gtceu = new JMenu("GTCEU");
        menuBar.add(gtceu);
        JMenuItem gtceuConfig = new JMenuItem("Add new material...");
        gtceu.add(gtceuConfig);
        gtceuConfig.addActionListener(e -> new GregtechMaterialCreator.Window(this));
        JMenuItem gtceuRecipe = new JMenuItem("Add new recipe...");
        gtceu.add(gtceuRecipe);
        gtceuRecipe.addActionListener(e -> new GregtechSimpleRecipeCreator.Window(this));

        JMenu options = new JMenu("Options");
        menuBar.add(options);
        JMenuItem workingDirectory = new JMenuItem("Change Working Directory...");
        options.add(workingDirectory);
        workingDirectory.addActionListener(e -> workingDirectorySelector());


//        JButton newGregtechElement = NormalLookingComponentFactory.normalLookingComponent(new JButton("New Gregtech Material..."));
//
//        add(newGregtechElement);
//        newGregtechElement.addActionListener(e -> new GregtechMaterialCreator.Window(this));
//
//        JButton newGregtechMixerRecipe = NormalLookingComponentFactory.normalLookingComponent(new JButton("New Simple Gregtech Recipe..."), 300);
//        add(newGregtechMixerRecipe);
//        newGregtechMixerRecipe.addActionListener(e -> new GregtechSimpleRecipeCreator.Window(this));

        workingDirectorySelector();

        setVisible(true);
    }

    private void workingDirectorySelector(){
        JFileChooser workingDirectoryChooser = new JFileChooser(workingDirectory);
        workingDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        workingDirectoryChooser.setDialogTitle("Select Working Directory");
        workingDirectoryChooser.setApproveButtonText("Select");
        workingDirectoryChooser.showOpenDialog(this);

        workingDirectory = workingDirectoryChooser.getSelectedFile().getAbsolutePath();
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }
}
