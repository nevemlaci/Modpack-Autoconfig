package io.github.nevemlaci;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static io.github.nevemlaci.NormalLookingComponentFactory.NormalLookingComponent;

public class GregtechElementCreator extends JPanel {

    private final MainMenu app;

    public GregtechElementCreator(MainMenu app) {
        this.app = app;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Registry Name:"));
        JTextField registryName = NormalLookingComponent(new JTextField());
        registryName.setToolTipText("Registry Name");
        add(registryName);

        add(new JLabel("Display Name:"));
        JTextField displayName = NormalLookingComponent(new JTextField());
        displayName.setToolTipText("Display Name");
        add(displayName);
        JButton autoRegistryName = new JButton("Generate registry name");
        autoRegistryName.addActionListener(_ -> {
            registryName.setText(displayName.getText().toLowerCase().replace(" ", "_"));
        });
        add(autoRegistryName);

        add(new JLabel("Components:"));
        JTextField components = NormalLookingComponent(new JTextField());
        components.setToolTipText("Components");
        add(components);

        JCheckBox customFormula = new JCheckBox("Custom Formula");
        add(customFormula);
        JTextField formula = NormalLookingComponent(new JTextField());
        formula.setToolTipText("Formula");
        add(formula);

        JComboBox<String> type = NormalLookingComponent(new JComboBox<>(new String[]{"ingot", "dust"}));
        add(type);

        JCheckBox isCable = NormalLookingComponent(new JCheckBox("Is Cable"));
        add(isCable);


        JComboBox<String> voltage = NormalLookingComponent(new JComboBox<>(GregtechVoltages.voltages));
        add(voltage);

        JCheckBox isSuperconductor = new JCheckBox("Is Superconductor");
        add(isSuperconductor);

        add(new JLabel("Energy Loss:"));
        JTextField energyLoss = NormalLookingComponent(new JTextField());
        energyLoss.setToolTipText("Energy Loss");
        add(energyLoss);

        add(new JLabel("Blast Temperature:"));
        JTextField blastTemp = NormalLookingComponent(new JTextField());
        add(blastTemp);
        add(new JLabel("Blast Gas:"));
        JTextField blastGas = NormalLookingComponent(new JTextField());
        add(blastGas);
        JComboBox<String> blastVoltage = NormalLookingComponent(new JComboBox<>(GregtechVoltages.voltages));
        add(blastVoltage);
        add(new JLabel("Blast Duration:"));
        JTextField blastDuration = NormalLookingComponent(new JTextField());
        add(blastDuration);

        JColorChooser colorChooser = new JColorChooser();
        colorChooser.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(colorChooser);

        JButton saveMatiralToFile = new JButton("Save material to file...");
        saveMatiralToFile.setMaximumSize(new Dimension(200, 20));
        add(saveMatiralToFile);

        saveMatiralToFile.addActionListener(_ -> {
            GregtechElement element = new GregtechElement();
            element.registryName(registryName.getText())
                    .displayName(displayName.getText())
                    .type(type.getSelectedItem().toString())
                    .components(components.getText())
                    .formula(formula.getText())
                    .color(colorChooser.getColor())
                    .isCable(isCable.isSelected())
                    .voltage(voltage.getSelectedItem().toString())
                    .isSuperconductor(isSuperconductor.isSelected())
                    .loss(energyLoss.getText().isEmpty() ? 0 : Integer.parseInt(energyLoss.getText()))
                    .blastTemp(blastTemp.getText().isEmpty() ? 0 : Integer.parseInt(blastTemp.getText()))
                    .blastGas(blastGas.getText().isEmpty() ? 0 : Integer.parseInt(blastGas.getText()))
                    .blastVoltage(blastVoltage.getSelectedItem().toString())
                    .blastDuration(blastDuration.getText().isEmpty() ? 0 : Integer.parseInt(blastDuration.getText()));

            String content = buildElement(element);

            writeToFile(content, element);
        });
    }

    private String buildElement(GregtechElement element) {
        String[] componentsArray = element.getComponents().split(",");

        String startupScript =
                        "GTCEuStartupEvents.registry('gtceu:material', event => {\n\t"
                        + "event.create('" + element.getRegistryName() + "')\n\t\t"
                        + "." + element.getType() + "()\n\t\t"
                        + ".components(";

        for (String component : componentsArray) {
            startupScript += "'" + component.replaceAll(" ", "") + "', ";
        }
        startupScript = startupScript.substring(0, startupScript.length() - 2);
        startupScript += ")\n\t\t";
        startupScript += ".color(0x" + Integer.toHexString(element.getColor().getRGB()).substring(2) + ").iconSet(GTMaterialIconSet.BRIGHT)\n\t\t";

        if (element.isCable()) {
            startupScript +=
                            ".cableProperties("
                            + "GTValues.V[GTValues." + element.getVoltage() + "], "
                            + "1," + (element.isSuperconductor() ? "0, true" : element.getLoss() + "false") + ")\n\t\t";
        }

        if (element.getBlastTemp() != 0) {
            if (element.getBlastTemp() < 0) {
                throw new IllegalArgumentException("Blast Temperature must be positive or zero/empty");
            }
            startupScript +=
                            ".blastTemp("
                            + element.getBlastTemp() + ", "
                            + (element.getBlastGas() > 0 ? element.getBlastGas() : "null") + ", "
                            + "GTValues.V[GTValues." + element.getBlastVoltage() + "], "
                            + element.getBlastDuration() + ")\n";
        }

        startupScript += "});\n\n\n";

        return startupScript;
    }

    private void writeToFile(String content, GregtechElement element) {
        JFileChooser fileChooser = new JFileChooser(app.getWorkingDirectory() + "/kubejs/startup_scripts");
        fileChooser.setDialogTitle("Save material to file...");
        fileChooser.setApproveButtonText("Save");
        fileChooser.setSelectedFile(new File(element.getRegistryName() + ".js"));
        fileChooser.showSaveDialog(this);

        try {
            Files.write(Paths.get(fileChooser.getSelectedFile().getAbsolutePath()), content.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            try {
                Files.write(Paths.get(fileChooser.getSelectedFile().getAbsolutePath()), content.getBytes(), StandardOpenOption.CREATE_NEW);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (!element.getDisplayName().isEmpty()) {
            File file = new File(app.getWorkingDirectory() + "/kubejs/assets/gtceu/lang/en_us.json");
            String jsonStr = null;
            try {
                jsonStr = new String(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JSONObject json = new JSONObject(jsonStr);
            json.put("material.gtceu." + element.getRegistryName(), element.getDisplayName());

            try {
                Files.write(file.toPath(), json.toString(4).getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class GregtechElementCreatorWindow extends JFrame {
        public GregtechElementCreatorWindow(MainMenu app) {
            setTitle("Gregtech Element Creator");
            setSize(800, 900);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            JScrollPane scrollPane = new JScrollPane(new GregtechElementCreator(app));
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            add(scrollPane);
            setVisible(true);
        }
    }

}
