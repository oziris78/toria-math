package com.telek.telekutils.swing;


import javax.swing.*;
import java.awt.*;


public class TSwingUtils {


    public static void hideAll(Component... components) { for(Component c : components) c.setVisible(false); }

    public static void showAll(Component... components) { for(Component c : components) c.setVisible(true); }

    public static void disableAll(Component... components) {for(Component c : components) c.setEnabled(false);}

    public static void enableAll(Component... components) {for(Component c : components) c.setEnabled(true);}
	
    public static <T extends JComponent> void addAllToPanel(JPanel pan, T... components) {for(T c : components) pan.add(c);}

	public static <T extends AbstractButton> void addAllToGroup(ButtonGroup group, T... buttons) { for(T btn : buttons) group.add(btn); }
	

}


