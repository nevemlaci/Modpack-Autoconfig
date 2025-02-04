package io.github.nevemlaci;

import javax.swing.*;
import java.awt.*;


public class NormalLookingComponentFactory {

    private NormalLookingComponentFactory(){

    }
    public static<T extends JComponent> T normalLookingComponent(T component){
        return normalLookingComponent(component, 200);
    }

    public static<T extends JComponent> T normalLookingComponent(T component, int width){
        component.setMaximumSize(new Dimension(width, 20));
        component.setAlignmentX(Component.LEFT_ALIGNMENT);

        return component;
    }
}
