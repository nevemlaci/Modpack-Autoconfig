package io.github.nevemlaci;

import java.awt.*;

public class GregtechElement {
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


    GregtechElement() {}

    public GregtechElement registryName(String registryName){
        this.registryName = registryName;
        return this;
    }

    public GregtechElement displayName(String displayName){
        this.displayName = displayName;
        return this;
    }

    public GregtechElement type(String type){
        this.type = type;
        return this;
    }

    public GregtechElement components(String components){
        this.components = components;
        return this;
    }

    public GregtechElement formula(String formula){
        this.formula = formula;
        return this;
    }

    public GregtechElement color(Color color){
        this.color = color;
        return this;
    }

    public GregtechElement isCable(boolean isCable){
        this.isCable = isCable;
        return this;
    }

    public GregtechElement voltage(String voltage){
        this.voltage = voltage;
        return this;
    }

    public GregtechElement loss(int loss){
        this.loss = loss;
        return this;
    }

    public GregtechElement isSuperconductor(boolean isSuperconductor){
        this.isSuperconductor = isSuperconductor;
        return this;
    }

    public GregtechElement blastTemp(int blastTemp){
        this.blastTemp = blastTemp;
        return this;
    }

    public GregtechElement blastGas(int blastGas){
        this.blastGas = blastGas;
        return this;
    }

    public GregtechElement blastVoltage(String blastVoltage){
        this.blastVoltage = blastVoltage;
        return this;
    }

    public GregtechElement blastDuration(int blastDuration){
        this.blastDuration = blastDuration;
        return this;
    }
}
