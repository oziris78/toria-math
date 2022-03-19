package com.telek.telekutils.swing.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class SwingLabel {

    private JLabel label;

    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public SwingLabel(JLabel label){
        this.label = label;
    }

    public SwingLabel(SwingLabel swingLabel){
        this.label = swingLabel.getLabel();
    }

    public SwingLabel(String text, Icon icon, int horizontalAlignment) {
        this.label = new JLabel(text, icon, horizontalAlignment);
    }

    public SwingLabel(String text, int horizontalAlignment) {
        this.label = new JLabel(text, horizontalAlignment);
    }

    public SwingLabel(String text) {
        this.label = new JLabel(text);
    }

    public SwingLabel(Icon image, int horizontalAlignment) {
        this.label = new JLabel(image, horizontalAlignment);
    }

    public SwingLabel(Icon image) {
        this.label = new JLabel(image);
    }

    public SwingLabel() {
        this.label = new JLabel();
    }

    ///////////////
    /*  METHODS  */
    ///////////////

    public SwingLabel addMouseListener(MouseListener mouseListener){
        this.label.addMouseListener(mouseListener);
        return this;
    }
    public SwingLabel addMouseMotionListener(MouseMotionListener mouseMotionListener){
        this.label.addMouseMotionListener(mouseMotionListener);
        return this;
    }
    public SwingLabel addMouseWheelListener(MouseWheelListener mouseWheelListener){
        this.label.addMouseWheelListener(mouseWheelListener);
        return this;
    }
    public SwingLabel setBounds(Rectangle bounds){
        this.label.setBounds(bounds);
        return this;
    }
    public SwingLabel setBackground(Color bgColor){
        this.label.setBackground(bgColor);
        return this;
    }
    public SwingLabel setForeground(Color fgColor){
        this.label.setForeground(fgColor);
        return this;
    }
    public SwingLabel setCursor(Cursor cursor){
        this.label.setCursor(cursor);
        return this;
    }
    public SwingLabel setEnabled(boolean isEnabled){
        this.label.setEnabled(isEnabled);
        return this;
    }
    public SwingLabel setFont(Font font){
        this.label.setFont(font);
        return this;
    }
    public SwingLabel setVisible(boolean isVisible){
        this.label.setVisible(isVisible);
        return this;
    }
    public SwingLabel setLayout(LayoutManager layout){
        this.label.setLayout(layout);
        return this;
    }
    public SwingLabel setBorder(Border border){
        this.label.setBorder(border);
        return this;
    }
    public SwingLabel setOpaque(boolean isOpaque){
        this.label.setOpaque(isOpaque);
        return this;
    }
    public SwingLabel setToolTipText(String toolTipText){
        this.label.setToolTipText(toolTipText);
        return this;
    }
    public SwingLabel setText(String text){
        this.label.setText(text);
        return this;
    }
    public SwingLabel setDisabledIcon(Icon disabledIcon){
        this.label.setDisabledIcon(disabledIcon);
        return this;
    }
    public SwingLabel setIcon(Icon icon){
        this.label.setIcon(icon);
        return this;
    }
    public SwingLabel setHorizontalAlignment(int horizontalAlignment){
        this.label.setHorizontalAlignment(horizontalAlignment);
        return this;
    }
    public SwingLabel setVerticalAlignment(int verticalAlignment){
        this.label.setVerticalAlignment(verticalAlignment);
        return this;
    }
    public SwingLabel setHorizontalTextPosition(int horizontalTextPosition){
        this.label.setHorizontalTextPosition(horizontalTextPosition);
        return this;
    }
    public SwingLabel setVerticalTextPosition(int verticalTextPosition){
        this.label.setVerticalTextPosition(verticalTextPosition);
        return this;
    }
    public SwingLabel setIconTextGap(int iconTextGap){
        this.label.setIconTextGap(iconTextGap);
        return this;
    }

    ///////////////////////////
    /*  GETTERS AND SETTERS  */
    ///////////////////////////

    public JLabel getLabel() {
        return label;
    }
}
