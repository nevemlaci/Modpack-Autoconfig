package io.github.nevemlaci;

import java.awt.*;

/**
 * A helper class to keep track of the properties of a Gregtech material.
 */
public class GregtechMaterial {
    private String registryName;
    private String displayName;
    private String type;
    private String components;
    private String formula;
    private Color color;
    private boolean isCable;
    private String voltage;
    private int loss;
    private boolean isSuperconductor;
    private int blastTemp;
    private int blastGas;
    private String blastVoltage;
    private int blastDuration;

    public String getRegistryName() {
        return registryName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getType() {
        return type;
    }

    public String getComponents() {
        return components;
    }

    public String getFormula() {
        return formula;
    }

    public Color getColor() {
        return color;
    }

    public boolean isCable() {
        return isCable;
    }

    public String getVoltage() {
        return voltage;
    }

    public int getLoss() {
        return loss;
    }

    public boolean isSuperconductor() {
        return isSuperconductor;
    }

    public int getBlastTemp() {
        return blastTemp;
    }

    public int getBlastGas() {
        return blastGas;
    }

    public String getBlastVoltage() {
        return blastVoltage;
    }

    public int getBlastDuration() {
        return blastDuration;
    }


    GregtechMaterial() {}

    public GregtechMaterial registryName(String registryName){
        this.registryName = registryName;
        return this;
    }

    public GregtechMaterial displayName(String displayName){
        this.displayName = displayName;
        return this;
    }

    public GregtechMaterial type(String type){
        this.type = type;
        return this;
    }

    public GregtechMaterial components(String components){
        this.components = components;
        return this;
    }

    public GregtechMaterial formula(String formula){
        this.formula = formula;
        return this;
    }

    public GregtechMaterial color(Color color){
        this.color = color;
        return this;
    }

    public GregtechMaterial isCable(boolean isCable){
        this.isCable = isCable;
        return this;
    }

    public GregtechMaterial voltage(String voltage){
        this.voltage = voltage;
        return this;
    }

    public GregtechMaterial loss(int loss){
        this.loss = loss;
        return this;
    }

    public GregtechMaterial isSuperconductor(boolean isSuperconductor){
        this.isSuperconductor = isSuperconductor;
        return this;
    }

    public GregtechMaterial blastTemp(int blastTemp){
        this.blastTemp = blastTemp;
        return this;
    }

    public GregtechMaterial blastGas(int blastGas){
        this.blastGas = blastGas;
        return this;
    }

    public GregtechMaterial blastVoltage(String blastVoltage){
        this.blastVoltage = blastVoltage;
        return this;
    }

    public GregtechMaterial blastDuration(int blastDuration){
        this.blastDuration = blastDuration;
        return this;
    }
}
