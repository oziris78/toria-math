package com.telek.telekutils.swing.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class SwingPanel {

    private JPanel panel;

    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public SwingPanel(){
        this.panel = new JPanel();
    }

    public SwingPanel(LayoutManager layoutManager){
        this.panel = new JPanel(layoutManager);
    }

    ///////////////
    /*  METHODS  */
    ///////////////

    public SwingPanel setBounds(Rectangle bounds){
        this.panel.setBounds(bounds);
        return this;
    }
    public SwingPanel setBackground(Color bgColor){
        this.panel.setBackground(bgColor);
        return this;
    }
    public SwingPanel setForeground(Color fgColor){
        this.panel.setForeground(fgColor);
        return this;
    }
    public SwingPanel setCursor(Cursor cursor){
        this.panel.setCursor(cursor);
        return this;
    }
    public SwingPanel setEnabled(boolean isEnabled){
        this.panel.setEnabled(isEnabled);
        return this;
    }
    public SwingPanel setFont(Font font){
        this.panel.setFont(font);
        return this;
    }
    public SwingPanel setVisible(boolean isVisible){
        this.panel.setVisible(isVisible);
        return this;
    }
    public SwingPanel setLayout(LayoutManager layout){
        this.panel.setLayout(layout);
        return this;
    }
    public SwingPanel setBorder(Border border){
        this.panel.setBorder(border);
        return this;
    }
    public SwingPanel setOpaque(boolean isOpaque){
        this.panel.setOpaque(isOpaque);
        return this;
    }
    public SwingPanel setToolTipText(String toolTipText){
        this.panel.setToolTipText(toolTipText);
        return this;
    }
    public SwingPanel addMouseListener(MouseListener mouseListener){
        this.panel.addMouseListener(mouseListener);
        return this;
    }
    public SwingPanel addMouseMotionListener(MouseMotionListener mouseMotionListener){
        this.panel.addMouseMotionListener(mouseMotionListener);
        return this;
    }
    public SwingPanel addMouseWheelListener(MouseWheelListener mouseWheelListener){
        this.panel.addMouseWheelListener(mouseWheelListener);
        return this;
    }


    ///////////////////////////
    /*  GETTERS AND SETTERS  */
    ///////////////////////////


    public JPanel getPanel() {
        return panel;
    }

}

