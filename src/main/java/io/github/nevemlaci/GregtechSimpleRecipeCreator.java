package io.github.nevemlaci;

import javax.swing.*;
import java.util.List;

import static io.github.nevemlaci.NormalLookingComponentFactory.NormalLookingComponent;

public class GregtechSimpleRecipeCreator extends JPanel {
    String[] recipeTypes = {"mixer", "alloy_smelter"};

    MainMenu app;

    public GregtechSimpleRecipeCreator(MainMenu app) {
        this.app = app;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel itemInputsLabel = new JLabel("Item Inputs:");
        add(itemInputsLabel);
        List<JTextField> itemInputs = List.of(
                NormalLookingComponent(new JTextField()),
                NormalLookingComponent(new JTextField()),
                NormalLookingComponent(new JTextField()),
                NormalLookingComponent(new JTextField())
        );
        itemInputs.forEach(this::add);

        JLabel fluidInputsLabel = new JLabel("Fluid Inputs:");
        add(fluidInputsLabel);
        List<JTextField> fluidInputs = List.of(
                NormalLookingComponent(new JTextField()),
                NormalLookingComponent(new JTextField()),
                NormalLookingComponent(new JTextField()),
                NormalLookingComponent(new JTextField())
        );
        fluidInputs.forEach(this::add);

        JLabel itemOutputsLabel = new JLabel("Item Outputs:");
        add(itemOutputsLabel);
        List<JTextField> itemOutputs = List.of(
                NormalLookingComponent(new JTextField()),
                NormalLookingComponent(new JTextField()),
                NormalLookingComponent(new JTextField()),
                NormalLookingComponent(new JTextField())
        );
        itemOutputs.forEach(this::add);

        JLabel fluidOutputsLabel = new JLabel("Fluid Outputs:");
        add(fluidOutputsLabel);
        List<JTextField> fluidOutputs = List.of(
                NormalLookingComponent(new JTextField()),
                NormalLookingComponent(new JTextField()),
                NormalLookingComponent(new JTextField()),
                NormalLookingComponent(new JTextField())
        );
        fluidOutputs.forEach(this::add);

        JLabel durationLabel = new JLabel("Duration:");
        add(durationLabel);
        JTextField duration = NormalLookingComponent(new JTextField());
        add(duration);

        JLabel euTLabel = new JLabel("EU/t:");
        add(euTLabel);
        JTextField euT = NormalLookingComponent(new JTextField());
        add(euT);
        JComboBox<String> voltages = NormalLookingComponent(new JComboBox<>(GregtechVoltages.voltages));
        add(voltages);

        voltages.addActionListener(e -> {
            euT.setText(String.valueOf(GregtechVoltages.getVoltageMaximum((String) voltages.getSelectedItem())));
        });

        JLabel recipeTypeLabel = new JLabel("Recipe Type:");
        add(recipeTypeLabel);
        JComboBox<String> recipeType = NormalLookingComponent(new JComboBox<>(recipeTypes));
        add(recipeType);


        JButton save = NormalLookingComponent(new JButton("Save"));
        add(save);

        save.addActionListener(e -> {
            GregtechRecipe recipe = new GregtechRecipe(
                    itemInputs.stream().map(field -> field.getText().replace(" ", "")).toList(),
                    fluidInputs.stream().map(field -> field.getText().replace(" ", "")).toList(),
                    itemOutputs.stream().map(field -> field.getText().replace(" ", "")).toList(),
                    fluidOutputs.stream().map(field -> field.getText().replace(" ", "")).toList(),
                    Integer.parseInt(duration.getText().replace(" ", "")),
                    Integer.parseInt(euT.getText().replace(" ", ""))
            );

            System.out.println(recipe.toKubeJS(recipeType.getSelectedItem().toString().replace(" ", "")));
        });
    }

    public static class Window extends JFrame {
        public Window(MainMenu app) {
            setTitle("Gregtech Mixer Recipe Creator");
            setSize(800, 900);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            JScrollPane scrollPane = new JScrollPane(new GregtechSimpleRecipeCreator(app));
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            add(scrollPane);
            setVisible(true);
        }
    }


}
