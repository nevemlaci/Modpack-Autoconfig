package io.github.nevemlaci;

import javax.swing.*;
import java.awt.*;


public class NormalLookingComponentFactory {

    private NormalLookingComponentFactory(){

    }
    public static<T extends JComponent> T NormalLookingComponent(T component){
        component.setMaximumSize(new Dimension(200, 20));
        component.setAlignmentX(Component.LEFT_ALIGNMENT);

        return component;
    }
}
